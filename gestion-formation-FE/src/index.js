import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min';
import App from "./App";
import AuthContextProvider from "./Context/AuthContext";
import SurveyContextProvider from "./Context/SurveyContext";

ReactDOM.render(
    <BrowserRouter>
        <React.StrictMode>
            <AuthContextProvider>
                <SurveyContextProvider>
                    <App />
                </SurveyContextProvider>
            </AuthContextProvider>
        </React.StrictMode>
    </BrowserRouter>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
