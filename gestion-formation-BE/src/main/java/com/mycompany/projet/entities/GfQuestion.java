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
    @NamedQuery(name = "GfQuestion.findByDifficulty", query = "SELECT g FROM GfQuestion g WHERE g.difficulty = :difficulty")})
public class GfQuestion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "difficulty", nullable = false)
    private int difficulty;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contents", nullable = false, length = 65535)
    
    private String contents;
    @JoinTable(name = "survey_has_question", joinColumns = {
        @JoinColumn(name = "id_question", referencedColumnName = "id_question", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_survey", referencedColumnName = "id_survey", nullable = false)})

    
    @JoinColumn(name = "id_trainer", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idTrainer;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_question")
    private Integer idQuestion;
    
    @JoinColumn(name = "id_skill", referencedColumnName = "id_skill")
    @ManyToOne(optional = false)
    private GfSkill idSkill;

    public GfQuestion() {
    }

    public GfQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public GfQuestion(Integer difficulty, String contents, GfSkill skill, User user) {
        this.idSkill = skill;
        this.difficulty = difficulty;
        this.contents = contents;
        this.idTrainer = user;
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


    public User getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(User idTrainer) {
        this.idTrainer = idTrainer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
