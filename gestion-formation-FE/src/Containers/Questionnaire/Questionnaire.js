import React from "react";
import "./Questionnaire.css"
import Question from "../../Components/Question/Question";

export default function Questionnaire(props){

    return (
        <>
            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
                    <h1>Questionnaire d'évalutation</h1>
                    <Question/>
                </div>
            </div>
        </>
    );

}