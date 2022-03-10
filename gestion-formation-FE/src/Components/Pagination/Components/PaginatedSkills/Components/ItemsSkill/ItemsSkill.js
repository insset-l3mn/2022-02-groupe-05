import React, {useContext, useEffect, useRef, useState} from "react";
import {Button, Form, Modal, Table} from "react-bootstrap";
import ModalView from "../../../../../ModalView/ModalView";
import axios from "axios";
import InputFloating from "../../../../../Form/Components/InputFloating/InputFloating";
import Error from "../../../../../Error/Error";
import Success from "../../../../../Success/Success";
import {AuthContext} from "../../../../../../Context/AuthContext";
import {createBrowserHistory} from "history";

export default function ItemsSkill(props){

    const [error, setError] = useState(false)
    const [success, setSuccess] = useState(false)
    const [skill, setSkill] = useState(null)
    const modal = useRef()
    const {user, addUser} = useContext(AuthContext)
    const history = createBrowserHistory()


    const changeSkill = (s) => {
        setSkill(s)
    }

    const handleClick = (e) => {
        e.preventDefault()
        axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/"+e.target.name)
            .then((response) => {
                changeSkill(response["data"])
                console.log(response)
            })
    }

    const closeModal = () => {
        modal.current.handleClose()
        changeSkill(null)
        setError(null)
        setSuccess(null)
    }

    const updateItem = () => {
        setSuccess(false)
        setError(false)
        const url = "http://localhost:8080/gestion-formation-BE/api/skill/update"

        const params = new URLSearchParams()
        params.append("idSkill", skill.idSkill)
        params.append("name", skill.name)
        params.append("weight", skill.weight)
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
        setSkill(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const deleteItem = async () => {
        console.log(skill)
        await axios.get("http://localhost:8080/gestion-formation-BE/api/skill/remove/" + skill.idSkill + "/" + user.userId)
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
                    <th>Compétences</th>
                    <th>Sous domaine</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr key={item.idSkill}>
                        <td>
                            <a href="#" className="link-primary" name={item.idSkill} onClick={handleClick}>{item.idSkill}</a>
                        </td>
                        <td>{item.name}</td>
                        <td>{item.idSubdomain.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {skill !== null &&
                <ModalView ref={modal}
                           centered
                           size={"lg"}>
                    <Modal.Header>
                        <Modal.Title>{skill.name}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <InputFloating id="floatingInputName"
                                           type="text"
                                           name={"name"}
                                           value={skill.name}
                                           placeholder={"Nom de la compétence"}
                                           labelContent={"Nom de la compétence"}
                                           onChange={onChangeInput}/>
                            <InputFloating id="floatingInputWeight"
                                           type="number"
                                           name={"weight"}
                                           value={skill.weight}
                                           placeholder={"Poids de la compétence"}
                                           labelContent={"Poids de la compétence"}
                                           onChange={onChangeInput}/>
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