import React, {useEffect, useRef, useState} from "react";
import {Button, Modal, Table} from "react-bootstrap";
import ModalView from "../../../../../ModalView/ModalView";
import axios from "axios";

export default function ItemsSkill(props){

    const [skill, setSkill] = useState(null)
    const modal = useRef();

    const changeSkill = (s) => {
        setSkill(s)
    }

    const handleClick = (e) => {
        e.preventDefault()
        axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/1")
            .then((response) => {
                changeSkill(response["data"])
                console.log(response)
            })
    }

    const closeModal = () => {
        modal.current.handleClose()
        changeSkill(null)
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
                    <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={closeModal}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={closeModal}>
                            Save Changes
                        </Button>
                    </Modal.Footer>
                </ModalView>
            }

        </>
    );

}