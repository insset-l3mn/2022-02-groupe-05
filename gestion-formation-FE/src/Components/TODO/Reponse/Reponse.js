import React from "react";

export default function Reponse(props){
    return (
        <>
			<ul className="list-group">

				<li className="list-group-item">
					<input className="form-check-input me-1" type="checkbox" value={props.content}/>
					{props.content}
				</li>

			</ul>
        </>
    );

}