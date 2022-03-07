/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfSubdomain;
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
public class SubdomainGestionnary {

    @PersistenceContext
    private EntityManager em;

    public void createSubdomain(GfSubdomain subomain) {
        em.persist(subomain);
    }
    
    public Boolean removeSubdomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        try {
            em1.createQuery("DELETE FROM GfSubdomain WHERE idSubdomain=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean existSubdomain(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSubdomain s WHERE s.name=:name")
                .setParameter("name", name);

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public Boolean existSubdomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSubdomain s WHERE s.idSubdomain=:id")
                .setParameter("id", id);

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public static GfSubdomain requestSubdomain(String NAME) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSubdomain s WHERE s.name=:name")
                .setParameter("name", NAME);

        if (!query.getResultList().isEmpty()) {
            return (GfSubdomain) query.getResultList().get(0);
        }

        return null;
    }
    
    public Boolean updateSubdomain(int ID, String NAME) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.createQuery("UPDATE GfSubdomain s SET s.name=:name WHERE s.idSubdomain=:id")
                    .setParameter("name", NAME)
                    .setParameter("id", ID)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Boolean purgeSubdomainFromSkills(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.createQuery("DELETE FROM GfSkill WHERE idSubdomain.idSubdomain=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public GfSubdomain readSubdomain(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSubdomain s WHERE s.idSubdomain=:id")
                .setParameter("id", id);

        GfSubdomain subdomain = null;

        if (!query.getResultList().isEmpty()) {
            subdomain = (GfSubdomain) query.getResultList().get(0);
        }

        return subdomain;
    }

    public List readSubdomains(int count, int startAt) {
        return em.createQuery(
                "SELECT s FROM GfSubdomain s")
                .setFirstResult(startAt)
                .setMaxResults(count)
                .getResultList();
    }
    
    public int countSubdomains() {
        return em.createQuery("SELECT s FROM GfSubdomain s").getResultList().size();
    }
}
