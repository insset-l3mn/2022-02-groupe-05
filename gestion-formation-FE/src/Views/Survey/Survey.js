import React, {useContext, useEffect, useRef, useState} from "react";
import Container from "../../Components/Container/Container";
import Question from "../../Components/Question/Question";
import {AuthContext} from "../../Context/AuthContext";
import axios from "axios";
import Proposal from "../../Components/Proposal/Proposal";

export default function Survey(){

    const [question, setQuestion] = useState(null)
    const [proposal, setProposal] = useState([])
    const [chooseProposal, setChooseProposal] = useState(null)
    const [difficulty, setDifficulty] = useState(1)
    let trueProposal = null;
    const [start, setStart] = useState(false)
    const isMounted = useRef(false)
    const [isFirst, setIsFirst] = useState(true);
    const {user, addUser} = useContext(AuthContext)
    const [info, setInfo] = useState("")

    const chooseQuestion = async () => {
        setQuestion(await axios.get('http://localhost:8080/gestion-formation-BE/api/questionnary/getQuestion/' + user.userId + "/" + difficulty)
            .then(async (res) => {
                console.log(res)
                if(!res["data"].message){
                    setProposal(await axios.get('http://localhost:8080/gestion-formation-BE/api/proposal/read/' + res["data"].idQuestion)
                        .then((res) => {
                            console.log(res["data"])
                            return res["data"];
                        }))
                    return res["data"];
                }else{
                    console.log("ddd")
                    setInfo("Fin du questionnaire, une formation vous sera bientôt proposée !")
                }
            }))
    }

    useEffect(() => {
        if(isMounted.current && !isFirst){
            chooseQuestion().then(r =>  setStart(true))

        }else{
            isMounted.current = true;
            setIsFirst(false);
        }
    },[start])

    const changeProposal = e => {
        let proposal = e.target.id;
        setChooseProposal(proposal)
    }

    const respondQuestion = async () => {
        await axios.get('http://localhost:8080/gestion-formation-BE/api/questionnary/response/' + user.userId + '/qId/' + question.idQuestion + '/rId/' + chooseProposal)
    }

    const handleSubmit = e => {
        console.log("submit")
        console.log(chooseProposal)
        setStart(false)
        respondQuestion()
            .then(r => chooseQuestion()
                .then(r => setStart(true)))
    }

    return (
        <>
            <Container>
                <h1>{info}</h1>
                <br/>
                {
                    start === false ?
                        <button className={"btn btn-primary btn-lg"} onClick={() => setStart(true)}>Commencer</button>
                    :

                        <Question question={question}>
                            <div className="proposals">
                                {
                                    proposal.map((item) => {
                                        if(item.state === 1){
                                            trueProposal = item;
                                        }

                                        return(
                                            <Proposal className={parseInt(item.idProposal) === parseInt(chooseProposal) ? "activeProposal proposal" : "proposal"} key={item.idProposal} proposal={item} onClick={changeProposal}/>
                                        )
                                    })
                                }
                            </div>
                            {
                                chooseProposal !== null && <button className={"btn btn-primary"} onClick={handleSubmit}>Valider</button>
                            }
                        </Question>
                }

            </Container>
        </>
    );
}