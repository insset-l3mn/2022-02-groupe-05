/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.SubdomainGestionnary;
import com.mycompany.projet.entities.GfSubdomain;
import com.mycompany.projet.entities.Message;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author valen
 */
@Path("/graph")
public class GraphResource {

    @EJB
    private SubdomainGestionnary subdomainGestionnary;

    /*@POST
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
    }*/
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/global")
    public Object readSkills() {
        List<GfSubdomain> subdomains = subdomainGestionnary.readSubdomains(999, 0);
        int id = 1;
        int x = 0;
        int y = 0;
        
        JSONArray json = new JSONArray();
        
        for(int i = 0; i < subdomains.size()+1; i++){
            JSONObject item = new JSONObject();
                item.put("id", id);
                
            JSONArray data = new JSONArray();
            JSONObject itemData = new JSONObject();
                if(id == 1)itemData.put("label", "Aviation");
                else itemData.put("label", subdomains.get(i-1).getName());
            data.put(itemData);
            item.put("data", data);
            
            JSONArray position = new JSONArray();
            JSONObject positionData = new JSONObject();
                positionData.put("x",x);
                positionData.put("y",y);
            position.put(positionData);
            item.put("position", position);
                    
            json.put(item);
            id++;
            x+=5;
            y+=5;
        }
               
        return json.toString();
    }
}
