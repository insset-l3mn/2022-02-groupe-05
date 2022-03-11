/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

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
@Path("/subdomain")
public class SubdomainResource {

    @EJB
    private SubdomainGestionnary subdomainGestionnary;

    @EJB
    private UserGestionnary userGestionnary;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/add")
    public Message addSubdomain(@FormParam("name") String name, @FormParam("userId") int id) {
        if (name != null) {
            if (!userGestionnary.isVisitor(id)) {
                User user = userGestionnary.requestUser(id);
                if (!subdomainGestionnary.existSubdomain(name)) {
                    subdomainGestionnary.createSubdomain(new GfSubdomain(name));
                    return new Message("success", "Le sous-domaine a bien été ajoutée.");
                } else {
                    return new Message("error", "Le sous-domaine existe déjà");
                }
            } else {
                return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de l'ajout d'un nouveau sous-domaine.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remove/{idSubdomain}/{userId}")
    public Message removeSkill(@PathParam("idSubdomain") int idSubdomain, @PathParam("userId") int userId) {
        if (!userGestionnary.isVisitor(userId)) {
            if (subdomainGestionnary.existSubdomain(idSubdomain)) {
                if (subdomainGestionnary.removeSubdomain(idSubdomain)) {
                    return new Message("success", "Le sous-domaine a bien été supprimée.");
                } else {
                    return new Message("error", "Une erreur est survenue lors de la suppression du sous-domaine. Des compétences doivent être en lien avec le sous-domaine empêchant sa suppression.");
                }
            } else {
                return new Message("error", "Le sous-domaine n'existe pas.");
            }
        } else {
            return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remove/force/{idSubdomain}/{userId}")
    public Message removeSkillForce(@PathParam("idSubdomain") int idSubdomain, @PathParam("userId") int userId) {
        if (!userGestionnary.isVisitor(userId)) {
            if (subdomainGestionnary.existSubdomain(idSubdomain)) {
                //supprimer question avant skill
                if (subdomainGestionnary.purgeSubdomainFromSkills(idSubdomain)) {
                    if (subdomainGestionnary.removeSubdomain(idSubdomain)) {
                        return new Message("success", "Le sous-domaine a bien été supprimée.");
                    } else {
                        return new Message("error", "Une erreur est survenue lors de la suppression en force du sous-domaine.");
                    }
                } else {
                    return new Message("error", "Une erreur est survenur lors de la suppression des compétences liés au sous-domaine.");
                }
            } else {
                return new Message("error", "Le sous-domaine n'existe pas.");
            }
        } else {
            return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    public Message removeSubdomain(@FormParam("id") int id, @FormParam("name") String name, @FormParam("userId") int userId) {
        if (name != null) {
            if (!userGestionnary.isVisitor(userId)) {
                if (subdomainGestionnary.existSubdomain(id)) {
                    if (!subdomainGestionnary.existSubdomain(name)) {
                        if (subdomainGestionnary.updateSubdomain(id, name)) {
                            return new Message("success", "Le sous-domaine a bien mise à jour.");
                        } else {
                            return new Message("error", "Une erreur est survenue lors de la mise à jour du sous-domaine.");
                        }
                    } else {
                        return new Message("error", "Un sous-domaine porte déjà ce nom.");
                    }
                } else {
                    return new Message("error", "Le sous-domaine n'existe pas.");
                }
            } else {
                return new Message("error", "Vous n'êtes pas autorisé à effectuer cette action.");
            }
        } else {
            return new Message("error", "Une erreur est survenue lors de la mise à jour du sous-domaine.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{idSubdomain}")
    public Object readSkill(@PathParam("idSubdomain") int id) {
        if (subdomainGestionnary.existSubdomain(id)) {
            GfSubdomain subdomain = subdomainGestionnary.readSubdomain(id);
            return subdomain;
        } else {
            return new Message("error", "La compétence n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{count}/{startAt}")
    public Object readSkills(@PathParam("count") int count, @PathParam("startAt") int startAt) {
        try {
            return subdomainGestionnary.readSubdomains(count, startAt);
        } catch (Exception e) {
            return new Message("error", "Une erreur est survenue.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public Object readSkills() {
        try {
            return subdomainGestionnary.countSubdomains();
        } catch (Exception e) {
            return new Message("error", "Une erreur est survenue.");
        }
    }
}
