/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author valen
 */
@Entity
@Table(name = "user_has_skill")
@NamedQueries({
    @NamedQuery(name = "UserHasSkill.findAll", query = "SELECT u FROM UserHasSkill u"),
    @NamedQuery(name = "UserHasSkill.findByIdUser", query = "SELECT u FROM UserHasSkill u WHERE u.userHasSkillPK.idUser = :idUser"),
    @NamedQuery(name = "UserHasSkill.findByIdSkill", query = "SELECT u FROM UserHasSkill u WHERE u.userHasSkillPK.idSkill = :idSkill"),
    @NamedQuery(name = "UserHasSkill.findByScore", query = "SELECT u FROM UserHasSkill u WHERE u.score = :score"),
    @NamedQuery(name = "UserHasSkill.findByMalus", query = "SELECT u FROM UserHasSkill u WHERE u.malus = :malus"),
    @NamedQuery(name = "UserHasSkill.findBySuccessiveError", query = "SELECT u FROM UserHasSkill u WHERE u.successiveError = :successiveError")})
public class UserHasSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasSkillPK userHasSkillPK;
    @Column(name = "score")
    private Integer score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "malus")
    private int malus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "successive_error")
    private int successiveError;
    @JoinColumn(name = "id_skill", referencedColumnName = "id_skill", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GfSkill gfSkill;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserHasSkill() {
    }

    public UserHasSkill(UserHasSkillPK userHasSkillPK) {
        this.userHasSkillPK = userHasSkillPK;
    }

    public UserHasSkill(UserHasSkillPK userHasSkillPK, int malus, int successiveError) {
        this.userHasSkillPK = userHasSkillPK;
        this.malus = malus;
        this.successiveError = successiveError;
    }

    public UserHasSkill(int idUser, int idSkill) {
        this.userHasSkillPK = new UserHasSkillPK(idUser, idSkill);
    }

    public UserHasSkillPK getUserHasSkillPK() {
        return userHasSkillPK;
    }

    public void setUserHasSkillPK(UserHasSkillPK userHasSkillPK) {
        this.userHasSkillPK = userHasSkillPK;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getMalus() {
        return malus;
    }

    public void setMalus(int malus) {
        this.malus = malus;
    }

    public int getSuccessiveError() {
        return successiveError;
    }

    public void setSuccessiveError(int successiveError) {
        this.successiveError = successiveError;
    }

    public GfSkill getGfSkill() {
        return gfSkill;
    }

    public void setGfSkill(GfSkill gfSkill) {
        this.gfSkill = gfSkill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHasSkillPK != null ? userHasSkillPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasSkill)) {
            return false;
        }
        UserHasSkill other = (UserHasSkill) object;
        if ((this.userHasSkillPK == null && other.userHasSkillPK != null) || (this.userHasSkillPK != null && !this.userHasSkillPK.equals(other.userHasSkillPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.UserHasSkill[ userHasSkillPK=" + userHasSkillPK + " ]";
    }
    
}
