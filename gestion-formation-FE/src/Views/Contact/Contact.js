import React, {useContext, useEffect} from "react";
import './Contact.css'
import {AuthContext} from "../../Context/AuthContext";
import {Navigate} from "react-router-dom";
import Container from "../../Components/Container/Container";

export default function Contact(){

	const {user, setUser} = useContext(AuthContext)

    return (
        <>
			<Container>
				<h2>Contact</h2>
			</Container>
		</>
    );

}