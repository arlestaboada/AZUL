/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge
 */
@Embeddable
public class MenuRolPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private int idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU")
    private int idMenu;

    public MenuRolPK() {
    }

    public MenuRolPK(int idRol, int idMenu) {
        this.idRol = idRol;
        this.idMenu = idMenu;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (int) idMenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuRolPK)) {
            return false;
        }
        MenuRolPK other = (MenuRolPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idMenu != other.idMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.MenuRolPK[ idRol=" + idRol + ", idMenu=" + idMenu + " ]";
    }
    
}
