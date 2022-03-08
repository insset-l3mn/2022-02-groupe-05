import React, {useEffect, useState} from "react";
import axios from "axios";
import PaginatedQuestions from "../../../Components/Pagination/Components/PaginatedQuestions/PaginatedQuestions";
import Pagination from "../../../Components/Pagination/Pagination";

export default function ListQuestions(props){

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

            <Pagination>
                {isLoad && <PaginatedQuestions
                    itemsPerPage={10}
                    value={questions}/>}
            </Pagination>

        </>
    );

}