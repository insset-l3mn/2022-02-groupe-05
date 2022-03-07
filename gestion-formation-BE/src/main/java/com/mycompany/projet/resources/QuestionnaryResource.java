/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.resources;

import com.mycompany.projet.ejb.QuestionGestionnary;
import com.mycompany.projet.ejb.SkillGestionnary;
import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.Message;
import com.mycompany.projet.entities.User;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author valen
 */
@Path("/questionnary")
public class QuestionnaryResource {
    
    @EJB
    private QuestionGestionnary questionGestionnary;

    @EJB
    private SkillGestionnary skillGestionnary;
    
    @EJB
    private UserGestionnary userGestionnary;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{id}")
    public Message addQuestionnary(@PathParam("id") int id) {
        User user = userGestionnary.requestUser(id);
        if(user != null){
            //userGestionnary.setSurvey(id, questionnaryGestionnary.readQuestionnary(questionnaryGestionnary.countQuestionnaries()));
        }else{
            return new Message("error", "Une erreur est survenue lors de la création d'un questionnaire.");
        }
        
        
        int nbrQuestion = 0;
        int nbrSkill = skillGestionnary.countSkills();
        
        return new Message("info", "" + nbrSkill);
    }
    
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Path("/next/{userId}")
    public void updateQuestion(@PathParam("id") int id) {
        //Entrée : IdQuestion ; Answer
        
        /**Traitemement : 
         * Si mauvaise réponse alors
         *      Si le nombre d'echec d'une compétence de suite est plus grand que 3 dans le niveau de la question, ajouter 5 au malus
         *      Sinon appliquer un malus de +(4 - niveau de la question) sur la compétence. (niveau max d'une question = 3)
         *      nbrEchecCompetence++
         * Sinon -1 au malus si elui-ci est > 0
         *      nbrEchecCompetence = 0
         *      Si niveauDifficulté plus petit que 3
         *      niveauDifficulté += 0.5 (pour choisir une question on prend un arrondi inférieur)
         * 
         * 
         * Si le score du malus atteint 10 alors on ne pose plus de question sur cette compétence
         *
         * Chercher une question dont le malus d'une compétence n'est pas à 10 et où le score atteint est inférieur à 20
         * 
         */
    }
}
