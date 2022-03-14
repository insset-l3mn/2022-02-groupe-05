/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author valen
 */
@Embeddable
public class UserHasSkillPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_skill")
    private int idSkill;

    public UserHasSkillPK() {
    }

    public UserHasSkillPK(int idUser, int idSkill) {
        this.idUser = idUser;
        this.idSkill = idSkill;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser;
        hash += (int) idSkill;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasSkillPK)) {
            return false;
        }
        UserHasSkillPK other = (UserHasSkillPK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idSkill != other.idSkill) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projet.entities.UserHasSkillPK[ idUser=" + idUser + ", idSkill=" + idSkill + " ]";
    }
    
}
