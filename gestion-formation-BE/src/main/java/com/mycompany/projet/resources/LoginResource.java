/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.UserGestionnary;
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
@Path("/login")
public class LoginResource {
    @EJB
    private UserGestionnary userGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/username/{username}/password/{password}")
    public User testValue(@PathParam("username") String username, @PathParam("password") String password) {
        if (username != null && password != null) {
            User user = userGestionnary.requestUser(username, password);
            if(user != null) return user;
            else return null;
        } else {
            return null;
        }
    }
}
