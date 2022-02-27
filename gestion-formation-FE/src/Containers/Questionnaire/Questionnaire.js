import React, {useState} from "react";
import "./Questionnaire.css"
import Question from "../../Components/Question/Question";

export default function Questionnaire(){

    const [question, setQuestion] = useState(
        [
            {
                id:1,
                content:"question 1"
            },
            {
                id:2,
                content:"question 2"
            }
        ]

    );

    const [numberQuestion, setNumberQuestion] = useState(0);

    const submitResponse = () =>{
        if(numberQuestion < 19){
            setNumberQuestion(numberQuestion+1);
            console.log(numberQuestion)
        }else{
            console.log("Fin du questionnaire")
        }
    }

    return (
        <>
            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
                    <h1>Questionnaire d'évalutation</h1>
                    <Question question={question[numberQuestion].content}
                              id={question[numberQuestion].id}>

                        <div className="card-footer text-muted">
                            <button className={"btn btn-primary"}
                                    onClick={submitResponse}>Valider</button>
                        </div>

                    </Question>
                </div>
            </div>
        </>
    );

}