import React, {createContext, useContext, useEffect, useState} from "react";

export const AuthContext = createContext();

const AuthContextProvider = props => {
	const [user, setUser] = useState(null);

	const addUser = (u) =>{
		setUser(u);
		localStorage.setItem("user", JSON.stringify(u))
	}

	useEffect(() => {
		localStorage.getItem("user") != null && addUser(JSON.parse(localStorage.getItem("user")));
	},[])

	return(
		<AuthContext.Provider value={{user, addUser}}>
			{props.children}
		</AuthContext.Provider>
	);
}

export default AuthContextProvider;