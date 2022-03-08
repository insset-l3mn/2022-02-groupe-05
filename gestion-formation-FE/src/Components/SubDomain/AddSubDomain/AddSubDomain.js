import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../../Context/AuthContext";
import axios from "axios";
import Error from "../../Error/Error";
import Success from "../../Success/Success";
import Form from "../../Form/Form";
import InputFloating from "../../InputFloating/InputFloating";

export default function AddSubDomain(props){

    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(false);
    const {user, addUser} = useContext(AuthContext)

    const [subDomain, setSubDomain] = useState({
        name:"",
        userId:""
    });

    useEffect(() => {
        setSuccess(false)
        setError(false)
    },[])

    const handleSubmit = e => {
        setSuccess(false)
        setError(false)
        e.preventDefault()
        const url = "http://localhost:8080/gestion-formation-BE/api/subdomain/add"

        const params = new URLSearchParams()
        params.append("name", subDomain.name)
        params.append("idUser", user.userId)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }

        axios.post(url, params, config)
            .then((response) => {
                console.log(response)
                response["data"]["type"] === "error" ? setError(response["data"]["message"]) : setSuccess(response["data"]["message"])

            })
            .catch((error) => {
                console.log(error)
            })

    }


    const onChangeInput = e => {
        const {name, value} = e.target;
        setSubDomain(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    return (
        <>
            <h1 className={"text-center"}>Ajouter un sous domaine</h1>
            <br/>

            {error && <Error message={error}/>}
            {success && <Success message={success}/>}

            <Form labelButton={"Ajouter"} onSubmit={handleSubmit}>

                <InputFloating id="floatingInputSubDomainNom"
                               type="text"
                               name={"name"}
                               labelContent={"Nom du sous domaine"}
                               placeholder={"Nom du sous domaine"}
                               onChange={onChangeInput}/>

            </Form>
        </>
    );

}