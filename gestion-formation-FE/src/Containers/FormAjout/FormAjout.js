import React, {useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import InputSelect from "../../Components/InputSelect/InputSelect";
import axios from "axios";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";
import {v4 as uuidv4} from 'uuid';

export default function FormAjout(props){


    const [questionDomainName, setQuestionDomaineName] = useState([]);
    const [questionSkillName, setQuestionSkillName] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/gestion-formation-BE/api/domain/read/99/0")
            .then((response) => {
                setQuestionDomaineName(response["data"]);
            });
        axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/99/0")
            .then((response) => {
                setQuestionSkillName(response["data"]);
            });
    },[])



    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(false);
    const idUser = 1;

    const [domaine, setDomaine] = useState({
        nom:""
    });

    const [competence, setCompetence] = useState({
        nom:"",
        poids:""
    });

    const [question, setQuestion] = useState({
        contents:"",
        level:"",
        difficulty:"",
        domainName:"",
        skillName:"",
        right_answer:"",
        wrong_answer_1:"",
        wrong_answer_2:"",
        wrong_answer_3:""
    });

    useEffect(() => {
        setSuccess(false)
        setError(false)
    },[props.type])

    const handleSubmitQuestion = e => {
        e.preventDefault();
        console.log(question)
        const url = "http://localhost:8080/gestion-formation-BE/api/question/add"

        const params = new URLSearchParams()
        params.append('level', question.level)
        params.append('difficulty', question.difficulty)
        params.append('contents', question.contents)
        params.append('domainName', question.domainName)
        params.append('skillName', question.skillName)
        params.append('right_answer', question.right_answer)
        params.append('wrong_answer_1', question.wrong_answer_1)
        params.append('wrong_answer_2', question.wrong_answer_2)
        params.append('wrong_answer_3', question.wrong_answer_3)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])

            })
            .catch((error) => {
                console.log(error)
            })

    }


    const handleSubmitDomaine = e => {

        e.preventDefault()
        const url = "http://localhost:8080/gestion-formation-BE/api/domain/add"

        const params = new URLSearchParams()
        params.append('denominate', domaine.nom)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])

            })
            .catch((error) => {
                console.log(error)
            })

    }


    const handleSubmitCompetence = e => {
        setSuccess(false)
        setError(false)
        e.preventDefault()
        const url = "http://localhost:8080/gestion-formation-BE/api/skill/add"

        const params = new URLSearchParams()
        params.append("name", competence.nom)
        params.append("weight", competence.poids)
        params.append("idUser", idUser)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])

            })
            .catch((error) => {
                console.log(error)
            })

    }


    const onChangeInputQuestion = e => {
        const {name, value} = e.target;
        setQuestion(prevState => ({
            ...prevState,
            [name]: value
        }));
    }


    const onChangeInputDomaine = e => {
        const {name, value} = e.target;
        setDomaine(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const onChangeInputCompetence = e => {
        const {name, value} = e.target;
        setCompetence(prevState => ({
            ...prevState,
            [name]: value
        }));
    }




    return (
        <>
            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
			        <h1>Ajouter {props.type === "question" || props.type === "compétence" ? "une" : "un"} {props.type}</h1>
                    <br/>
                    {error && <Error message={error}/>}
                    {success && <Success message={success}/>}

                    <Form labelButton={"Ajouter"} onSubmit={props.type === "question" ? handleSubmitQuestion : props.type === "compétence" ? handleSubmitCompetence : handleSubmitDomaine}>
                        {/* Affichage formulaire pour l'ajout d'un domaine */}
                        {props.type === "domaine" ? (
                            <InputFloating id="floatingInputDomaine"
                                           type="text"
                                           labelContent={"Nom du domaine"}
                                           placeholder={"Nom du domaine"}
                                           name={"nom"}
                                           onChange={onChangeInputDomaine}/>

                            ) : props.type === "compétence" ? (
                                <>{/* Affichage formulaire pour l'ajout d'une compétence */}
                                    <InputFloating id="floatingInputCompetenceNom"
                                                   type="text"
                                                   name={"nom"}
                                                   labelContent={"Nom de la compétence"}
                                                   placeholder={"Nom de la compétence"}
                                                   onChange={onChangeInputCompetence}/>
                                    <InputFloating id="floatingInputCompetencePoids"
                                                   type="number"
                                                   name={"poids"}
                                                   labelContent={"Poids de la compétence"}
                                                   placeholder={"Poids de la compétence"}
                                                   onChange={onChangeInputCompetence}/>
                                </>
                            ) : (
                                <>{/* Affichage formulaire pour l'ajout d'une question */}
                                    <InputFloating id="floatingInputQuestionContenu"
                                                   type="text"
                                                   name={"contents"}
                                                   labelContent={"Contenu"}
                                                   placeholder={"Contenu"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputFloating id="floatingInputQuestionNiveau"
                                                   type="number"
                                                   name={"level"}
                                                   labelContent={"Niveau"}
                                                   placeholder={"Niveau"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputFloating id="floatingInputQuestionDifficulte"
                                                   type="text"
                                                   name={"difficulty"}
                                                   labelContent={"Difficulté"}
                                                   placeholder={"Difficulté"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputSelect id="floatingInputQuestionDomaine"
                                                 type="select"
                                                 name={"domainName"}
                                                 labelContent={"Domaine"}
                                                 placeholder={"Domaine"}
                                                 onChange={e => setQuestion(prevState => ({
                                                     ...prevState,
                                                     domainName: e.target.value
                                                 }))}>
                                        {questionDomainName.map((item) => <option value={item.denominate}>{item.denominate}</option>)}
                                    </InputSelect>

                                    <InputSelect id="floatingInputQuestionSkill"
                                                 type="select"
                                                 name={"skillName"}
                                                 labelContent={"Compétence"}
                                                 placeholder={"Compétence"}
                                                 onChange={e => setQuestion(prevState => ({
                                                     ...prevState,
                                                     skillName: e.target.value
                                                 }))}>
                                        {questionSkillName.map((item) => <option value={item.name}>{item.name}</option>)}
                                    </InputSelect>
                                    <div className="row">
                                        <div className="col">
                                            <InputFloating id="floatingInputReponse1"
                                                           type="text"
                                                           name={"right_answer"}
                                                           labelContent={"Bonne réponse"}
                                                           placeholder={"Bonne réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                            <InputFloating id="floatingInputReponse2"
                                                           type="text"
                                                           name={"wrong_answer_1"}
                                                           labelContent={"Mauvaise réponse"}
                                                           placeholder={"Mauvaise réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                        </div>
                                        <div className="col">
                                            <InputFloating id="floatingInputReponse3"
                                                           type="text"
                                                           name={"wrong_answer_2"}
                                                           labelContent={"Mauvaise réponse"}
                                                           placeholder={"Mauvaise réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                            <InputFloating id="floatingInputReponse4"
                                                           type="text"
                                                           name={"wrong_answer_3"}
                                                           labelContent={"Mauvaise réponse"}
                                                           placeholder={"Mauvaise réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                        </div>
                                    </div>

                                </>
                            )

                        }

                    </Form>
                </div>
            </div>
        </>
    );

}