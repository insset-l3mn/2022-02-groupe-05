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
    
    public Boolean existUser(String NAME) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.name = '" + NAME + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean existUser(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id_user = '" + id + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
    
    public Boolean updateUser(int ID, String USERNAME, String EMAIL, String PASSWORD) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        //User u = new User(USERNAME, EMAIL, PASSWORD);
        try{
            em1.createQuery("UPDATE User u SET u.name='" + USERNAME + "', u.email='"+ EMAIL +"', u.password='"+ PASSWORD +"' WHERE u.id_user = '" + ID + "'").executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    /*public User getUser(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userPU");
        EntityManager em1 = emf.createEntityManager();
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.id = '" + id + "'");
        
        if(query.getResultList().isEmpty()) return null;
        return query;
    }*/
}
    