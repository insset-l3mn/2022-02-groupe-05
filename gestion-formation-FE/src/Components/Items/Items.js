import React from "react";
import {Table} from "react-bootstrap";

export default function Items(props){

    return (
        <>
            <Table striped bordered hover variant={"dark"} responsive>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Question</th>
                </tr>
                </thead>
                <tbody>
                {props.currentItems && props.currentItems.map((item) => (
                    <tr >
                        <td key={item.id}>

                            {item.idQuestion}
                        </td>
                        <td>{item.contents}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

        </>
    );

}