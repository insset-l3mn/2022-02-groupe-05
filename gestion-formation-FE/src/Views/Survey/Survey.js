import React, {useState} from "react";
import Container from "../../Components/Container/Container";

export default function Survey(){

    const [question, setQuestion] = useState(null);
    const [difficulty, setDifficulty] = useState(0);

    const startSurvey = () => {
        console.log("Start questionnaire")
        
    }

    return (
        <>
            <Container>
                <h1>Questionnaire d'évalutation</h1>
                <br/>
                {
                    question === null ?
                        <button className={"btn btn-primary btn-lg"} onClick={() => startSurvey}>Commencer</button>
                    :
                    "heel"
                }

            </Container>
        </>
    );
}