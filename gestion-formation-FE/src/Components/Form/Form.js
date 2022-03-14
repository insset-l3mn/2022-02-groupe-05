import React from "react";
import './Form.css'
import {Button} from "react-bootstrap";

export default function Form(props){

    return (
        <>
            <form {...props}>
                {props.children}
                <Button onClick={props.onSubmit}>{props.labelButton}</Button>
            </form>
        </>
    );

}