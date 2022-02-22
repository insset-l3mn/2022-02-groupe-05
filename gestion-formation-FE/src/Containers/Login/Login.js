import React, {useState} from "react";
import './Login.css';
import InputFloating from "../../Components/InputFloating/InputFloating";
import Form from "../../Components/Form/Form";
import PropTypes from "prop-types";

export default function Login({setToken}){

	const [email, setEmail] = useState()
	const [password, setPassword] = useState()


	const handleSubmit = async e => {
		e.preventDefault();
		const token = await loginUser({email,password});
		setToken(token);
	}

    return (
        <>
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<Form labelButton={"Connexion"} onSubmit={handleSubmit}>

						<h1 className="h3 mb-3 fw-normal">Connexion</h1>

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
					</Form>

				</div>
			</div>
        </>
    );

}

async function loginUser(credentials){
	return fetch('http://localhost:8080/login/email/password', {
		method: 'POST',
		headers:{
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(credentials)
	})
		.then(data => data.json())
}

/*Validation des props*/
Login.propTypes ={
	setToken: PropTypes.func.isRequired
}