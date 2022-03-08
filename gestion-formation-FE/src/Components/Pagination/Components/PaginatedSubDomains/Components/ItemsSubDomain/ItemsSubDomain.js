import React from "react";
import {Table} from "react-bootstrap";

export default function ItemsSubDomain(props){
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
                            {item.idSubdomain}
                        </td>
                        <td>{item.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

        </>
    );

}