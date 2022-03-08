import React from "react";
import {Table} from "react-bootstrap";

export default function ItemsQuestions(props){
    return (
        <>
            <Table striped bordered hover variant={"dark"} responsive>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Question</th>
                    <th>Compétence</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr key={item.idQuestion}>
                        <td>
                            {item.idQuestion}
                        </td>
                        <td>{item.contents}</td>
                        <td>{item.idSkill.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

        </>
    );

}