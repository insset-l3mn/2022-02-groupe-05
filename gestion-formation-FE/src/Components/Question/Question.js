import React, {useEffect, useState} from "react";
import "./question.css"

export default function Question(props){
    console.log(props.skillName)
    return (
        <>
            {
                props.question ?

                    <>

                        <div className="question">
                            <div className="question-content">
                                <h3>{props.question.contents}</h3>

                            </div>
                            {props.children}
                        </div>

                    </>

                    :

                    <p>Chargement de la question</p>
            }
        </>
    );

}