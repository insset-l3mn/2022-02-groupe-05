import React, {useContext, useEffect, useState} from "react";
import './Profil.css';
import axios from "axios";
import {AuthContext} from "../../Context/AuthContext";

export default function Profil(props){

    const [userUpdate, setUpdateUser] = useState({});
    const [confirmPassword, setConfirmPassword] = useState("");
    const {user, addUser} = useContext(AuthContext)

    useEffect(() => {
        setUpdateUser(user)
        setConfirmPassword(userUpdate.password);
    },[])

    const onChangeInput = e => {
        const {name, value} = e.target;
        setUpdateUser(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const updateProfil = () => {
        //confirmPassword === user.userPassword ? console.log("OK mdp") : console.log("Nop mdp")
        if(userUpdate.userName.length > 0 && userUpdate.userEmail.length > 0 && userUpdate.userPassword.length > 0 && confirmPassword.length > 0 && userUpdate.userPassword === confirmPassword){
            console.log("Update ok")

            axios.get("http://localhost:8080/gestion-formation-BE/api/user/update/" + userUpdate.userId + "/" + userUpdate.userName + "/" + userUpdate.userEmail + "/" + userUpdate.userPassword)
                .then((response) => {
                    console.log(response)
                });
            axios.get("http://localhost:8080/gestion-formation-BE/api/user/login/"+userUpdate.userEmail+"/"+userUpdate.userPassword).then((response) => {
                if(!response["data"].hasOwnProperty("type")){
                    addUser(response["data"]);
                    setUpdateUser(response["data"])
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
                                           value={userUpdate.userName}
                                           className="form-control form-control-sm"
                                           onChange={onChangeInput}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td>
                                    <input type="email"
                                           name={"userEmail"}
                                           value={userUpdate.userEmail}
                                           className="form-control form-control-sm"
                                           onChange={onChangeInput}/>
                                </td>
                            </tr>
                            <tr>
                                <th>Mot de passe</th>
                                <td>
                                    <input type="password"
                                           name={"userPassword"}
                                           value={userUpdate.userPassword}
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
                                <td>{userUpdate.userRole}</td>
                            </tr>
                            <tr>
                                <td colSpan={"2"}>
                                    <button className={"btn btn-secondary"} onClick={updateProfil}>Modifier</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    );

}