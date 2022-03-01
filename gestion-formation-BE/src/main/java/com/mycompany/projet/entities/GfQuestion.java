/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "gf_question")
@NamedQueries({
    @NamedQuery(name = "GfQuestion.findAll", query = "SELECT g FROM GfQuestion g"),
    @NamedQuery(name = "GfQuestion.findByIdQuestion", query = "SELECT g FROM GfQuestion g WHERE g.idQuestion = :idQuestion"),
    @NamedQuery(name = "GfQuestion.findByLevel", query = "SELECT g FROM GfQuestion g WHERE g.level = :level"),
    @NamedQuery(name = "GfQuestion.findByDifficulty", query = "SELECT g FROM GfQuestion g WHERE g.difficulty = :difficulty")})
public class GfQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_question")
    private Integer idQuestion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "level")
    private String level;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "difficulty")
    private String difficulty;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contents")
    private String contents;
    @JoinTable(name = "former_manage_question", joinColumns = {
        @JoinColumn(name = "id_question", referencedColumnName = "id_question")}, inverseJoinColumns = {
        @JoinColumn(name = "id_trainer", referencedColumnName = "id_user")})
    @ManyToMany
    private Collection<User> userCollection;
    @JoinColumn(name = "id_domain", referencedColumnName = "id_domain")
    @ManyToOne(optional = false)
    private GfDomain idDomain;
    @JoinColumn(name = "id_skill", referencedColumnName = "id_skill")
    @ManyToOne(optional = false)
    private GfSkill idSkill;

    public GfQuestion() {
    }

    public GfQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public GfQuestion(String level, String difficulty, String contents) {
        this.level = level;
        this.difficulty = difficulty;
        this.contents = contents;
    }
    
    /*public GfQuestion(Integer idQuestion, String level, String difficulty, String contents) {
        this.idQuestion = idQuestion;
        this.level = level;
        this.difficulty = difficulty;
        this.contents = contents;
    }*/

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public GfDomain getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(GfDomain idDomain) {
        this.idDomain = idDomain;
    }

    public GfSkill getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(GfSkill idSkill) {
        this.idSkill = idSkill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestion != null ? idQuestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfQuestion)) {
            return false;
        }
        GfQuestion other = (GfQuestion) object;
        if ((this.idQuestion == null && other.idQuestion != null) || (this.idQuestion != null && !this.idQuestion.equals(other.idQuestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfQuestion[ idQuestion=" + idQuestion + " ]";
    }
    
}
