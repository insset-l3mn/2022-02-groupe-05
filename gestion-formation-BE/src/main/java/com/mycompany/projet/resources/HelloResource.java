/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author valen
 */
@Path("/hello")
public class HelloResource {

   @GET
   public String sayHello() {
      return "Hello World";
   }
}
