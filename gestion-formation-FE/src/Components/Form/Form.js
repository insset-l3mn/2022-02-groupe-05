import React from "react";
import './Form.css'
import {Button} from "react-bootstrap";

export default function Form(props){

    return (
        <>
            <form onSubmit={props.onSubmit}>
                {props.children}
                <Button onClick={props.onSubmit}>{props.labelButton}</Button>
            </form>
        </>
    );

}