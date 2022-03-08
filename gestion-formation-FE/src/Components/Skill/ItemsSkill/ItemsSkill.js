import React from "react";
import {Table} from "react-bootstrap";

export default function ItemsSkill(props){
    return (
        <>
            <Table striped bordered hover variant={"dark"} responsive>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Question</th>
                    <th>Sous domaine</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr >
                        <td key={item.idSkill}>
                            {item.idSkill}
                        </td>
                        <td>{item.name}</td>
                        <td>{item.idSubdomain.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

        </>
    );

}