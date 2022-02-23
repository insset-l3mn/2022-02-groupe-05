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
@Path("/register")
public class RegisterResource {

    @EJB
    private UserGestionnary userGestionnary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/{email}/{password}")
    public Boolean testValue(@PathParam("username") String username, @PathParam("username") String email, @PathParam("password") String password) {
        if (username != null && password != null && email != null) {
            if(!userGestionnary.existUser(username)){
                userGestionnary.createUser(new User(username, email, password));
                return true;
            }else return false;
        } else {
            return false;
        }
    }
}
