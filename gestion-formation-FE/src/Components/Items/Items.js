import React from "react";

export default function Items(props){

    return (
        <>
            {props.currentItems && props.currentItems.map((item) => (
                <div key={item.name}>
                    <h3>{item.name}</h3>
                </div>
            ))}
        </>
    );

}