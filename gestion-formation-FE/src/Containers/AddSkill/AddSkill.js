import React, {useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import axios from "axios";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";

export default function AddSkill(props){

	const [error, setError] = useState(false);
	const [success, setSuccess] = useState(false);

	const [skill, setSkill] = useState({
		name:"",
		weight:"",
		idUser:1
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
		params.append("idUser", skill.idUser)

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
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
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
				</div>
			</div>
		</>
	);

}