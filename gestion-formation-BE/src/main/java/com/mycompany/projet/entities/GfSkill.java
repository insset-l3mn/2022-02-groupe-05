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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "gf_skill")
@NamedQueries({
    @NamedQuery(name = "GfSkill.findAll", query = "SELECT g FROM GfSkill g"),
    @NamedQuery(name = "GfSkill.findByIdSkill", query = "SELECT g FROM GfSkill g WHERE g.idSkill = :idSkill"),
    @NamedQuery(name = "GfSkill.findByName", query = "SELECT g FROM GfSkill g WHERE g.name = :name"),
    @NamedQuery(name = "GfSkill.findByWeight", query = "SELECT g FROM GfSkill g WHERE g.weight = :weight")})
public class GfSkill implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private int weight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gfSkill")
    private Collection<UserHasSkill> userHasSkillCollection;
    
    @JoinColumn(name = "id_subdomain", referencedColumnName = "id_subdomain")
    @ManyToOne(optional = false)
    private GfSubdomain idSubdomain;
    
    @JsonbTransient
    @ManyToMany(mappedBy = "gfSkillCollection")
    private Collection<GfSkill> gfSkillCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_skill")
    private Integer idSkill;
    
    @JsonbTransient
    @JoinTable(name = "prerequisite_of_skill", joinColumns = {
        @JoinColumn(name = "id_skill", referencedColumnName = "id_skill")}, inverseJoinColumns = {
        @JoinColumn(name = "id_skill_prerequisite", referencedColumnName = "id_skill")})
    @ManyToMany
    private Collection<GfSkill> gfSkillCollection;
    

    
    @JoinColumn(name = "id_trainer", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idTrainer;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSkill")
    private Collection<GfQuestion> gfQuestionCollection;

    public GfSkill() {
    }

    public GfSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public GfSkill(User idTrainer, String name, Integer weight, GfSubdomain idSubdomain) {
        this.idTrainer = idTrainer;
        this.name = name;
        this.weight = weight;
        this.idSubdomain = idSubdomain;
    }
    
    /*public GfSkill(Integer idSkill, String name, String weight) {
        this.idSkill = idSkill;
        this.name = name;
        this.weight = weight;
    }*/

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }


    public Collection<GfSkill> getGfSkillCollection() {
        return gfSkillCollection;
    }

    public void setGfSkillCollection(Collection<GfSkill> gfSkillCollection) {
        this.gfSkillCollection = gfSkillCollection;
    }

    public User getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(User idTrainer) {
        this.idTrainer = idTrainer;
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
        hash += (idSkill != null ? idSkill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfSkill)) {
            return false;
        }
        GfSkill other = (GfSkill) object;
        if ((this.idSkill == null && other.idSkill != null) || (this.idSkill != null && !this.idSkill.equals(other.idSkill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfSkill[ idSkill=" + idSkill + " ]";
    }


    public Collection<GfSkill> getGfSkillCollection1() {
        return gfSkillCollection1;
    }

    public void setGfSkillCollection1(Collection<GfSkill> gfSkillCollection1) {
        this.gfSkillCollection1 = gfSkillCollection1;
    }


    public GfSubdomain getIdSubdomain() {
        return idSubdomain;
    }

    public void setIdSubdomain(GfSubdomain idSubdomain) {
        this.idSubdomain = idSubdomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Collection<UserHasSkill> getUserHasSkillCollection() {
        return userHasSkillCollection;
    }

    public void setUserHasSkillCollection(Collection<UserHasSkill> userHasSkillCollection) {
        this.userHasSkillCollection = userHasSkillCollection;
    }
    
}
