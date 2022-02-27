import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./Containers/Home/Home";
import NotFound from "./Containers/NotFound/NotFound";
import React from "react";
import Footer from "./Components/Footer/Footer";
import Register from "./Containers/Register/Register";
import Logout from "./Containers/Logout/Logout";
import Questionnaire from "./Containers/Questionnaire/Questionnaire";
import Navbar from "./Components/Navbar/Navbar";
import Login from "./Containers/Login/Login";
import Contact from "./Containers/Contact/Contact";

function App() {

	return (
		<div className="App bg-dark text-white">
			<Navbar/>
			<Routes>
				<Route path={"/"} element={<Home/>}/>
				<Route path={"/login"} element={<Login/>}/>
				<Route path={"/register"} element={<Register/>}/>
				<Route path={"/contact"} element={<Contact/>}/>
				<Route path={"/questionnaire"} element={<Questionnaire/>}/>
				<Route path={"/logout"} element={<Logout/>}/>
				<Route path={"*"} element={<NotFound/>}/>
			</Routes>
			<Footer/>
		</div>
	);
}
export default App;
