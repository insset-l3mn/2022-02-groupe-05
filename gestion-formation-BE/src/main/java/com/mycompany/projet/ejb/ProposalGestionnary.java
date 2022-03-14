/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfProposal;
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
public class ProposalGestionnary {

    @PersistenceContext
    private EntityManager em;
    
    public void createProposal(GfProposal proposal){
        em.persist(proposal);
    }
    
    public List readProposals(int qID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT g FROM GfProposal g WHERE g.idQuestion.idQuestion=:id ORDER BY g.state DESC").setParameter("id", qID);
        return query.getResultList();
    }
    
    public Boolean purgeProposalFromQuestion(int IDQuestion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.createQuery("DELETE FROM GfProposal WHERE idQuestion.idQuestion=:id")
                    .setParameter("id", IDQuestion)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Boolean updateProposal(int ID, String DENOMINATE, char STATE, int IDQuestion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try {
            /*em1.createQuery("UPDATE GfProposal p SET p.denominate=:denominate WHERE p.idProposal=:idProposal")
                    .setParameter("denominate", DENOMINATE)
                    .setParameter("state", STATE)
                    .setParameter("idQuestion", IDQuestion)
                    .setParameter("idProposal", ID)
                    .executeUpdate();*/
            em1.createQuery("DELETE FROM GfProposal WHERE idQuestion.idQuestion=:id")
                    .setParameter("id", IDQuestion)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
    