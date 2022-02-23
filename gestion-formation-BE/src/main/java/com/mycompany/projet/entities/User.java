/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "gf_user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.id_user = :id_user"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUserPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer id_user;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    public User() {
    }

    /*public User(Integer userId) {
        this.userId = userId;
    }*/
    
    public User(String NAME, String EMAIL, String PASSWORD) {
        this.name = NAME;
        this.email = EMAIL;
        this.password = PASSWORD;
    }

    public Integer getUserId() {
        return id_user;
    }

    public void setUserId(Integer userId) {
        this.id_user = userId;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_user != null ? id_user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id_user == null && other.id_user != null) || (this.id_user != null && !this.id_user.equals(other.id_user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.User[ id_user=" + id_user + " ]";
    }
    
}
