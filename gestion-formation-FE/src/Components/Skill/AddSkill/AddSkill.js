import React, {useContext, useEffect, useState} from "react";
import Form from "../../Form/Form";
import InputFloating from "../../InputFloating/InputFloating";
import axios from "axios";
import Error from "../../Error/Error";
import Success from "../../Success/Success";
import {AuthContext} from "../../../Context/AuthContext";
import InputSelect from "../../InputSelect/InputSelect";

export default function AddSkill(props){

	const [error, setError] = useState(false);
	const [success, setSuccess] = useState(false);
	const {user, addUser} = useContext(AuthContext)
	const [subDomainList, setSubDomainList] = useState([]);

	const [skill, setSkill] = useState({
		name:"",
		weight:"",
		subDomain:"",
		idUser:""
	});

	useEffect(() => {
		axios.get("http://localhost:8080/gestion-formation-BE/api/subdomain/read/99/0")
			.then((response) => {
				setSubDomainList(response["data"]);
			});

		setSuccess(false)
		setError(false)
	},[])

	const handleSubmit = e => {
		setSuccess(false)
		setError(false)
		e.preventDefault()
		const url = "http://localhost:8080/gestion-formation-BE/api/skill/add"

		const params = new URLSearchParams()
		params.append("name", skill.name)
		params.append("weight", skill.weight)
		params.append("subdomain", skill.subDomain)
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
			<h1 className={"text-center"}>Ajouter une compétence</h1>
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
				<InputSelect id="floatingInputSkillSubDomain"
							 type="select"
							 name={"subDomain"}
							 defaultValue={"DEFAULT"}
							 labelContent={"Sous domaine"}
							 placeholder={"Sous domaine"}
							 onChange={e => setSkill(prevState => ({
								 ...prevState,
								 subDomain: e.target.value
							 }))}>
					{subDomainList.map((item) => <option key={item.name} value={item.name}>{item.name}</option>)}
				</InputSelect>
			</Form>
		</>
	);

}