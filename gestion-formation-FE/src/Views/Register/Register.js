import React, {useState} from "react";
import './Register.css'
import Form from "../../Components/Form/Form";
import axios from "axios";
import InputFloating from "../../Components/Form/Components/InputFloating/InputFloating";
import Container from "../../Components/Container/Container";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";

export default function Register(props){


	const [email, setEmail] = useState("")
	const [password, setPassword] = useState("")
	const [passwordConfirm, setPasswordConfirm] = useState("")
	const [username, setUsername] = useState("")
	const [error, setError] = useState(false);
	const [success, setSuccess] = useState(false);



	const handleSubmit = async e => {
		e.preventDefault();
		setError(false);
		setSuccess(false);

		const params = new URLSearchParams()
		params.append('username', username)
		params.append('password', password)
		params.append('email', email)
		params.append('confirmation_password', passwordConfirm)

		const config = {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		axios.post("http://localhost:8080/gestion-formation-BE/api/user/register", params, config)
			.then((response) => {
				if(response["data"].hasOwnProperty("type")){
					if(response["data"].type === "error"){
						setError(response["data"].message)
					}else{
						setSuccess(response["data"].message)
					}
				}
			})

	}

    return (
        <>
			<Container>
				<Form labelButton={"S'inscrire"} onSubmit={handleSubmit}>
					<h1 className="h3 mb-3 fw-normal">Inscription</h1>

					{error && <Error message={error}/>}
					{success && <Success message={success}/>}

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