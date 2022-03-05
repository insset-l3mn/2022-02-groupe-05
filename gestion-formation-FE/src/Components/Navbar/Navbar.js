import React, {useContext} from "react";
import "./Navbar.css";
import {NavLink} from "react-router-dom";
import {AuthContext} from "../../Context/AuthContext";

export default function Navbar(){

    const {addUser, user} = useContext(AuthContext);

    return (

        <header className="mb-auto bg-dark text-white px-3 pt-3">
            <h3 className="float-md-start mb-0">Skill-Reader</h3>
            <nav className="nav nav-masthead justify-content-center">
                <NavLink className="nav-link"
                         activeClassName={"active"}
                         aria-current="page"
                         to={"/"}>Accueil</NavLink>

                {user==null &&
                    <>
                        <NavLink className="nav-link"
                                 activeClassName={"active"}
                                 to={"/login"}>Connexion</NavLink>
                        <NavLink className="nav-link"
                                 activeClassName={"active"}
                                 to={"/register"}>Inscription</NavLink>
                    </>
                }

                <NavLink className="nav-link"
                         activeClassName={"active"}
                         to={"/contact"}>Contact</NavLink>

                {user!=null &&
                    <>
                        <NavLink className="nav-link"
                                 activeClassName={"active"}
                                 to={"/profil"}>Profil</NavLink>
                        <NavLink className="nav-link"
                                 to={"/logout"}>Déconnexion</NavLink>
                    </>
                }

            </nav>
        </header>

    );

}