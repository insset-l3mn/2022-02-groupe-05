import React from "react";
import './Form.css'

export default function Form(props){

    return (
        <>
            <form onSubmit={props.onSubmit}>
                {props.children}
                <button className="w-100 btn btn-lg btn-primary" type="submit">{props.labelButton}</button>
            </form>
        </>
    );

}