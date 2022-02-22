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
    name="java:app/jdbc/projetjee",
    serverName="localhost",
    portNumber=3306,
    user="root",
    password="Cng162513*",
    databaseName="projetjee",
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
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.userName = '" + NAME + "'");
        
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
        Query query = em1.createQuery("SELECT u FROM User u WHERE u.userName = '" + NAME + "'");
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }
}
    