import React, {useState, useContext} from "react";
import './Login.css';
import Form from "../../Components/Form/Form";
import axios from "axios";
import {Navigate} from "react-router-dom";
import {AuthContext} from "../../Context/AuthContext";
import InputFloating from "../../Components/InputFloating/InputFloating";
import Error from "../../Components/Error/Error";

export default function Login(){

	const [username, setUsername] = useState()
	const [password, setPassword] = useState()
	const {user, addUser} = useContext(AuthContext)
	const [error, setError] = useState(false);

	const handleSubmit = async e => {
		e.preventDefault();

		const params = new URLSearchParams()
		params.append('username', username)
		params.append('password', password)

		const config = {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		axios.post("http://localhost:8080/gestion-formation-BE/api/user/login", params, config)
			.then((response) => {
				if(response["data"].hasOwnProperty("type")){
					setError(response["data"]["message"])
				}else{
					addUser(response["data"])
				}
			})

	}

	return (
		<>
			{user != null && <Navigate to={"/"}/>}

			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<Form labelButton={"Connexion"} onSubmit={handleSubmit}>

						<h1 className="h3 mb-3 fw-normal">Connexion</h1>

						{error && <Error message={error}/>}

						<InputFloating id="floatingInputUsername"
									   type="text"
									   placeholder={"Pseudo"}
									   labelContent={"Pseudo"}
									   onChange={e => setUsername(e.target.value)}/>

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