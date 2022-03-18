/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfSkill;
import com.mycompany.projet.entities.Message;
import com.mycompany.projet.entities.UserHasSkill;
import com.mycompany.projet.entities.UserHasSkillPK;
import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author valen
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/gestion-formation",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        databaseName = "gestion-formation",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class UserHasSkillGestionnary {

    @PersistenceContext
    private EntityManager em;

    //INSERT INTO `gestion-formation`.user_has_skill (id_user, id_skill, score, malus, successive_error) VALUES (1, 1, 1, 0, 0)
    public void addSkillToUser(UserHasSkill userHasSkill) {
        //Vérifier si l'user ne possède pas déjà ce skill avant de l'ajouter.
        if(!existSkill(userHasSkill.getUserHasSkillPK().getIdUser(), userHasSkill.getUserHasSkillPK().getIdSkill())){
            em.persist(userHasSkill);
        }
    }

    public int count(int userID) {
        return em.createQuery("SELECT u FROM UserHasSkill u WHERE u.user.id_user=:idUser")
                .setParameter("idUser", userID)
                .getResultList().size();
    }

    public void remove(int userId, int skillId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        em1.createQuery("DELETE FROM UserHasSkill u WHERE (u.userHasSkillPK.idUser=:userId AND u.userHasSkillPK.idSkill=:skillId)")
                .setParameter("userId", userId)
                .setParameter("skillId", skillId)
                .executeUpdate();
    }
    
    public void removeAll(int userId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        em1.createQuery("DELETE FROM UserHasSkill u WHERE (u.userHasSkillPK.idUser=:userId)")
                .setParameter("userId", userId)
                .executeUpdate();
    }
    
    public void removeBySubdomainId(int userId, int subdomainId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        List<GfSkill> skills = em1.createQuery("SELECT g FROM GfSkill g WHERE g.idSubdomain.idSubdomain=:subdomainId")
                .setParameter("subdomainId", subdomainId)
                .getResultList();
        
        for(int i = 0; i < skills.size(); i++){
            remove(userId, skills.get(i).getIdSkill());
        }
    }
    
    public void addAll(int userId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        List<GfSkill> skills = em1.createQuery("SELECT g FROM GfSkill g")
                .getResultList();
        
        for(int i = 0; i < skills.size(); i++){
            UserHasSkillPK relation = new UserHasSkillPK(userId, skills.get(i).getIdSkill());
            UserHasSkill userHasSkill = new UserHasSkill(relation, 0, 0);
            
            addSkillToUser(userHasSkill);//ERROR Verifier si l'user ne possède pas déjà ce skill
        }
    }
    
    public void addBySubdomain(int userId, int subdomainId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        List<GfSkill> skills = em1.createQuery("SELECT g FROM GfSkill g WHERE g.idSubdomain.idSubdomain=:subdomainId")
                .setParameter("subdomainId", subdomainId)
                .getResultList();
        
        for(int i = 0; i < skills.size(); i++){
            UserHasSkillPK relation = new UserHasSkillPK(userId, skills.get(i).getIdSkill());
            UserHasSkill userHasSkill = new UserHasSkill(relation, 0, 0);
            
            addSkillToUser(userHasSkill);//ERROR Verifier si l'user ne possède pas déjà ce skill
        }
    }
    
    public boolean existSkill(int userId, int skillId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM UserHasSkill s WHERE (s.userHasSkillPK.idSkill=:skillId AND s.userHasSkillPK.idUser=:userId)")
                .setParameter("skillId", skillId)
                .setParameter("userId", userId);
        
        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
}
