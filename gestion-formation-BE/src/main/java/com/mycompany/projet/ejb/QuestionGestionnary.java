/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfQuestion;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class QuestionGestionnary {

    @PersistenceContext
    private EntityManager em;

    public void createQuestion(GfQuestion question) {
        em.persist(question);
    }

    public Boolean existQuestion(String contents) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.contents = '" + contents + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean existQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.idQuestion = '" + id + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public Boolean updateQuestion(int id, String level, String difficulty, String contents, String domainName, String skillName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        
        int idDomain;
        int idSkill;
        try{
            idDomain = DomainGestionnary.requestDomain(domainName).getIdDomain();
            idSkill = SkillGestionnary.requestSkill(skillName).getIdSkill();
        }catch(Exception e){
            return false;
        }
        
        try {
            em1.createQuery("UPDATE GfQuestion q SET q.level='" + level + "', q.difficulty='" + difficulty + "', q.contents='"+ contents +"', q.idDomain='"+ idDomain +"', q.idSkill='"+ idSkill +"' WHERE q.idQuestion = '" + id + "'").executeUpdate();
            //em1.createQuery("UPDATE GfQuestion q SET q.level='0', q.difficulty='0', q.contents='Hello', q.idDomain='6', q.idSkill='10' WHERE q.idQuestion='20'").executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        try {
            em1.createQuery("DELETE FROM GfQuestion WHERE idQuestion=" + id).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public GfQuestion readQuestion(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.idQuestion = '" + id + "'");

        GfQuestion question = null;

        if (!query.getResultList().isEmpty()) {
            question = (GfQuestion) query.getResultList().get(0);
        }

        return question;
    }
    
    public GfQuestion readQuestion(String str) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.contents = '" + str + "'");

        GfQuestion question = null;

        if (!query.getResultList().isEmpty()) {
            question = (GfQuestion) query.getResultList().get(0);
        }

        return question;
    }

    public List readQuestions(int count, int startAt) {
        return em.createQuery(
                "SELECT q FROM GfQuestion q")
                .setFirstResult(startAt)
                .setMaxResults(count)
                .getResultList();
    }
}
