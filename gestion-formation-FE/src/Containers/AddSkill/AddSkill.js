import React, {useContext, useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import axios from "axios";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";
import {AuthContext} from "../../Context/AuthContext";
import Container from "../../Components/Container/Container";

export default function AddSkill(props){

	const [error, setError] = useState(false);
	const [success, setSuccess] = useState(false);
	const {user, addUser} = useContext(AuthContext)

	const [skill, setSkill] = useState({
		name:"",
		weight:""
	});

	useEffect(() => {
		setSuccess(false)
		setError(false)
	},[props.type])

	const handleSubmit = e => {
		setSuccess(false)
		setError(false)
		e.preventDefault()
		const url = "http://localhost:8080/gestion-formation-BE/api/skill/add"

		const params = new URLSearchParams()
		params.append("name", skill.nom)
		params.append("weight", skill.poids)
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
		setSkill(prevState => ({
			...prevState,
			[name]: value
		}));
	}


	return (
		<>
			<Container>
				<h1>Ajouter une compétence</h1>
				<br/>

				{error && <Error message={error}/>}
				{success && <Success message={success}/>}

				<Form labelButton={"Ajouter"} onSubmit={handleSubmit}>

					<InputFloating id="floatingInputCompetenceNom"
								   type="text"
								   name={"name"}
								   labelContent={"Nom de la compétence"}
								   placeholder={"Nom de la compétence"}
								   onChange={onChangeInput}/>
					<InputFloating id="floatingInputCompetencePoids"
								   type="number"
								   name={"weight"}
								   labelContent={"Poids de la compétence"}
								   placeholder={"Poids de la compétence"}
								   onChange={onChangeInput}/>

				</Form>
			</Container>
		</>
	);

}