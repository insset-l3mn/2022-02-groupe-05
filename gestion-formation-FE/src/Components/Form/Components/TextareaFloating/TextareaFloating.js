import React from "react";

{/*
    props = {
        placeholder,
        id,
        labelContent
    }
*/}
export default function TextareaFloating(props){

	return (
		<>
			<div className="form-floating">
				<textarea className={"form-control"}
						  placeholder={props.placeholder}
						  id={props.id}
						  name={props.name}
						  value={props.value}
						  onChange={props.onChange}
						  style={{height:"80px"}}>
				</textarea>
				<label htmlFor={props.id} className={"text-black"}>{props.labelContent}</label>
			</div>
			<br/>
		</>
	);

}