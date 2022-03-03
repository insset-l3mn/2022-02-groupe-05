/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.DomainGestionnary;
import com.mycompany.projet.entities.GfDomain;
import com.mycompany.projet.entities.Message;
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
@Path("/domain")
public class DomainResource {

    @EJB
    private DomainGestionnary domainGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{denominate}")
    public Object testValue(@PathParam("denominate") String denominate) {
        if (denominate != null) {
            if (!domainGestionnary.existDomain(denominate)) {
                domainGestionnary.createDomain(new GfDomain(denominate));
                return new Message("success", "Le domaine a bien été ajouté.");
            } else {
                return new Message("error", "Le domaine existe déjà.");
            }
        }
        return new Message("error", "Une erreur est survenue lors de l'ajout d'un nouveau domaine.");
    }

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
    @Path("/update/{idDomain}/{denominate}")
    public Message updateDomain(@PathParam("idDomain") int id, @PathParam("denominate") String denominate) {

        if (denominate != null) {
            if (domainGestionnary.existDomain(id)) {
                if (!domainGestionnary.existDomain(denominate)) {
                    if (domainGestionnary.updateDomain(id, denominate)) {
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
        } else {
            return new Message("error", "Une erreur est survenue lors de la mise à jour du domaine.");
        }
    }
}
