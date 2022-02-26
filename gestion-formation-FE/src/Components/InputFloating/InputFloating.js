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
                    type={props.type}
                    className="form-control"
                    id={props.id}
                    value={props.value}
                    onChange={props.onChange}
                    placeholder={props.placeholder}/>
                <label htmlFor={props.id} className={"text-black"}>{props.labelContent}</label>
            </div>
            <br/>
        </>
    );

}