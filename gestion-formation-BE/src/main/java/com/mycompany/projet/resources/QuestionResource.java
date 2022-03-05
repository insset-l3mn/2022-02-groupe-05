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
@Path("/question")
public class QuestionResource {

    @EJB
    private QuestionGestionnary questionGestionnary;

    @EJB
    private DomainGestionnary domainGestionnary;

    @EJB
    private SkillGestionnary skillGestionnary;

    @EJB
    private ProposalGestionnary proposalGestionnary;

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{level}/{difficulty}/{contents}/{domainName}/{skillName}")
    public Message addQuestion(@PathParam("level") String level, @PathParam("difficulty") String difficulty, @PathParam("contents") String contents, @PathParam("domainName") String domainName, @PathParam("skillName") String skillName) {
        if (level != null && difficulty != null && contents != null && domainName != null && skillName != null) {

            try {
                contents = URLDecoder.decode(contents, "UTF-8");
                domainName = URLDecoder.decode(domainName, "UTF-8");
                skillName = URLDecoder.decode(skillName, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(QuestionResource.class.getName()).log(Level.SEVERE, null, ex);
            }

            GfDomain domain = domainGestionnary.requestDomain(domainName);
            GfSkill skill = skillGestionnary.requestSkill(skillName);

            if (domain != null && skill != null) {
                if (!questionGestionnary.existQuestion(contents)) {
                    questionGestionnary.createQuestion(new GfQuestion(level, difficulty, contents, domain, skill));
                    return new Message("success", "La question a bien été ajoutée.");
                } else {
                    return new Message("error", "La question existe déjà.");
                }
            } else {
                return new Message("error", "Le domaine ou la compétance n'éxiste pas.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de l'ajout d'une nouvelle compétance.");
        }
    }*/
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/add")
    public Message addQuestion(@FormParam("level") String level, @FormParam("difficulty") String difficulty, @FormParam("contents") String contents, @FormParam("domainName") String domainName, @FormParam("skillName") String skillName, @FormParam("right_answer") String right_answer, @FormParam("wrong_answer_1") String wrong_answer_1, @FormParam("wrong_answer_2") String wrong_answer_2, @FormParam("wrong_answer_3") String wrong_answer_3) {
        if (level != null && difficulty != null && contents != null && domainName != null && skillName != null && right_answer != null && wrong_answer_1 != null && wrong_answer_2 != null && wrong_answer_3 != null) {

            GfDomain domain = domainGestionnary.requestDomain(domainName);
            GfSkill skill = skillGestionnary.requestSkill(skillName);

            if (domain != null && skill != null) {
                if (!questionGestionnary.existQuestion(contents)) {
                    questionGestionnary.createQuestion(new GfQuestion(level, difficulty, contents, domain, skill));

                    GfQuestion question = questionGestionnary.readQuestion(contents);
                    //add answer
                    if (question != null) {
                        GfProposal p1 = new GfProposal(question, right_answer, (short) 1);
                        proposalGestionnary.createProposal(p1);
                        GfProposal p2 = new GfProposal(question, wrong_answer_1, (short) 0);
                        proposalGestionnary.createProposal(p2);
                        GfProposal p3 = new GfProposal(question, wrong_answer_2, (short) 0);
                        proposalGestionnary.createProposal(p3);
                        GfProposal p4 = new GfProposal(question, wrong_answer_3, (short) 0);
                        proposalGestionnary.createProposal(p4);
                    }
                    return new Message("success", "La question a bien été ajoutée.");
                } else {
                    return new Message("error", "La question existe déjà.");
                }
            } else {
                return new Message("error", "Le domaine ou la compétance n'éxiste pas.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de l'ajout d'une nouvelle question.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remove/{id}")
    public Message removeSkill(@PathParam("id") int id) {
        if (questionGestionnary.existQuestion(id)) {
            if (questionGestionnary.removeQuestion(id)) {
                return new Message("success", "La question a bien été supprimée.");
            } else {
                return new Message("error", "Une erreur est survenue lors de la suppression de la question.");
            }
        } else {
            return new Message("error", "La question n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update/{id}/{level}/{difficulty}/{contents}/{domainName}/{skillName}")
    public Message updateQuestion(@PathParam("id") int id, @PathParam("level") String level, @PathParam("difficulty") String difficulty, @PathParam("contents") String contents, @PathParam("domainName") String domainName, @PathParam("skillName") String skillName) {

        try {
            contents = URLDecoder.decode(contents, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(QuestionResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (questionGestionnary.existQuestion(id)) {
            if (!questionGestionnary.existQuestion(contents)) {
                if (questionGestionnary.updateQuestion(id, level, difficulty, contents, domainName, skillName)) {
                    return new Message("success", "La compétance a bien été mise à jour.");
                } else {
                    return new Message("error", "Une erreur est survenue lors de la mise à jour de la compétance. Vérifiez que le domaine et la compétance existe.");
                }
            } else {
                return new Message("error", "Une question existe déjà.");
            }
        } else {
            return new Message("error", "La question n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{id}")
    public Object readDomain(@PathParam("id") int id) {
        if (questionGestionnary.existQuestion(id)) {
            GfQuestion question = questionGestionnary.readQuestion(id);
            return question;
        } else {
            return new Message("error", "La question n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{count}/{startAt}")
    public Object readDomains(@PathParam("count") int count, @PathParam("startAt") int startAt) {
        try {
            return questionGestionnary.readQuestions(count, startAt);
        } catch (Exception e) {
            return new Message("error", "Une erreur est survenue.");
        }
    }
}
