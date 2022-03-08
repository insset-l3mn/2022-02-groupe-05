import React, {useEffect, useState} from "react";
import axios from "axios";
import Pagination from "../../../Components/Pagination/Pagination";
import PaginatedSubDomains from "../../../Components/Pagination/Components/PaginatedSubDomains/PaginatedSubDomains";

export default function ListSubDomains(props){

    const [subDomains, setSubDomains] = useState([]);
    const [isLoad, setIsLoad] = useState(false);

    const addSubDomains = (q) => {
        setSubDomains(q)
    }

    useEffect( () => {
         axios.get("http://localhost:8080/gestion-formation-BE/api/subdomain/read/99/0")
            .then((response) => {
                addSubDomains(response["data"])
                setIsLoad(true)
            })

    },[])

    return (
        <>
            <h1 className={"text-center"}>Les sous domaines</h1>
            <br/>

            <Pagination>
                {isLoad && <PaginatedSubDomains
                    itemsPerPage={10}
                    value={subDomains}/>}
            </Pagination>



        </>
    );

}