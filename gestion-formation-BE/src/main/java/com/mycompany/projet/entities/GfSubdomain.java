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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "gf_subdomain")
@NamedQueries({
    @NamedQuery(name = "GfSubdomain.findAll", query = "SELECT g FROM GfSubdomain g"),
    @NamedQuery(name = "GfSubdomain.findByIdSubdomain", query = "SELECT g FROM GfSubdomain g WHERE g.idSubdomain = :idSubdomain"),
    @NamedQuery(name = "GfSubdomain.findByName", query = "SELECT g FROM GfSubdomain g WHERE g.name = :name")})
public class GfSubdomain implements Serializable {
    @Transient
    private int temp_id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subdomain")
    private Integer idSubdomain;
    
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubdomain")
    private Collection<GfSkill> gfSkillCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubdomain")
    private Collection<GfCourse> gfCourseCollection;
    
    public GfSubdomain() {
    }

    public GfSubdomain(Integer idSubdomain) {
        this.idSubdomain = idSubdomain;
    }
    
    public GfSubdomain(String name) {
        this.name = name;
    }

    public GfSubdomain(Integer idSubdomain, String name) {
        this.idSubdomain = idSubdomain;
        this.name = name;
    }

    public Integer getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(Integer idSubdomain) {
        this.idSubdomain = idSubdomain;
    }


    public Collection<GfSkill> getGfSkillCollection() {
        return gfSkillCollection;
    }

    public void setGfSkillCollection(Collection<GfSkill> gfSkillCollection) {
        this.gfSkillCollection = gfSkillCollection;
    }

    public Collection<GfCourse> getGfCourseCollection() {
        return gfCourseCollection;
    }

    public void setGfCourseCollection(Collection<GfCourse> gfCourseCollection) {
        this.gfCourseCollection = gfCourseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubdomain != null ? idSubdomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfSubdomain)) {
            return false;
        }
        GfSubdomain other = (GfSubdomain) object;
        if ((this.idSubdomain == null && other.idSubdomain != null) || (this.idSubdomain != null && !this.idSubdomain.equals(other.idSubdomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfSubdomain[ idSubdomain=" + idSubdomain + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(int temp_id) {
        this.temp_id = temp_id;
    }
}
