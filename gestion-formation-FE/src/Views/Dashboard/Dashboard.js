import React, {useContext} from "react";
import {AuthContext} from "../../Context/AuthContext";
import {Col, Nav, Row, Tab, Tabs} from "react-bootstrap";
import AddSkill from "../Skill/AddSkill/AddSkill";
import AddQuestion from "../Question/AddQuestion/AddQuestion";
import ListQuestions from "../Question/ListQuestions/ListQuestions";
import AddSubDomain from "../SubDomain/AddSubDomain/AddSubDomain";
import ListSkills from "../Skill/ListSkills/ListSkills";
import ListSubDomains from "../SubDomain/ListSubDomains/ListSubDomains";

export default function Dashboard(props){

    const {user, addUser} = useContext(AuthContext)

    return (
        <>
            <br/>
            <div className={"container"}>
                <Tab.Container id="left-tabs-example" defaultActiveKey="listSubDomains">
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
                                    <Nav.Link eventKey="listSkills">Liste des compétences</Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="listSubDomains">Liste des sous domaines</Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="listQuestions">Liste des questions</Nav.Link>
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
                                <Tab.Pane eventKey="listSkills">
                                    <ListSkills/>
                                </Tab.Pane>
                                <Tab.Pane eventKey="listSubDomains">
                                    <ListSubDomains/>
                                </Tab.Pane>
                                <Tab.Pane eventKey="listQuestions">
                                    <ListQuestions/>
                                </Tab.Pane>
                            </Tab.Content>
                        </Col>
                    </Row>
                </Tab.Container>
            </div>
        </>
    );

}