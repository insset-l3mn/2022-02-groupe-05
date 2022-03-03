/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import com.mycompany.projet.entities.GfSkill;
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
public class SkillGestionnary {

    @PersistenceContext
    private EntityManager em;

    public void createSkill(GfSkill skill) {
        em.persist(skill);
    }

    public GfSkill requestSkill(String NAME) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSkill s WHERE s.name = :name").setParameter("name", NAME);

        if (!query.getResultList().isEmpty()) {
            return (GfSkill) query.getResultList().get(0);
        }

        return null;
    }

    public Boolean existSkill(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSkill s WHERE s.name = '" + name + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean existSkill(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSkill s WHERE s.idSkill = '" + id + "'");

        if (query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean removeSkill(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();

        try {
            em1.createQuery("DELETE FROM GfSkill WHERE idSkill=" + id).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateSkill(int ID, String NAME, String WEIGHT) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.createQuery("UPDATE GfSkill s SET s.name='" + NAME + "', s.weight='" + WEIGHT + "' WHERE s.idSkill = '" + ID + "'").executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public GfSkill readSkill(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT s FROM GfSkill s WHERE s.idSkill = '" + id + "'");

        GfSkill skill = null;

        if (!query.getResultList().isEmpty()) {
            skill = (GfSkill) query.getResultList().get(0);
        }

        return skill;
    }

    public List readSkills(int count, int startAt) {
        return em.createQuery(
                "SELECT s FROM GfSkill s")
                .setFirstResult(startAt)
                .setMaxResults(count)
                .getResultList();
    }
}
