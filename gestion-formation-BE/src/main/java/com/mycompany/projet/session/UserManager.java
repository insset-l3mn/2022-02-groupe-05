/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.mycompany.projet.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.mycompany.projet.entities.User;
/**
 *
 * @author valen
 */
@Stateless
public class UserManager {

    @PersistenceContext(unitName = "userPU")
    private EntityManager em;
     
    public void addUser(User c){
        em.persist(c);
    }
    
    public List<User> getAllUsers(){
        Query query = em.createNamedQuery("User.findAll");
        return query.getResultList();
    }
    
    public User getUser(int userId) {
        return em.find(User.class, userId); 
    }

    public User update(User c) {
      return em.merge(c); 
    } 
}
