import React, {useEffect, useState} from "react";
import axios from "axios";
import Pagination from "../../../Components/Pagination/Pagination";
import PaginatedSkills from "../../../Components/Pagination/Components/PaginatedSkills/PaginatedSkills";

export default function ListSkills(props){

    const [skills, setSkills] = useState([]);
    const [isLoad, setIsLoad] = useState(false);

    const addSkills = (q) => {
        setSkills(q)
    }

    useEffect( () => {
         axios.get("http://localhost:8080/gestion-formation-BE/api/skill/read/all")
            .then((response) => {
                addSkills(response["data"])
                setIsLoad(true)
            })

    },[])

    return (
        <>
            <h1 className={"text-center"}>Les compétences</h1>
            <br/>

            <Pagination>
                {isLoad && <PaginatedSkills
                    itemsPerPage={10}
                    value={skills}/>}
            </Pagination>



        </>
    );

}