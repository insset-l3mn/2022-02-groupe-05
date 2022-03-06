/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.DomainGestionnary;
import com.mycompany.projet.ejb.ProposalGestionnary;
import com.mycompany.projet.ejb.QuestionGestionnary;
import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.entities.GfDomain;
import com.mycompany.projet.entities.GfProposal;
import com.mycompany.projet.entities.GfQuestion;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.Message;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
