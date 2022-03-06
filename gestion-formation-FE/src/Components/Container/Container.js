import React from "react";

export default function Container(props){

    return (
        <>
			<div className="d-flex h-100 text-center text-white bg-dark align-items-center">
				<div className="cover-container d-flex w-100 p-3 mx-auto flex-column">
					{props.children}
				</div>
			</div>

        </>
    );

}