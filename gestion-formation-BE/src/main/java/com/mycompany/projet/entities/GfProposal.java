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
import javax.persistence.JoinColumn;
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
@Table(name = "gf_proposal")
@NamedQueries({
    @NamedQuery(name = "GfProposal.findAll", query = "SELECT g FROM GfProposal g"),
    @NamedQuery(name = "GfProposal.findByIdProposal", query = "SELECT g FROM GfProposal g WHERE g.idProposal = :idProposal"),
    @NamedQuery(name = "GfProposal.findByDenominate", query = "SELECT g FROM GfProposal g WHERE g.denominate = :denominate"),
    @NamedQuery(name = "GfProposal.findByState", query = "SELECT g FROM GfProposal g WHERE g.state = :state"),
    @NamedQuery(name = "GfProposal.findByIdQuestion", query = "SELECT g FROM GfProposal g WHERE g.idQuestion =:idQuestion")})
public class GfProposal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proposal", nullable = false)
    private Integer idProposal;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "denominate", nullable = false, length = 50)
    private String denominate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "state", nullable = false)
    private short state;
    
    @JoinColumn(name = "id_question", referencedColumnName = "id_question")
    @ManyToOne(optional = false)
    private GfQuestion idQuestion;
    

    public GfProposal() {
    }

    public GfProposal(Integer idProposal) {
        this.idProposal = idProposal;
    }

    /*public GfProposal(Integer idProposal, String denominate, short state) {
        this.idProposal = idProposal;
        this.denominate = denominate;
        this.state = state;
    }*/
    
    public GfProposal(GfQuestion idQuestion, String denominate, short state) {
        this.idQuestion = idQuestion;
        this.denominate = denominate;
        this.state = state;
    }

    public Integer getIdProposal() {
        return idProposal;
    }

    public void setIdProposal(Integer idProposal) {
        this.idProposal = idProposal;
    }

    public String getDenominate() {
        return denominate;
    }

    public void setDenominate(String denominate) {
        this.denominate = denominate;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public GfQuestion getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(GfQuestion idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProposal != null ? idProposal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GfProposal)) {
            return false;
        }
        GfProposal other = (GfProposal) object;
        if ((this.idProposal == null && other.idProposal != null) || (this.idProposal != null && !this.idProposal.equals(other.idProposal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.GfProposal[ idProposal=" + idProposal + " ]";
    }
    
}
