/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.ejb;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.projet.entities.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class UserGestionnary {

    @PersistenceContext
    private EntityManager em;
    
    public void createUser(User user){
        em.persist(user);
    }

    public User requestUser(String NAME, String PASSWORD) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.name = :name")
                .setParameter("name", NAME);
        User user = null;
        
        if(!query.getResultList().isEmpty()){
            user = (User) query.getResultList().get(0);
            if(!user.getUserPassword().equals(PASSWORD))user = null;
        }
        
        return user;
    }

    public User requestUser(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user=:id")
                .setParameter("id", id);
        User user = null;
        
        if(!query.getResultList().isEmpty()){
            user = (User) query.getResultList().get(0);
        }
        
        return user;
    }
    
    public Boolean isVisitor(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user=:id")
                .setParameter("id", id);
        
        if(!query.getResultList().isEmpty()){
            User user = (User) query.getResultList().get(0);
            if("VISITOR".equals(user.getUserRole())) return true;
        }else if(query.getResultList().isEmpty()){
            return true;
        }
        
        return false;
    }
    
    public Boolean isAdmin(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user=:id")
                .setParameter("id", id);
        
        if(!query.getResultList().isEmpty()){
            User user = (User) query.getResultList().get(0);
            if("ADMIN".equals(user.getUserRole())) return true;
        }
        
        return false;
    }
    
    public Boolean existUser(String NAME) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.name=:name")
                .setParameter("name", NAME);;
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean existUser(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user=:id")
                .setParameter("id", id);
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean updateUser(int ID, String USERNAME, String EMAIL, String PASSWORD) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        //User u = new User(USERNAME, EMAIL, PASSWORD);
        try{
            em1.createQuery("UPDATE User u SET u.name=:username, u.email=:email, u.password=:password WHERE u.id_user=:id")
                    .setParameter("username", USERNAME)
                    .setParameter("email", EMAIL)
                    .setParameter("password", PASSWORD)
                    .setParameter("id", ID)
                    .executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public User readUser(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user=:id")
                .setParameter("id", id);

        User user = null;

        if (!query.getResultList().isEmpty()) {
            user = (User) query.getResultList().get(0);
        }

        return user;
    }
    
    public List readUsers(int count, int startAt) {
        return em.createQuery(
                "SELECT u FROM User u")
                .setFirstResult(startAt)
                .setMaxResults(count)
                .getResultList();
    }
    
    public int countUsers() {
        return em.createQuery("SELECT s FROM User s").getResultList().size();
    }
}
    