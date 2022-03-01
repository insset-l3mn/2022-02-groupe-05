/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.QuestionGestionnary;
import com.mycompany.projet.entities.GfQuestion;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{level}/{difficulty}/{content}")
    public void testValue(@PathParam("level") String level, @PathParam("difficulty") String difficulty, @PathParam("content") String contents) {
        if (level != null && difficulty != null && contents != null) {
            questionGestionnary.createQuestion(new GfQuestion(level, difficulty, contents));
        }
    }
}
