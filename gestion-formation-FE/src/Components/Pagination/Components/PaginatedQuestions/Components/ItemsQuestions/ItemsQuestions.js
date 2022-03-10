import React, {useContext, useRef, useState} from "react";
import {Button, Form, Modal, Table} from "react-bootstrap";
import {AuthContext} from "../../../../../../Context/AuthContext";
import {createBrowserHistory} from "history";
import axios from "axios";
import ModalView from "../../../../../ModalView/ModalView";
import InputFloating from "../../../../../Form/Components/InputFloating/InputFloating";
import Error from "../../../../../Error/Error";
import Success from "../../../../../Success/Success";
import TextareaFloating from "../../../../../Form/Components/TextareaFloating/TextareaFloating";

export default function ItemsQuestions(props){

    const [error, setError] = useState(false)
    const [success, setSuccess] = useState(false)
    const [question, setQuestion] = useState(null)
    const modal = useRef()
    const {user, addUser} = useContext(AuthContext)
    const history = createBrowserHistory()
    const [proposals, setProposals] = useState({});


    const changeQuestion = (s) => {
        setQuestion(s)
    }

    const changeProposals = (s) => {
        setProposals(s)
    }

    const handleClick = (e) => {
        e.preventDefault()
        axios.get("http://localhost:8080/gestion-formation-BE/api/question/read/"+e.target.name)
            .then((response) => {
                changeQuestion(response["data"])
                console.log(response)

            })
        axios.get("http://localhost:8080/gestion-formation-BE/api/proposal/read/"+e.target.name)
            .then((response) => {
                changeProposals(response["data"])
                console.log(response)
            })
    }

    const closeModal = () => {
        modal.current.handleClose()
        changeQuestion(null)
        setError(null)
        setSuccess(null)
        setProposals(null)
    }

    const updateItem = () => {
        setSuccess(false)
        setError(false)
        const url = "http://localhost:8080/gestion-formation-BE/api/question/update"

        const params = new URLSearchParams()
        params.append("idQuestion", question.idQuestion)
        params.append("name", question.name)
        params.append("weight", question.weight)
        params.append("idUser", user.userId)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])
                if(response["data"]["type"] !== "error"){
                    history.push("/dashboard")
                    history.go()
                }
            })
            .catch((error) => {
                console.log(error)
            })

    }

    const onChangeInput = e => {
        const {name, value} = e.target;
        setQuestion(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const deleteItem = async () => {
        console.log(question)
        await axios.get("http://localhost:8080/gestion-formation-BE/api/question/remove/" + question.idQuestion + "/" + user.userId)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])
                if(response["data"]["type"] !== "error"){
                    history.push("/dashboard")
                    history.go()
                }
            })
            .catch((error) => {
                console.log(error)
            })

    }

    return (
        <>
            <Table striped bordered hover variant={"dark"} responsive>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Question</th>
                    <th>Compétence</th>
                    <th>Difficulté</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr key={item.idQuestion}>
                        <td>
                            <a href="#" className="link-primary" name={item.idQuestion} onClick={handleClick}>{item.idQuestion}</a>

                        </td>
                        <td>{item.contents}</td>
                        <td>{item.idSkill.name}</td>
                        <td>{item.difficulty}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {question !== null &&
                <ModalView ref={modal}
                           centered
                           size={"lg"}>
                    <Modal.Header>
                        <Modal.Title>{question.idQuestion}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <TextareaFloating id="floatingInputContents"
                                           name={"contents"}
                                           value={question.contents}
                                           placeholder={"Contenu de la question"}
                                           labelContent={"Contenu de la question"}
                                           onChange={onChangeInput}/>
                            <div className="row">
                                <div className="col">
                                    <InputFloating id="floatingInputDifficulty"
                                                   type="text"
                                                   name={"difficulty"}
                                                   value={question.difficulty}
                                                   placeholder={"Difficulté"}
                                                   labelContent={"Difficulté"}
                                                   onChange={onChangeInput}/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col">
                                    <InputFloating id="floatingInputProposal1"
                                                   type="text"
                                                   name={"difficulty"}
                                                   value={proposals[0].denominate}
                                                   placeholder={"Difficulté"}
                                                   labelContent={"Difficulté"}
                                                   onChange={onChangeInput}/>
                                    <InputFloating id="floatingInputProposal2"
                                                   type="text"
                                                   name={"difficulty"}
                                                   value={proposals[1].denominate}
                                                   placeholder={"Difficulté"}
                                                   labelContent={"Difficulté"}
                                                   onChange={onChangeInput}/>
                                </div>
                                <div className="col">
                                    <InputFloating id="floatingInputProposal3"
                                                   type="text"
                                                   name={"difficulty"}
                                                   value={proposals[2].denominate}
                                                   placeholder={"Difficulté"}
                                                   labelContent={"Difficulté"}
                                                   onChange={onChangeInput}/>
                                    <InputFloating id="floatingInputProposal4"
                                                   type="text"
                                                   name={"difficulty"}
                                                   value={proposals[3].denominate}
                                                   placeholder={"Difficulté"}
                                                   labelContent={"Difficulté"}
                                                   onChange={onChangeInput}/>
                                </div>
                            </div>

                        </Form>
                        {error && <Error message={error}/>}
                        {success && <Success message={success}/>}
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="danger" onClick={deleteItem}>
                            Supprimer
                        </Button>
                        <Button variant="secondary" onClick={closeModal}>
                            Fermer
                        </Button>
                        <Button variant="primary" onClick={updateItem}>
                            Sauvegarder
                        </Button>
                    </Modal.Footer>
                </ModalView>
            }
        </>
    );

}