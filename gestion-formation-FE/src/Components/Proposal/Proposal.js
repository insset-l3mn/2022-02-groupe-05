import React from "react";
import "./Proposal.css"

export default function Proposal(props){

    return (
        <>
			<div {...props} id={props.proposal.idProposal}>
				{props.proposal.denominate}
			</div>
        </>
    );

}