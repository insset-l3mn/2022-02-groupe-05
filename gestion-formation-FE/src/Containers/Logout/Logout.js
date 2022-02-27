import React, {useContext, useEffect} from "react";
import "./Logout.css";
import {AuthContext} from "../../Context/AuthContext";
import {Navigate} from "react-router-dom";

export default function Logout(){

    const {addUser, user} = useContext(AuthContext);

    useEffect(() => {
        addUser(null)
        window.sessionStorage.clear();
    }, [])

    return (
        <>
            {user === null && <Navigate to={"/login"}/>}

            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
                    <p>Déconnexion !</p>
                </div>
            </div>
        </>
    );

}