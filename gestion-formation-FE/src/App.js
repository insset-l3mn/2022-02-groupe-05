import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./Views/Home/Home";
import NotFound from "./Views/NotFound/NotFound";
import React, {useContext} from "react";
import Register from "./Views/Register/Register";
import Logout from "./Views/Logout/Logout";
import Login from "./Views/Login/Login";
import Profil from "./Views/Profil/Profil";
import {AuthContext} from "./Context/AuthContext";
import Dashboard from "./Views/Dashboard/Dashboard";
import Header from "./Components/Header/Header";
import ChooseSkill from "./Views/Skill/ChooseSkill/ChooseSkill";
import Survey from "./Views/Survey/Survey";
import {SurveyContext} from "./Context/SurveyContext";

function App() {

	const {user, addUser} = useContext(AuthContext);
	const {survey, setSurvey} = useContext(SurveyContext);

	return (
		<div className="App bg-dark text-white">
			<Header/>
			<Routes>
				<Route path={"/"} element={<Home/>}/>

				<Route path={"/profil"} element={user != null ? <Profil/> : <Login/>}/>

				<Route path={"/logout"} element={<Logout/>}/>
				<Route path={"/login"} element={user === null ? <Login/> : <Profil/>}/>
				<Route path={"/register"} element={user === null ? <Register/> : <Profil/>}/>

				<Route path={"/dashboard"} element={user != null && user.role !== "basic" ? <Dashboard/> : <Login/>}/>
				<Route path={"/profil/chooseSkill"} element={user === null ? <ChooseSkill/> : <Profil/>}/>
				<Route path={"/survey"} element={user === null && !survey ? <Survey/> : <Profil/>}/>

				<Route path={"*"} element={<NotFound/>}/>
			</Routes>
		</div>
	);
}
export default App;
