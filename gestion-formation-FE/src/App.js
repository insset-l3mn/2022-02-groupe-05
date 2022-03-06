import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./Containers/Home/Home";
import NotFound from "./Containers/NotFound/NotFound";
import React, {useContext} from "react";
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
import {AuthContext} from "./Context/AuthContext";

function App() {

	const {user, addUser} = useContext(AuthContext);

	return (
		<div className="App bg-dark text-white">
			<Navbar/>
			<Routes>
				<Route path={"/"} element={<Home/>}/>
				<Route path={"/login"} element={user === null ? <Login/> : <Profil/>}/>
				<Route path={"/register"} element={user === null ? <Register/> : <Profil/>}/>
				<Route path={"/contact"} element={<Contact/>}/>
				<Route path={"/profil"} element={user != null ? <Profil/> : <Login/>}/>
				<Route path={"/survey"} element={<Questionnaire/>}/>
				<Route path={"/logout"} element={<Logout/>}/>
				<Route path={"/question/add"} element={user != null && user.role !== "basic" ? <AddQuestion/> : <Login/>}/>
				<Route path={"/domain/add"} element={user != null && user.role !== "basic" ? <AddDomain/> : <Login/>}/>
				<Route path={"/skill/add"} element={user != null && user.role !== "basic" ? <AddSkill/> : <Login/>}/>
				<Route path={"*"} element={<NotFound/>}/>
			</Routes>
			<Footer/>
		</div>
	);
}
export default App;
