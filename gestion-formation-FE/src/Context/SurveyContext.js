import React, {createContext, useContext, useEffect, useState} from "react";
import axios from "axios";
import {AuthContext} from "./AuthContext";

export const SurveyContext = createContext();

const SurveyContextProvider = props => {
	const [survey, setSurvey] = useState(false);
	const {user, addUser} = useContext(AuthContext)

	const changeSurvey = (s) =>{
		setSurvey(s);
	}
	console.log(user)

	useEffect(async () => {
		survey === false && await axios.get('http://localhost:8080/gestion-formation-BE/api/questionnary/getQuestion/' + user.userId + "/1")
			.then(res => {
				if(!res["data"].message) {
					changeSurvey(true)
				}
			})
	},[user])

	return(
		<SurveyContext.Provider value={{survey, setSurvey}}>
			{props.children}
		</SurveyContext.Provider>
	);
}

export default SurveyContextProvider;