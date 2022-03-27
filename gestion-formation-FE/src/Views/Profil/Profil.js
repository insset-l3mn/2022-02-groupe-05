import React, {useContext, useEffect, useState} from "react";
import './Profil.css';
import axios from "axios";
import {AuthContext} from "../../Context/AuthContext";
import Error from "../../Components/Error/Error";
import Success from "../../Components/Success/Success";
import Container from "../../Components/Container/Container";

export default function Profil(props){

    const [userUpdate, setUpdateUser] = useState({});
    const [confirmPassword, setConfirmPassword] = useState("");


    const {user, addUser} = useContext(AuthContext)

    const [success, setSuccess] = useState(false);
    const [error, setError] = useState(false);

    useEffect(() => {
        setUpdateUser(user)
        setConfirmPassword(user.password);
    },[])

    const onChangeInput = e => {
        const {name, value} = e.target;
        setUpdateUser(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const updateProfil = async () => {
        setError(false)
        setSuccess(false)
        console.log(userUpdate)
        if (userUpdate.userName.length > 0 && userUpdate.userEmail.length > 0 && userUpdate.userPassword.length > 0 && confirmPassword.length > 0) {

            const config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }

            const params = new URLSearchParams()
            params.append('id', userUpdate.userId)
            params.append('username', userUpdate.userName)
            params.append('password', userUpdate.userPassword)
            params.append('confirmation_password', confirmPassword)
            params.append('email', userUpdate.userEmail)

            await axios.post("http://localhost:8080/gestion-formation-BE/api/user/update", params, config)
                .then((response) => {
                    console.log(response)
                    if(response["data"]["type"] === "success"){
                        setSuccess(response["data"]["message"])

                        const paramsLogin = new URLSearchParams()
                        paramsLogin.append('username', userUpdate.userName)
                        paramsLogin.append('password', userUpdate.userPassword)

                        axios.post("http://localhost:8080/gestion-formation-BE/api/user/login", paramsLogin, config)
                            .then((response) => {
                                console.log(response["data"])
                                addUser(response["data"]);
                                setUpdateUser(response["data"])
                            })
                    }else{
                        setError(response["data"]["message"])
                    }
                })
        }
    }


    return (
        <>
            <Container>
                <h1>Votre profil</h1>
                <br/>

                {error && <Error message={error}/>}
                {success && <Success message={success}/>}

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
            </Container>
        </>
    );

}