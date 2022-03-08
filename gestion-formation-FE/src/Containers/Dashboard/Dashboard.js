import React, {useContext} from "react";
import {AuthContext} from "../../Context/AuthContext";
import {Col, Nav, Row, Tab, Tabs} from "react-bootstrap";
import AddSkill from "../../Components/AddSkill/AddSkill";
import AddQuestion from "../../Components/Question/AddQuestion/AddQuestion";
import ListQuestion from "../../Components/Question/ListQuestion/ListQuestion";
import AddSubDomain from "../../Components/SubDomain/AddSubDomain/AddSubDomain";

export default function Dashboard(props){

    const {user, addUser} = useContext(AuthContext)

    return (
        <>
            <br/>
            <div className={"container"}>
                <Tab.Container id="left-tabs-example" defaultActiveKey="addSkill">
                    <Row>
                        <Col sm={3}>
                            <Nav variant="pills" className="flex-column">
                                <Nav.Item>
                                    <Nav.Link eventKey="addSkill">Ajout compétence</Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="addSubDomain">Ajout sous domaine</Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="addQuestion">Ajout question</Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="listQuestion">Liste des questions</Nav.Link>
                                </Nav.Item>
                            </Nav>
                        </Col>
                        <Col sm={8}>
                            <Tab.Content>
                                <Tab.Pane eventKey="addSkill">
                                    <AddSkill />
                                </Tab.Pane>
                                <Tab.Pane eventKey="addSubDomain">
                                    <AddSubDomain />
                                </Tab.Pane>
                                <Tab.Pane eventKey="addQuestion">
                                    <AddQuestion />
                                </Tab.Pane>
                                <Tab.Pane eventKey="listQuestion">

                                </Tab.Pane>
                            </Tab.Content>
                        </Col>
                    </Row>
                </Tab.Container>
            </div>
        </>
    );

}