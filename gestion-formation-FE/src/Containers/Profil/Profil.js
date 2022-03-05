import React, {useContext, useEffect, useState} from "react";
import './Profil.css';
import {Navigate} from "react-router-dom";
import axios from "axios";
import {AuthContext} from "../../Context/AuthContext";

export default function Profil(props){

    const [userProfil, setUser] = useState({});
    const [confirmPassword, setConfirmPassword] = useState("");
    const {addUser, user} = useContext(AuthContext)

    useEffect(() => {
        console.log(JSON.parse(window.sessionStorage.getItem("user")));
        setUser(JSON.parse(window.sessionStorage.getItem("user")));
        setConfirmPassword(userProfil.userPassword);
    },[])

    const onChangeInput = e => {
        const {name, value} = e.target;
        setUser(prevState => ({
            ...prevState,
            [name]: value
        }));
        console.log(userProfil)
    }

    const test = () => {
        //confirmPassword === user.userPassword ? console.log("OK mdp") : console.log("Nop mdp")
        if(userProfil.userName.length > 0 && userProfil.userEmail.length > 0 && userProfil.userPassword.length > 0 && confirmPassword.length > 0 && userProfil.userPassword === confirmPassword){
            console.log("Update ok")

            axios.get("http://localhost:8080/gestion-formation-BE/api/user/update/" + userProfil.userId + "/" + userProfil.userName + "/" + userProfil.userEmail + "/" + userProfil.userPassword)
                .then((response) => {
                    console.log(response)
                });
            axios.get("http://localhost:8080/gestion-formation-BE/api/user/login/"+userProfil.userEmail+"/"+userProfil.userPassword).then((response) => {
                if(!response["data"].hasOwnProperty("type")){
                    addUser(response["data"]);
                    setUser(response["data"])
                }

            });
        }
    }


    return (
        <>
            <div className="d-flex h-100 text-center text-white bg-dark align-items-center">
                <div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
                    <h1>Votre profil</h1>
                    <br/>
                    <table className="table table-dark table-striped">
                        <tbody>
                            <tr>
                                <th>Pseudo</th>
                                <td>
                                    <input type="text"
                                           name={"userName"}
                                           value={userProfil.userName}
                                           className="form-control form-control-sm"
                                           onChange={onChangeInput}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td>
                                    <input type="email"
                                           name={"userEmail"}
                                           value={userProfil.userEmail}
                                           className="form-control form-control-sm"
                                           onChange={onChangeInput}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Mot de passe</th>
                                <td>
                                    <input type="password"
                                           name={"userPassword"}
                                           value={userProfil.userPassword}
                                           className="form-control form-control-sm"
                                           onChange={onChangeInput}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Confirmation mot de passe</th>
                                <td>
                                    <input type="password"
                                           value={confirmPassword}
                                           className="form-control form-control-sm"
                                           onChange={e => setConfirmPassword(e.target.value)}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Role</th>
                                <td>{userProfil.userRole}</td>
                            </tr>
                            <tr>
                                <td colSpan={"2"}>
                                    <button className={"btn btn-secondary"} onClick={test}>Modifier</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    );

}