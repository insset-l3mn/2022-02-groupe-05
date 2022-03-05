import React, {createContext, useContext, useEffect, useState} from "react";

export const AuthContext = createContext();

const AuthContextProvider = props => {
	const [user, setUser] = useState(null);

	const addUser = (u) =>{
		setUser(u);
		window.sessionStorage.clear();
		window.sessionStorage.setItem("user", JSON.stringify(u));
	}

	useEffect(() => {
		window.sessionStorage.getItem("user") != null && addUser(JSON.parse(window.sessionStorage.getItem("user")));
	},[])

	return(
		<AuthContext.Provider value={{addUser, user}}>
			{props.children}
		</AuthContext.Provider>
	);
}

export default AuthContextProvider;