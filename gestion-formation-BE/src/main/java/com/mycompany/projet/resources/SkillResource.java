/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.ejb.SubdomainGestionnary;
import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.GfSubdomain;
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
@Path("/skill")
public class SkillResource {

    @EJB
    private SkillGestionnary skillGestionnary;
    
    @EJB
    private UserGestionnary userGestionnary;
    
    @EJB
    private SubdomainGestionnary subdomainGestionnary;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/add")
    public Message addSkill(@FormParam("name") String name, @FormParam("weight") Integer weight, @FormParam("subdomain") String subdomainName, @FormParam("idUser") int id) {
        if (name != null && weight != null) {
            if (!userGestionnary.isVisitor(id)) {
                User user = userGestionnary.requestUser(id);
                if (!skillGestionnary.existSkill(name)) {
                    GfSubdomain subdomain = subdomainGestionnary.requestSubdomain(subdomainName);
                    if(subdomain != null){
                        skillGestionnary.createSkill(new GfSkill(user, name, weight, subdomain));
                        return new Message("success", "La compétence a bien été ajoutée.");
                    }else{
                        return new Message("error", "Le sous-domaine n'existe pas.");
                    }
                } else {
                    return new Message("error", "La compétence existe déjà");
                }
            } else {
                return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de l'ajout d'une nouvelle compétence.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remove/{idSkill}/{idUser}")
    public Message removeSkill(@PathParam("idSkill") int idSkill, @PathParam("idUser") int idUser) {
        if (!userGestionnary.isVisitor(idUser)) {
            if (skillGestionnary.existSkill(idSkill)) {
                if (skillGestionnary.removeSkill(idSkill)) {
                    return new Message("success", "La compétence a bien été supprimée.");
                } else {
                    return new Message("error", "Une erreur est survenue lors de la suppression de la compétence.");
                }
            } else {
                return new Message("error", "La compétence n'existe pas.");
            }
        } else {
            return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    public Message updateSkill(@FormParam("idSkill") int idSkill, @FormParam("name") String name, @FormParam("weight") int weight, @FormParam("idUser") int idUser) {
        if (name != null && weight > 0) {
            if (!userGestionnary.isVisitor(idUser)) {
                if (skillGestionnary.existSkill(idSkill)) {
                    GfSkill skill = skillGestionnary.readSkill(idSkill);
                    
                    if (!skillGestionnary.existSkill(name) || name.equals(skill.getName())) {
                        if (skillGestionnary.updateSkill(idSkill, name, weight)) {
                            return new Message("success", "La compétence a bien été mise à jour.");
                        } else {
                            return new Message("error", "Une erreur est survenue lors de la mise à jour de la compétence.");
                        }
                    }else{
                        return new Message("error", "Une compétence porte déjà ce nom.");
                    }
                } else {
                    return new Message("error", "La compétence n'existe pas.");
                }
            } else {
                return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de la mise à jour de la compétence.");
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{idSkill}")
    public Object readSkill(@PathParam("idSkill") int id) {
        if (skillGestionnary.existSkill(id)) {
            GfSkill skill = skillGestionnary.readSkill(id);    
            return skill;
        } else {
            return new Message("error", "La compétence n'existe pas.");
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{count}/{startAt}")
    public Object readSkills(@PathParam("count") int count, @PathParam("startAt") int startAt) {
        try{
            return skillGestionnary.readSkills(count, startAt);
        }catch(Exception e){
            return new Message("error", "Une erreur est survenue.");
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/all")
    public Object readAllSkills() {
        try{
            return skillGestionnary.readAll();
        }catch(Exception e){
            return new Message("error", "Une erreur est survenue.");
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public Object readSkills() {
        try{
            return skillGestionnary.countSkills();
        }catch(Exception e){
            return new Message("error", "Une erreur est survenue.");
        }
    }
}
