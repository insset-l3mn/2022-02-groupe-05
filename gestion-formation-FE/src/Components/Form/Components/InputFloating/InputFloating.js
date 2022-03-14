import React from "react";
import './InputFloating.css'

{/*
    props = {
        type,
        placeholder,
        id,
        labelContent
    }
*/}
export default function InputFloating(props){

    return (
        <>
            <div className="form-floating">
                <input
                    {...props}
                    className={"form-control " + props.className}/>
                <label htmlFor={props.id} className={"text-black"}>{props.labelContent}</label>
            </div>
            <br/>
        </>
    );

}