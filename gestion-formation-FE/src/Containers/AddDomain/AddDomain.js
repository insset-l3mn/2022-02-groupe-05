import React, {useEffect, useState} from "react";
import Form from "../../Components/Form/Form";
import InputFloating from "../../Components/InputFloating/InputFloating";
import axios from "axios";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";

export default function AddDomain(props){


	const [error, setError] = useState(false);
	const [success, setSuccess] = useState(false);

	const [domain, setDomain] = useState({
		denominate:""
	});

	useEffect(() => {
		setSuccess(false)
		setError(false)
	},[props.type])

	const handleSubmit = e => {

		e.preventDefault()
		const url = "http://localhost:8080/gestion-formation-BE/api/domain/add"

		const params = new URLSearchParams()
		params.append('denominate', domain.denominate)

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
		setDomain(prevState => ({
			...prevState,
			[name]: value
		}));
	}


	return (
		<>
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<h1>Ajouter un domaine</h1>
					<br/>

					{error && <Error message={error}/>}
					{success && <Success message={success}/>}

					<Form labelButton={"Ajouter"} onSubmit={handleSubmit}>

						<InputFloating id="floatingInputDomaine"
									   type="text"
									   labelContent={"Nom du domaine"}
									   placeholder={"Nom du domaine"}
									   name={"denominate"}
									   onChange={onChangeInput}/>

					</Form>
				</div>
			</div>
		</>
	);

}