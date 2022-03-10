import React, {useContext, useRef, useState} from "react";
import {Button, Form, Modal, Table} from "react-bootstrap";
import ModalView from "../../../../../ModalView/ModalView";
import InputFloating from "../../../../../Form/Components/InputFloating/InputFloating";
import axios from "axios";
import Error from "../../../../../Error/Error";
import Success from "../../../../../Success/Success";
import {AuthContext} from "../../../../../../Context/AuthContext";
import {createBrowserHistory} from "history";

export default function ItemsSubDomain(props){

    const [error, setError] = useState(false)
    const [success, setSuccess] = useState(false)
    const [subDomain, setSubDomain] = useState(null)
    const modal = useRef();
    const {user, addUser} = useContext(AuthContext)
    const history = createBrowserHistory()

    const changeSubDomain = (s) => {
        setSubDomain(s)
    }

    const handleClick = (e) => {
        e.preventDefault()
        axios.get("http://localhost:8080/gestion-formation-BE/api/subdomain/read/"+e.target.name)
            .then((response) => {
                changeSubDomain(response["data"])
                console.log(response)
            })
    }

    const closeModal = () => {
        modal.current.handleClose()
        changeSubDomain(null)
        setError(null)
        setSuccess(null)
    }

    const updateItem = () => {
        setSuccess(false)
        setError(false)
        const url = "http://localhost:8080/gestion-formation-BE/api/subdomain/update"

        const params = new URLSearchParams()
        params.append("id", subDomain.idSubdomain)
        params.append("name", subDomain.name)
        params.append("userId", user.userId)

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
        setSubDomain(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const deleteItem = async () => {
        console.log("delete")
        await axios.get("http://localhost:8080/gestion-formation-BE/api/subdomain/remove/" + subDomain.idSubdomain + "/" + user.userId)
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
                    <th>Sous domaine</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr key={item.idSubdomain}>
                        <td>
                            <a href="#" className="link-primary" name={item.idSubdomain} onClick={handleClick}>{item.idSubdomain}</a>

                        </td>
                        <td>{item.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

            {subDomain !== null &&
                <ModalView ref={modal}
                           centered
                           size={"lg"}>
                    <Modal.Header>
                        <Modal.Title>{subDomain.name}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <InputFloating id="floatingInputName"
                                           type="text"
                                           name={"name"}
                                           value={subDomain.name}
                                           placeholder={"Nom du sous domaine"}
                                           labelContent={"Nom du sous domaine"}
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