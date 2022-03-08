import React, {useContext, useEffect} from "react";
import './Contact.css'
import {AuthContext} from "../../Context/AuthContext";
import {Navigate, useLocation} from "react-router-dom";
import Container from "../../Components/Container/Container";

export default function Contact(props){

	const {user, setUser} = useContext(AuthContext)
	const location = useLocation()
    return (
        <>
			<Container>
				<h2>Contact</h2>
			</Container>
		</>
    );

}