import React, {useState, useContext} from "react";
import './Login.css';
import InputFloating from "../../Components/InputFloating/InputFloating";
import Form from "../../Components/Form/Form";
import axios from "axios";
import {LoginContext} from "../../Context/LoginContext";

export default function Login(){

	const [email, setEmail] = useState()
	const [password, setPassword] = useState()
	const {addUser, user} = useContext(LoginContext)

	const handleSubmit = async e => {
		e.preventDefault();
		axios.get("http://localhost:8080/gestion-formation-BE/api/login/"+email+"/"+password).then((response) => {
			addUser(response["data"]);
			console.log(response)
		});

	}

    return (
        <>
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<Form labelButton={"Connexion"} onSubmit={handleSubmit}>

						<h1 className="h3 mb-3 fw-normal">Connexion</h1>

						<p>{user!=null ? "Connecté" : "Hors ligne"}</p>

						<InputFloating id="floatingInputEmail"
									   type="text"
									   placeholder={"email@exemple.com"}
									   labelContent={"Email"}
									   onChange={e => setEmail(e.target.value)}/>

						<InputFloating id="floatingInputPassword"
									   type="password"
									   placeholder={"Mot de passe"}
									   labelContent={"Mot de passe"}
									   onChange={e => setPassword(e.target.value)}/>
					</Form>

				</div>
			</div>
        </>
    );

}