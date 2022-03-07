import React, {useContext} from "react";
import "./Navbar.css";
import {NavLink} from "react-router-dom";
import {AuthContext} from "../../Context/AuthContext";

export default function Navbar(){

    const {user, addUser} = useContext(AuthContext);

    return (

        <header className="mb-auto bg-dark text-white px-3 pt-3">
            <h3 className="float-md-start mb-0">Skill-Reader</h3>
            <nav className="nav nav-masthead justify-content-center">
                <NavLink className="nav-link"
                         activeclassname={"active"}
                         aria-current="page"
                         to={"/"}>Accueil</NavLink>

                {user === null &&
                    <>
                        <NavLink className="nav-link"
                                 activeclassname={"active"}
                                 to={"/login"}>Connexion</NavLink>
                        <NavLink className="nav-link"
                                 activeclassname={"active"}
                                 to={"/register"}>Inscription</NavLink>
                    </>
                }

                { user != null && user.role !== 'basic' &&

                    <>
                        <NavLink className="nav-link"
                                 activeclassname={"active"}
                                 to={"/dashboard"}>Tableau de bord</NavLink>
                    </>
                }

                <NavLink className="nav-link"
                         activeclassname={"active"}
                         style={{marginLeft:"1rem"}}
                         to={"/contact"}>Contact</NavLink>

                {user != null &&
                    <>
                        <NavLink className="nav-link"
                                 activeclassname={"active"}
                                 to={"/profil"}>Profil</NavLink>
                        <NavLink className="nav-link"
                                 to={"/logout"}>Déconnexion</NavLink>
                    </>
                }

            </nav>
        </header>

    );

}