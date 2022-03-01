/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.projet.jsf;

import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author valen
 */
@Named(value = "addUser")
@ViewScoped
public class AddUser implements Serializable{
    
    @EJB
    private UserGestionnary userGestionnary;

    private String name, email, password, role;
    
    public String getName() {
        return name;
    }

    public void setName(String NAME) {
        this.name = NAME;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String EMAIL) {
        this.email = EMAIL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String PASSWORD) {
        this.password = PASSWORD;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String ROLE) {
        this.role = ROLE;
    }

    public String createUser() {
        if(name.length() > 1 && email.length() > 6 && password.length() > 8){
            userGestionnary.createUser(new User(name, email, password, role));
            return "login?&msg=1&faces-redirect=true";
        }else{
            return "register?error=1&faces-redirect=true";
        }
    }
}
