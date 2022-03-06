/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.ProposalGestionnary;
import com.mycompany.projet.ejb.QuestionGestionnary;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author valen
 */
@Path("/proposal")
public class ProposalResource {

    @EJB
    private QuestionGestionnary questionGestionnary;

    @EJB
    private ProposalGestionnary proposalGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{questionID}")
    public List readDomains(@PathParam("questionID") int qId) {
        try {
            return proposalGestionnary.readProposals(qId);
        } catch (Exception e) {
            return null;
        }
    }
}
