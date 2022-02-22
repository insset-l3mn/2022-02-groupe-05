/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.projet.managedbeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import com.mycompany.projet.entities.User;
import com.mycompany.projet.session.UserManager;

/**
 *
 * @author valen
 */
@Named(value = "UserMBean")
@ViewScoped
public class UserMBean implements Serializable {
    private List<User> userList;
    private User user;
    
    @EJB
    private UserManager userManager;
    
    
    public UserMBean() {
    }
    
    public List<User> getAllComptes() {
        if (userList == null) {
            userList = userManager.getAllUsers();
        }
        return userList;
    }
    
    /*public CompteBancaire getCompteBancaire(int idCompteBancaire) {
        return null;
    }
    
    public String update() {
        compteBancaire = compteBancaireManager.update(compteBancaire);
        return "CompteBancaireList";
    }*/
}
