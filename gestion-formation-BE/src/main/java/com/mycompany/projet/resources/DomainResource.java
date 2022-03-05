/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.DomainGestionnary;
import com.mycompany.projet.entities.GfDomain;
import com.mycompany.projet.entities.Message;
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
@Path("/domain")
public class DomainResource {

    @EJB
    private DomainGestionnary domainGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/remove/{idDomain}")
    public Message removeDomain(@PathParam("idDomain") int idDomain) {
        if (domainGestionnary.existDomain(idDomain)) {
            if (domainGestionnary.removeDomain(idDomain)) {
                return new Message("success", "Le domaine a bien été supprimée.");
            } else {
                return new Message("error", "Une erreur est survenue lors de la suppression du domaine.");
            }
        } else {
            return new Message("error", "La compétance n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{idDomain}")
    public Object readDomain(@PathParam("idDomain") int id) {
        if (domainGestionnary.existDomain(id)) {
            GfDomain domain = domainGestionnary.readDomain(id);

            /*JsonObject value = Json.createObjectBuilder()
                    .add("id", domain.getIdDomain())
                    .add("denominate", domain.getIdDomain())
                    .build();
             */
            return domain;
        } else {
            return new Message("error", "Le domaine n'existe pas.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read/{count}/{startAt}")
    public Object readDomains(@PathParam("count") int count, @PathParam("startAt") int startAt) {
        try {
            return domainGestionnary.readDomains(count, startAt);
        } catch (Exception e) {
            return new Message("error", "Une erreur est survenue.");
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/add")
    public Message addDomain(@FormParam("denominate") String d) {
        if (!domainGestionnary.existDomain(d)) {
            domainGestionnary.createDomain(new GfDomain(d));
            return new Message("success", "Le domaine a bien été ajouté.");
        } else {
            return new Message("error", "Le domaine existe déjà.");
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    public Message updateDomain(@FormParam("id") int id, @FormParam("denominate") String d) {
        if (domainGestionnary.existDomain(id)) {
            if (!domainGestionnary.existDomain(d)) {
                if (domainGestionnary.updateDomain(id, d)) {
                    return new Message("success", "Le domaine a bien été mise à jour.");
                } else {
                    return new Message("error", "Une erreur est survenue lors de la mise à jour du domaine.");
                }
            } else {
                return new Message("error", "Un domaine porte déjà ce nom.");
            }
        } else {
            return new Message("error", "Le domaine n'existe pas.");
        }
    }
}
