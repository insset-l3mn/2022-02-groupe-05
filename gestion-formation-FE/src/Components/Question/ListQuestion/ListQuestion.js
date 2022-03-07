import React, {useEffect, useState} from "react";
import axios from "axios";
import PaginatedItems from "../../PaginatedItems/PaginatedItems";

export default function ListQuestion(props){

    const [questions, setQuestions] = useState([]);
    const [isLoad, setIsLoad] = useState(false);

    const addQuestions = (q) => {
        setQuestions(q)
    }

    useEffect( () => {
         axios.get("http://localhost:8080/gestion-formation-BE/api/question/read/99/0")
            .then((response) => {
                addQuestions(response["data"])
                setIsLoad(true)
            })

    },[])

    return (
        <>
            <h1 className={"text-center"}>Les questions</h1>
            <br/>

            {isLoad && <PaginatedItems
                itemsPerPage={10}
                value={questions}/>}

        </>
    );

}