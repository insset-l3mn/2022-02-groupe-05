import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./Views/Home/Home";
import NotFound from "./Views/NotFound/NotFound";
import React, {useContext} from "react";
import Register from "./Views/Register/Register";
import Logout from "./Views/Logout/Logout";
import Questionnaire from "./Containers/Questionnaire/Questionnaire";
import Login from "./Views/Login/Login";
import Contact from "./Views/Contact/Contact";
import Profil from "./Views/Profil/Profil";
import {AuthContext} from "./Context/AuthContext";
import Dashboard from "./Views/Dashboard/Dashboard";
import Header from "./Components/Header/Header";
import ViewSkill from "./Views/Skill/ViewSkill/ViewSkill";

function App() {

	const {user, addUser} = useContext(AuthContext);

	return (
		<div className="App bg-dark text-white">
			<Header/>
			<Routes>
				<Route path={"/"} element={<Home/>}/>

				<Route path={"/contact"} element={<Contact/>}/>
				<Route path={"/profil"} element={user != null ? <Profil/> : <Login/>}/>
				<Route path={"/survey"} element={<Questionnaire/>}/>

				<Route path={"/logout"} element={<Logout/>}/>
				<Route path={"/login"} element={user === null ? <Login/> : <Profil/>}/>
				<Route path={"/register"} element={user === null ? <Register/> : <Profil/>}/>

				<Route path={"/dashboard"} element={user != null && user.role !== "basic" ? <Dashboard/> : <Login/>}/>
				<Route path={"/dashboard/skill/view"} element={<ViewSkill/>}/>


				<Route path={"*"} element={<NotFound/>}/>
			</Routes>
		</div>
	);
}
export default App;
