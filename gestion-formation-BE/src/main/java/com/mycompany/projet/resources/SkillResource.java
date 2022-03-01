/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.Message;
import com.mycompany.projet.entities.User;
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
@Path("/skill")
public class SkillResource {

    @EJB
    private SkillGestionnary skillGestionnary;
    @EJB
    private UserGestionnary userGestionnary;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{name}/{weight}/{id}")
    public Message testValue(@PathParam("name") String name, @PathParam("weight") String weight, @PathParam("id") int id) {
        if (name != null && weight != null) {
            if(userGestionnary.isFormer(id)){
                User user = userGestionnary.requestUser(id);
                if(!skillGestionnary.existSkill(name)){
                    skillGestionnary.createSkill(new GfSkill(user, name, weight));
                    return new Message("success","La compétance à bien été ajoutée.");
                }else{
                    return new Message("error","La compétance existe déjà");
                }
            }else{
                return new Message("error","Vous n'êtes pas autorisé à effectuer cette action.");
            }
        }else{
            return new Message("error", "Une erreur est survenue lors de l'ajout d'une nouvelle compétance.");
        }
    }
}
