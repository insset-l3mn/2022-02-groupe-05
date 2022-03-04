/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "gf_domain")
@NamedQueries({
    @NamedQuery(name = "GfDomain.findAll", query = "SELECT g FROM GfDomain g"),
    @NamedQuery(name = "GfDomain.findByIdDomain", query = "SELECT g FROM GfDomain g WHERE g.idDomain = :idDomain"),
    @NamedQuery(name = "GfDomain.findByDenominate", query = "SELECT g FROM GfDomain g WHERE g.denominate = :denominate")})
public class GfDomain implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "denominate")
    private String denominate;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_domain")
    private Integer idDomain;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDomaine")
    @JsonbTransient//Perpet de dire que l'on ne veut pas ce champ dans le JSON
    private Collection<GfCourse> gfCourseCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDomain")
    @JsonbTransient
    private Collection<GfQuestion> gfQuestionCollection;

    public GfDomain() {
    }

    public GfDomain(Integer idDomain) {
        this.idDomain = idDomain;
    }
    
    public GfDomain(String denominate) {
        this.denominate = denominate;
    }

    public GfDomain(Integer idDomain, String denominate) {
        this.idDomain = idDomain;
        this.denominate = denominate;
    }

    public Integer getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(Integer idDomain) {
        this.idDomain = idDomain;
    }

    public Collection<GfCourse> getGfCourseCollection() {
        return gfCourseCollection;
    }

    public void setGfCourseCollection(Collection<GfCourse> gfCourseCollection) {
        this.gfCourseCollection = gfCourseCollection;
    }

    public Collection<GfQuestion> getGfQuestionCollection() {
        return gfQuestionCollection;
    }

    public void setGfQuestionCollection(Collection<GfQuestion> gfQuestionCollection) {
        this.gfQuestionCollection = gfQuestionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomain != null ? idDomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfDomain)) {
            return false;
        }
        GfDomain other = (GfDomain) object;
        if ((this.idDomain == null && other.idDomain != null) || (this.idDomain != null && !this.idDomain.equals(other.idDomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfDomain[ idDomain=" + idDomain + " ]";
    }

    public String getDenominate() {
        return denominate;
    }

    public void setDenominate(String denominate) {
        this.denominate = denominate;
    }
    
}
