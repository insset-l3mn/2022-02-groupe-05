import './App.css';
import Navbar from "./Components/Navbar/Navbar";
import {Route, Routes} from "react-router-dom";
import Home from "./Containers/Home/Home";
import Login from "./Containers/Login/Login";
import NotFound from "./Containers/NotFound/NotFound";
import React, {useState} from "react";
import Footer from "./Components/Footer/Footer";
import Register from "./Containers/Register/Register";
import Contact from "./Containers/Contact/Contact";

function App() {

	const [token, setToken] = useState()

	return (
		<div className="App bg-dark text-white">
			<Navbar/>
			{token}
			<Routes>
				<Route path={"/"} element={<Home/>}/>
				<Route path={"/login"} element={token ? <Login/> : <Login setToken={setToken}/>}/>
				<Route path={"/register"} element={<Register/>}/>
				<Route path={"/contact"} element={<Contact/>}/>
				<Route path={"*"} element={<NotFound/>}/>
			</Routes>

			<Footer/>

		</div>
	);
}

export default App;
