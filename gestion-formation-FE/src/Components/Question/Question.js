import React, {useState} from "react";
import './Question.css'
import Reponse from "../Reponse/Reponse";
import {v4 as uuidv4} from 'uuid';

export default function Question(props){

        const reponses =
        [
            1,2,3,4
        ];

    return (
        <>
            <div className="card text-center text-dark">
                <div className="card-header">
                    {props.question}
                </div>

                {reponses.map(item => {
                    return(
                        <Reponse key={uuidv4()} content={item}/>
                    );
                })}

                {props.children}

            </div>
        </>
    );

}