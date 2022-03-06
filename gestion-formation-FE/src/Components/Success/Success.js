import React from "react";
import './Success.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

export default function Success(props){

    return (
        <div className="alert alert-success d-flex align-items-center" role="alert">
            <i className="bi bi-info-circle-fill"/>
            <div style={{marginLeft:"5px"}}>
                {props.message}
            </div>
        </div>
    );

}