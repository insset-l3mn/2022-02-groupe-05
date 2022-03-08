import React, {useEffect, useState} from "react";
import axios from "axios";
import PaginatedItems from "../../PaginatedItems/PaginatedItems";

export default function ListSkill(props){

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

            {isLoad && <PaginatedItems
                itemsPerPage={10}
                value={skills}/>}

        </>
    );

}