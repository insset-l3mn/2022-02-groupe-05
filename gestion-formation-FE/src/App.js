import './App.css';
import Navbar from "./Components/Navbar/Navbar";
import {Route, Routes} from "react-router-dom";
import Home from "./Containers/Home/Home";
import Login from "./Containers/Login/Login";
import NotFound from "./Containers/NotFound/NotFound";
import React, {useContext, useState} from "react";
import Footer from "./Components/Footer/Footer";
import Register from "./Containers/Register/Register";
import Contact from "./Containers/Contact/Contact";
import LoginContextProvider, {LoginContext} from "./Context/LoginContext";
import Logout from "./Containers/Logout/Logout";

function App() {

	return (
		<div className="App bg-dark text-white">
			<LoginContextProvider>
				<Navbar/>
					<Routes>
					<Route path={"/"} element={<Home/>}/>
					<Route path={"/login"} element={<Login/>}/>
					<Route path={"/register"} element={<Register/>}/>
					<Route path={"/contact"} element={<Contact/>}/>
					<Route path={"/logout"} element={<Logout/>}/>
					<Route path={"*"} element={<NotFound/>}/>
				</Routes>
				<Footer/>
			</LoginContextProvider>
		</div>
	);
}

export default App;
