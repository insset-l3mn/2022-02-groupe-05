import React, {createContext, useContext, useEffect, useState} from "react";

export const AuthContext = createContext();

const AuthContextProvider = props => {
	const [user, setUser] = useState(null);

	const addUser = (u) =>{
		setUser(u);
		window.sessionStorage.setItem("user", u);
	}

	useEffect(() => {
		window.sessionStorage.getItem("user") != null && addUser(window.sessionStorage.getItem("user"));
	},[])

	return(
		<AuthContext.Provider value={{addUser, user}}>
			{props.children}
		</AuthContext.Provider>
	);
}

export default AuthContextProvider;