/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.UserGestionnary;
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
@Path("/user")
public class UserResource {

    @EJB
    private UserGestionnary userGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update/{id}/{username}/{email}/{password}")
    public Object testValue(@PathParam("id") int id, @PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
        if (userGestionnary.existUser(id)) {
            userGestionnary.updateUser(id, username, email, password);
            return new Message("success", "Les informations de l'utilisateur ont bien été mis à jour.");
        } else {
            return new Message("error", "L'utilisateur n'existe pas.");
        }
    }
}
