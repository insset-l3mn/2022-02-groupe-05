import React, {createContext, useContext, useEffect, useState} from "react";

export const LoginContext = createContext();

const LoginContextProvider = props => {
	const [user, setUser] = useState(null);

	const addUser = (u) =>{
		setUser(u);
		window.sessionStorage.setItem("user", u);
	}

	useEffect(() => {
		window.sessionStorage.getItem("user") != null && addUser(window.sessionStorage.getItem("user"));
	},[])

	return(
		<LoginContext.Provider value={{addUser, user}}>
			{props.children}
		</LoginContext.Provider>
	);
}

export default LoginContextProvider;