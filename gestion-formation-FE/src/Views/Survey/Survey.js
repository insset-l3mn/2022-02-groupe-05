import React, {useContext, useEffect, useRef, useState} from "react";
import Container from "../../Components/Container/Container";
import Question from "../../Components/Question/Question";
import {AuthContext} from "../../Context/AuthContext";
import axios from "axios";
import Proposal from "../../Components/Proposal/Proposal";
import {logDOM} from "@testing-library/react";
import transitionEndListener from "react-bootstrap/transitionEndListener";

export default function Survey(){

    const [question, setQuestion] = useState(null)
    const [proposal, setProposal] = useState([])
    const [chooseProposal, setChooseProposal] = useState(null)
    const [difficulty, setDifficulty] = useState(1)
    let trueProposal = null;
    const [start, setStart] = useState(false)
    const isMounted = useRef(false)
    const {user, addUser} = useContext(AuthContext)

    const chooseQuestion = async () => {
        setQuestion(await axios.get('http://localhost:8080/gestion-formation-BE/api/questionnary/getQuestion/' + user.userId + "/" + difficulty)
            .then(async (res) => {
                console.log(res)
                setProposal(await axios.get('http://localhost:8080/gestion-formation-BE/api/proposal/read/' + res["data"].idQuestion)
                    .then((res) => {
                        return res["data"];
                    }))
                return res["data"];
            }))
    }

    useEffect(() => {
        if(isMounted.current){
            chooseQuestion().then(r =>  setStart(true))

        }else{
            isMounted.current = true;
        }
    },[start])

    const changeProposal = e => {
        let proposal = e.target.id;
        setChooseProposal(proposal)
    }

    const handleSubmit = e => {
        console.log("submit")

        if(parseInt(chooseProposal) === parseInt(trueProposal.idProposal)){
            setStart(false)

            chooseQuestion().then(r => setStart(true))
        }
    }

    return (
        <>
            <Container>
                <h1>Questionnaire d'évalutation</h1>
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
                                            <Proposal className={parseInt(item.idProposal) === parseInt(chooseProposal) ? "active proposal" : "proposal"} key={item.idProposal} proposal={item} onClick={changeProposal}/>
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