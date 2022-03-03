/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfQuestion;
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
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/gestion-formation",
    serverName="localhost",
    portNumber=3306,
    user="root",
    password="root",
    databaseName="gestion-formation",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class QuestionGestionnary {

    @PersistenceContext
    private EntityManager em;
    
    public void createQuestion(GfQuestion question){
        em.persist(question);
    }
    
    public Boolean existQuestion(String contents) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT q FROM GfQuestion q WHERE q.contents = '" + contents + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
}
    