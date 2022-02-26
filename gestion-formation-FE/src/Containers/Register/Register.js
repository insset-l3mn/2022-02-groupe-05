import React, {useContext} from "react";
import './Register.css'
import InputFloating from "../../Components/InputFloating/InputFloating";
import Form from "../../Components/Form/Form";
import {LoginContext} from "../../Context/LoginContext";

export default function Register(){

	const {addUser, user} = useContext(LoginContext);

    return (
        <>
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<Form labelButton={"S'inscrire"}>
						<h1 className="h3 mb-3 fw-normal">Inscription</h1>
						<p>{user!=null ? "Connecté" : "Hors ligne"}</p>

						<div className="row g-2">

							<div className="col-md">
								<InputFloating id="floatingInputLastname"
											   type="text"
											   placeholder={"Doe"}
											   labelContent={"Nom"}/>
							</div>

							<div className="col-md">
								<InputFloating id="floatingInputFirstName"
											   type="text"
											   placeholder={"John"}
											   labelContent={"Prénom"}/>
							</div>

						</div>

						<InputFloating id="floatingInputEmail"
									   type="email"
									   placeholder={"email@exemple.com"}
									   labelContent={"Email"}/>

						<InputFloating id="floatingInputPassword"
									   type="password"
									   placeholder={"Mot de passe"}
									   labelContent={"Mot de passe"}/>

						<InputFloating id="floatingInputPasswordConfirm"
									   type="password"
									   placeholder={"Confirmez votre mot de passe"}
									   labelContent={"Confirmez votre mot de passe"}/>

					</Form>
				</div>
			</div>
        </>
    );

}