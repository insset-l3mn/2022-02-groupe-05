/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "gf_course")
@NamedQueries({
    @NamedQuery(name = "GfCourse.findAll", query = "SELECT g FROM GfCourse g"),
    @NamedQuery(name = "GfCourse.findByIdCourse", query = "SELECT g FROM GfCourse g WHERE g.idCourse = :idCourse")})
public class GfCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_course")
    private Integer idCourse;
    @JoinColumn(name = "id_domaine", referencedColumnName = "id_domain")
    @ManyToOne(optional = false)
    private GfDomain idDomaine;
    @JoinColumn(name = "created_by", referencedColumnName = "id_user")
    @ManyToOne
    private User createdBy;
    
    @JsonbTransient
    @OneToMany(mappedBy = "idCourse")
    private Collection<User> userCollection;

    public GfCourse() {
    }

    public GfCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public GfDomain getIdDomaine() {
        return idDomaine;
    }

    public void setIdDomaine(GfDomain idDomaine) {
        this.idDomaine = idDomaine;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCourse != null ? idCourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfCourse)) {
            return false;
        }
        GfCourse other = (GfCourse) object;
        if ((this.idCourse == null && other.idCourse != null) || (this.idCourse != null && !this.idCourse.equals(other.idCourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfCourse[ idCourse=" + idCourse + " ]";
    }
    
}
