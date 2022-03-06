import React, {useState} from "react";
import './Register.css'
import Form from "../../Components/Form/Form";
import axios from "axios";
import InputFloating from "../../Components/InputFloating/InputFloating";
import Container from "../../Components/Container/Container";

export default function Register(props){


	const [email, setEmail] = useState("")
	const [password, setPassword] = useState("")
	const [passwordConfirm, setPasswordConfirm] = useState("")
	const [username, setUsername] = useState("")

	const handleSubmit = async e => {
		e.preventDefault();

		(password === passwordConfirm && password.length >= 5) ? console.log("Pass ok") : console.log("Pass error")

		axios.get("http://localhost:8080/gestion-formation-BE/api/user/register/" + username + "/"+email+"/"+password)
			.then((response) => {
				console.log(response)
		});
	}

    return (
        <>
			<Container>
				<Form labelButton={"S'inscrire"} onSubmit={handleSubmit}>
					<h1 className="h3 mb-3 fw-normal">Inscription</h1>

					<InputFloating id="floatingInputFirstName"
								   type="text"
								   placeholder={"Gotaga"}
								   labelContent={"Nom d'utilisateur"}
								   onChange={e => setUsername(e.target.value)}/>

					<InputFloating id="floatingInputEmail"
								   type="email"
								   placeholder={"email@exemple.com"}
								   labelContent={"Email"}
								   onChange={e => setEmail(e.target.value)}/>

					<InputFloating id="floatingInputPassword"
								   type="password"
								   placeholder={"Mot de passe"}
								   labelContent={"Mot de passe"}
								   onChange={e => setPassword(e.target.value)}/>

					<InputFloating id="floatingInputPasswordConfirm"
								   type="password"
								   placeholder={"Confirmez votre mot de passe"}
								   labelContent={"Confirmez votre mot de passe"}
								   onChange={e => setPasswordConfirm(e.target.value)}/>

				</Form>
			</Container>
        </>
    );

}