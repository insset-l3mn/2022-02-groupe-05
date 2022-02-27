import React, {useState} from "react";
import './Question.css'
import Reponse from "../Reponse/Reponse";
import {v4 as uuidv4} from 'uuid';

export default function Question(){

    const [question, setQuestion] = useState("Question");

        const reponses =
        [
            1,2,3,4
        ];

    return (
        <>
            <div className="card text-center text-dark">
                <div className="card-header">
                    {question}
                </div>

                {reponses.map(item => {
                    return(
                        <Reponse key={uuidv4()} content={item}/>
                    );
                })}

                <div className="card-footer text-muted">
                    <button className={"btn btn-primary"}>Valider</button>
                </div>
            </div>
        </>
    );

}