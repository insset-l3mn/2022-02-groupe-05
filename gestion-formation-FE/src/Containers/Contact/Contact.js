import React, {useContext} from "react";
import './Contact.css'
import {LoginContext} from "../../Context/LoginContext";

export default function Contact(){

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