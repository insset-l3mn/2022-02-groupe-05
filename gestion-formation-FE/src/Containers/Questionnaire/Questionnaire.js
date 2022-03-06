import React, {useState} from "react";
import "./Questionnaire.css"
import Container from "../../Components/Container/Container";

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
            <Container>
                <h1>Questionnaire d'évalutation</h1>

            </Container>
        </>
    );

}