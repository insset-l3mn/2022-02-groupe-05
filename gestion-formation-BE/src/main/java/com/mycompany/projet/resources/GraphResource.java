/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.ejb.SubdomainGestionnary;
import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.GfSubdomain;
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

    @EJB
    private SkillGestionnary skillGestionnary;

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

        for (int i = 0; i < subdomains.size(); i++) {
            subdomains.get(i).setTemp_id(id);

            JSONObject item = new JSONObject();
            item.put("id", subdomains.get(i).getTemp_id());

            JSONArray data = new JSONArray();
            JSONObject itemData = new JSONObject();
            //itemData.put("label", "Aviation");
            itemData.put("label", subdomains.get(i).getName());
            itemData.put("object_id", subdomains.get(i).getIdSubdomain());
            data.put(itemData);
            item.put("data", data);

            JSONArray position = new JSONArray();
            JSONObject positionData = new JSONObject();
            positionData.put("x", x);
            positionData.put("y", y);
            position.put(positionData);
            item.put("position", position);

            json.put(item);
            id++;
            x += 5;
            y += 5;

            List<GfSkill> skills = skillGestionnary.readAllFromSubdomainId(subdomains.get(i).getIdSubdomain());

            for (int j = 0; j < skills.size(); j++) {
                //PARTIE AJOUT DE LA COMPETANCE
                skills.get(j).setTemp_id(id);

                JSONObject itemSkill = new JSONObject();
                itemSkill.put("id", skills.get(j).getTemp_id());

                JSONArray dataSkill = new JSONArray();
                JSONObject itemDataSkill = new JSONObject();
                //itemData.put("label", "Aviation");
                itemDataSkill.put("label", skills.get(j).getName());
                itemDataSkill.put("object_id", skills.get(j).getIdSkill());
                dataSkill.put(itemDataSkill);
                itemSkill.put("data", dataSkill);

                JSONArray positionSkill = new JSONArray();
                JSONObject positionDataSkill = new JSONObject();
                positionDataSkill.put("x", x);
                positionDataSkill.put("y", y);
                positionSkill.put(positionDataSkill);
                itemSkill.put("position", positionSkill);

                json.put(itemSkill);

                //PARTIE AJOUT DU LIEN ENTRE LE SOUSDOMAINE ET LE SKILL
                JSONArray link = new JSONArray();
                JSONObject linkData = new JSONObject();
                linkData.put("id", "e" + subdomains.get(i).getTemp_id() + "-" + skills.get(j).getTemp_id());
                linkData.put("source", subdomains.get(i).getTemp_id());
                linkData.put("target", skills.get(j).getTemp_id());
                link.put(linkData);
                json.put(linkData);

                x += 5;
                y += 5;

                id++;
            }
        }

        //Ajout de aviation
        JSONObject item = new JSONObject();
        item.put("id", 999);

        JSONArray data = new JSONArray();
        JSONObject itemData = new JSONObject();
        //itemData.put("label", "Aviation");
        itemData.put("label", "Aviation");
        itemData.put("object_id", -1);
        data.put(itemData);
        item.put("data", data);

        JSONArray position = new JSONArray();
        JSONObject positionData = new JSONObject();
        positionData.put("x", x);
        positionData.put("y", y);
        position.put(positionData);
        item.put("position", position);

        json.put(item);
        
        
        //PARTIE AJOUT DU LIEN ENTRE AVIATION ET LES SOUS-DOMAINES
        for (int i = 0; i < subdomains.size(); i++) {
            JSONArray link = new JSONArray();
                JSONObject linkData = new JSONObject();
                linkData.put("id", "e999" + "-" + subdomains.get(i).getTemp_id());
                linkData.put("source", 999);
                linkData.put("target", subdomains.get(i).getTemp_id());
                link.put(linkData);
                json.put(linkData);
        }
        
                
        return json.toString();
    }
}
