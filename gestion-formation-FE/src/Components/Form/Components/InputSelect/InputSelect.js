import React, {useEffect, useState} from "react";
import './InputSelect.css'

export default function InputSelect(props){


    return (
        <>
            <div className="form-floating">
                <select className="form-select" id="floatingSelect" onChange={props.onChange} name={props.name} defaultValue={props.defaultValue}>
                    <option value={"DEFAULT"}>...</option>
                    {props.children}
                </select>
                <label htmlFor="floatingSelect" style={{color:"black"}}>Choisir un {props.labelContent}</label>
            </div>
            <br/>
        </>
    );

}