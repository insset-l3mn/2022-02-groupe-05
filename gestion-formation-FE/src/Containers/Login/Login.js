import React, {useState, useContext} from "react";
import './Login.css';
import Form from "../../Components/Form/Form";
import axios from "axios";
import {Navigate} from "react-router-dom";
import {AuthContext} from "../../Context/AuthContext";
import InputFloating from "../../Components/InputFloating/InputFloating";
import Error from "../../Components/Error/Error";

export default function Login(){

	const [email, setEmail] = useState()
	const [password, setPassword] = useState()
	const {addUser, user} = useContext(AuthContext)
	const [error, setError] = useState(false);

	const handleSubmit = async e => {
		e.preventDefault();
		axios.get("http://localhost:8080/gestion-formation-BE/api/user/login/"+email+"/"+password).then((response) => {
			if(!response["data"].hasOwnProperty("type")){
				addUser(response["data"]);
				//console.log(response)
			}else{
				//console.log("Erreur de login")
				setError(response["data"]["message"])
			}

		});

	}

	return (
		<>
			{user != null && <Navigate to={"/"}/>}
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<Form labelButton={"Connexion"} onSubmit={handleSubmit}>

						<h1 className="h3 mb-3 fw-normal">Connexion</h1>

						{/*<p>{user!=null ? "Connecté" : "Hors ligne"}</p>*/}

						{error && <Error message={error}/>}

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