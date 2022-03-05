import React, {useEffect, useState} from "react";
import './InputSelect.css'
import axios from "axios";
import {v4 as uuidv4} from "uuid";

export default function InputSelect(props){

    const [domaineQuestion, setDomaineQuestion] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/gestion-formation-BE/api/domain/read/99/0")
            .then((response) => {
                setDomaineQuestion(response["data"]);
            });
    },[])


    return (
        <>
            <div className="form-floating">
                <select className="form-select" id="floatingSelect" onChange={props.onChange} name={props.name}>
                    <option value={"DEFAULT"} selected>...</option>
                    {domaineQuestion.map((item) => <option value={item.idDomain}>{item.denominate}</option>)}
                </select>
                <label htmlFor="floatingSelect" style={{color:"black"}}>Choisir un {props.labelContent}</label>
            </div>
            <br/>
        </>
    );

}