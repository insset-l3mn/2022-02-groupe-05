import React, {useEffect, useState} from "react";
import axios from "axios";
import ReactPaginate from "react-paginate";
import Items from "../../Items/Items";
import PaginatedItems from "../../PaginatedItems/PaginatedItems";

export default function ListQuestion(props){

    const [questions, setQuestions] = useState([]);

    useEffect( () => {

        axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/99/0")
            .then((response) => {
                setQuestions(response["data"])
            });

    }, [])

    return (
        <>
            <h1 className={"text-center"}>Les questions</h1>
            <br/>

            <PaginatedItems
                itemsPerPage={3}
                value={questions}/>
        </>
    );

}