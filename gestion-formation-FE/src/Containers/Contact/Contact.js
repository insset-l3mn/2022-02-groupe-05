import React, {useContext, useEffect} from "react";
import './Contact.css'
import {AuthContext} from "../../Context/AuthContext";
import {Navigate} from "react-router-dom";

export default function Contact(){

	const {user, setUser} = useContext(AuthContext)

	console.log(user);

    return (
        <>

			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					<h2>Contact</h2>

				</div>
			</div>
		</>
    );

}