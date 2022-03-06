/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.ProposalGestionnary;
import com.mycompany.projet.ejb.QuestionGestionnary;
import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.GfProposal;
import com.mycompany.projet.entities.GfQuestion;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.Message;
import com.mycompany.projet.entities.User;
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
    private SkillGestionnary skillGestionnary;

    @EJB
    private ProposalGestionnary proposalGestionnary;

    @EJB
    private UserGestionnary userGestionnary;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/add")
    public Message addQuestion(@FormParam("difficulty") Integer difficulty, @FormParam("contents") String contents, @FormParam("skillName") String skillName, @FormParam("right_answer") String right_answer, @FormParam("wrong_answer_1") String wrong_answer_1, @FormParam("wrong_answer_2") String wrong_answer_2, @FormParam("wrong_answer_3") String wrong_answer_3, @FormParam("userId") int userId) {
        if (difficulty != null && contents != null && skillName != null && right_answer != null && wrong_answer_1 != null && wrong_answer_2 != null && wrong_answer_3 != null) {
            GfSkill skill = skillGestionnary.requestSkill(skillName);

            if (skill != null) {
                if (!questionGestionnary.existQuestion(contents)) {
                    User user = userGestionnary.requestUser(userId);
                    if (user != null) {
                        if (!user.getUserRole().equals("VISITOR")) {
                            questionGestionnary.createQuestion(new GfQuestion(difficulty, contents, skill, user));

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
                            return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
                        }
                    } else {
                        return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
                    }
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
    @Path("/remove/{idQuestion}/{idUser}")
    public Message removeSkill(@PathParam("idQuestion") int idQuestion, @PathParam("idUser") int idUser) {
        if (questionGestionnary.existQuestion(idQuestion)) {
            if (questionGestionnary.isOwnerOfQuestion(idQuestion, idUser) || userGestionnary.isAdmin(idUser)) {
                proposalGestionnary.purgeProposalFromQuestion(idQuestion);

                if (questionGestionnary.removeQuestion(idQuestion)) {
                    return new Message("success", "La question a bien été supprimée.");
                } else {
                    return new Message("error", "Une erreur est survenue lors de la suppression de la question.");
                }
            }else{
                return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
            }
        } else {
            return new Message("error", "La question n'existe pas.");
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    public Message updateQuestion(@FormParam("id") int id, @FormParam("difficulty") Integer difficulty, @FormParam("contents") String contents, @FormParam("skillName") String skillName, @FormParam("right_answer") String right_answer, @FormParam("wrong_answer_1") String wrong_answer_1, @FormParam("wrong_answer_2") String wrong_answer_2, @FormParam("wrong_answer_3") String wrong_answer_3, @FormParam("userId") int userId) {
        if (contents != null && skillName != null && right_answer != null && wrong_answer_1 != null && wrong_answer_2 != null && wrong_answer_3 != null) {
            if (questionGestionnary.existQuestion(id)) {
                if (questionGestionnary.isOwnerOfQuestion(id, userId) || userGestionnary.isAdmin(id)) {
                    if (!questionGestionnary.existQuestion(contents) || contents.equals(questionGestionnary.readQuestion(contents).getContents())) {
                        if (questionGestionnary.updateQuestion(id, difficulty, contents, skillName)) {
                            proposalGestionnary.purgeProposalFromQuestion(id);

                            //On ne s'embête pas à UPDATE, on les recréer...
                            GfQuestion question = questionGestionnary.readQuestion(id);
                            GfProposal p1 = new GfProposal(question, right_answer, (short) 1);
                            proposalGestionnary.createProposal(p1);
                            GfProposal p2 = new GfProposal(question, wrong_answer_1, (short) 0);
                            proposalGestionnary.createProposal(p2);
                            GfProposal p3 = new GfProposal(question, wrong_answer_2, (short) 0);
                            proposalGestionnary.createProposal(p3);
                            GfProposal p4 = new GfProposal(question, wrong_answer_3, (short) 0);
                            proposalGestionnary.createProposal(p4);

                            return new Message("success", "La question a bien été mise à jour.");
                        } else {
                            return new Message("error", "Une erreur est survenue lors de la mise à jour de la question. Vérifiez que la compétance existe.");
                        }
                    } else {
                        return new Message("error", "Une question existe déjà.");
                    }
                } else {
                    return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
                }
            } else {
                return new Message("error", "La question n'existe pas.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de la mise à jour de la question.");
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public Object readQuestions() {
        try{
            return questionGestionnary.countQuestions();
        }catch(Exception e){
            return new Message("error", "Une erreur est survenue.");
        }
    }
}
