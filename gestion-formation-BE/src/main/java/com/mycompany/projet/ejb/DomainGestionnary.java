/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfDomain;
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
public class DomainGestionnary {

    @PersistenceContext
    private EntityManager em;
    
    public void createDomain(GfDomain domain){
        em.persist(domain);
    }
    
    public GfDomain requestDomain(String DENOMINATE) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT d FROM GfDomain d WHERE d.denominate = :denominate")
                .setParameter("denominate", DENOMINATE);
        
        if(!query.getResultList().isEmpty()){
            return (GfDomain) query.getResultList().get(0);
        }
        
        return null;
    }
    
    public Boolean existDomain(String denominate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT d FROM GfDomain d WHERE d.denominate = '" + denominate + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean existDomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT d FROM GfDomain d WHERE d.idDomain = '" + id + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean removeDomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        
        try{
            em1.createQuery("DELETE FROM GfDomain WHERE idDomain=" + id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public Boolean updateDomain(int ID, String DENOMINATE) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try{
            em1.createQuery("UPDATE GfDomain d SET d.denominate='" + DENOMINATE +"' WHERE d.idDomain = '" + ID + "'").executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public GfDomain readDomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT d FROM GfDomain d WHERE d.idDomain = '" + id + "'");
        GfDomain domain = null;
        
        if(!query.getResultList().isEmpty()){
            domain = (GfDomain) query.getResultList().get(0);
        }
        
        return domain;
    }
}
    