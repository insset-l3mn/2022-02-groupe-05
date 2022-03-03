/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.DomainGestionnary;
import com.mycompany.projet.ejb.QuestionGestionnary;
import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.entities.GfDomain;
import com.mycompany.projet.entities.GfQuestion;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.Message;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("/question")
public class QuestionResource {

    @EJB
    private QuestionGestionnary questionGestionnary;

    @EJB
    private DomainGestionnary domainGestionnary;

    @EJB
    private SkillGestionnary skillGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{level}/{difficulty}/{contents}/{domainName}/{skillName}")
    public Message testValue(@PathParam("level") String level, @PathParam("difficulty") String difficulty, @PathParam("contents") String contents, @PathParam("domainName") String domainName, @PathParam("skillName") String skillName) {
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
    }
}
