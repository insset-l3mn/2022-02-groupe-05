import React, {useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import InputSelect from "../../Components/InputSelect/InputSelect";
import axios from "axios";
import formurlencoded from 'form-urlencoded';
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";

export default function FormAjout(props){

    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(false);

    const [domaine, setDomaine] = useState({
        nom:""
    });

    const [competence, setCompetence] = useState({
        nom:"",
        poids:""
    });

    const [question, setQuestion] = useState({
        contenu:"",
        niveau:"",
        difficulte:"",
        domaine:"",
        bonne_reponse:"",
        mauvaise_reponse_1:"",
        mauvaise_reponse_2:"",
        mauvaise_reponse_3:""
    });

    const handleSubmitQuestion = e => {
        e.preventDefault();
        console.log(question)
    }


    const handleSubmitDomaine = e => {
        setSuccess(false)
        setError(false)
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
        e.preventDefault();

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
                                                   labelContent={"Nom de la compétence"}
                                                   placeholder={"Nom de la compétence"}
                                                   onChange={""}/>
                                    <InputFloating id="floatingInputCompetencePoids"
                                                   type="number"
                                                   labelContent={"Poids de la compétence"}
                                                   placeholder={"Poids de la compétence"}
                                                   onChange={""}/>
                                </>
                            ) : (
                                <>{/* Affichage formulaire pour l'ajout d'une question */}
                                    <InputFloating id="floatingInputQuestionContenu"
                                                   type="text"
                                                   name={"contenu"}
                                                   labelContent={"Contenu"}
                                                   placeholder={"Contenu"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputFloating id="floatingInputQuestionNiveau"
                                                   type="number"
                                                   name={"niveau"}
                                                   labelContent={"Niveau"}
                                                   placeholder={"Niveau"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputFloating id="floatingInputQuestionDifficulte"
                                                   type="text"
                                                   name={"difficulte"}
                                                   labelContent={"Difficulté"}
                                                   placeholder={"Difficulté"}
                                                   onChange={onChangeInputQuestion}/>

                                    <InputSelect id="floatingInputQuestionDomaine"
                                                 type="select"
                                                 name={"domaine"}
                                                 labelContent={"Domaine"}
                                                 placeholder={"Domaine"}
                                                 onChange={e => setQuestion(prevState => ({
                                                     ...prevState,
                                                     domaine: e.target.value
                                                 }))}/>

                                    <div className="row">
                                        <div className="col">
                                            <InputFloating id="floatingInputReponse1"
                                                           type="text"
                                                           name={"bonne_reponse"}
                                                           labelContent={"Bonne réponse"}
                                                           placeholder={"Bonne réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                            <InputFloating id="floatingInputReponse2"
                                                           type="text"
                                                           name={"mauvaise_reponse_1"}
                                                           labelContent={"Mauvaise réponse"}
                                                           placeholder={"Mauvaise réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                        </div>
                                        <div className="col">
                                            <InputFloating id="floatingInputReponse3"
                                                           type="text"
                                                           name={"mauvaise_reponse_2"}
                                                           labelContent={"Mauvaise réponse"}
                                                           placeholder={"Mauvaise réponse"}
                                                           onChange={onChangeInputQuestion}/>
                                            <InputFloating id="floatingInputReponse4"
                                                           type="text"
                                                           name={"mauvaise_reponse_3"}
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