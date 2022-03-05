import React from "react";
import './Error.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

export default function Error(props){

    return (
        <div className="alert alert-danger d-flex align-items-center" role="alert">
            <i className="bi bi-exclamation-triangle-fill"/>
            <div style={{marginLeft:"5px"}}>
                {props.message}
            </div>
        </div>
    );

}