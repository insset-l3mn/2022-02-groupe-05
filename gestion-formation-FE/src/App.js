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
import Profil from "./Containers/Profil/Profil";
import AddQuestion from "./Containers/AddQuestion/AddQuestion";
import AddSkill from "./Containers/AddSkill/AddSkill";
import AddDomain from "./Containers/AddDomain/AddDomain";

function App() {

	return (
		<div className="App bg-dark text-white">
			<Navbar/>
			<Routes>
				<Route path={"/"} element={<Home/>}/>
				<Route path={"/login"} element={<Login/>}/>
				<Route path={"/register"} element={<Register/>}/>
				<Route path={"/contact"} element={<Contact/>}/>
				<Route path={"/profil"} element={<Profil/>}/>
				<Route path={"/survey"} element={<Questionnaire/>}/>
				<Route path={"/logout"} element={<Logout/>}/>
				<Route path={"/question/add"} element={<AddQuestion/>}/>
				<Route path={"/domain/add"} element={<AddDomain/>}/>
				<Route path={"/skill/add"} element={<AddSkill/>}/>
				<Route path={"*"} element={<NotFound/>}/>
			</Routes>
			<Footer/>
		</div>
	);
}
export default App;
