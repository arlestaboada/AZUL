/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "MENU_ROL",schema = "BD_CVERDE")
public class MenuRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MenuRolPK menuRolPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_REGISTRO")
    private Character estadoRegistro;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;

    public MenuRol() {
    }

    public MenuRol(MenuRolPK menuRolPK) {
        this.menuRolPK = menuRolPK;
    }

    public MenuRol(MenuRolPK menuRolPK, Character estadoRegistro) {
        this.menuRolPK = menuRolPK;
        this.estadoRegistro = estadoRegistro;
    }

    public MenuRol(int idRol, int idMenu) {
        this.menuRolPK = new MenuRolPK(idRol, idMenu);
    }

    public MenuRolPK getMenuRolPK() {
        return menuRolPK;
    }

    public void setMenuRolPK(MenuRolPK menuRolPK) {
        this.menuRolPK = menuRolPK;
    }

    public Character getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Character estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuRolPK != null ? menuRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuRol)) {
            return false;
        }
        MenuRol other = (MenuRol) object;
        if ((this.menuRolPK == null && other.menuRolPK != null) || (this.menuRolPK != null && !this.menuRolPK.equals(other.menuRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.MenuRol[ menuRolPK=" + menuRolPK + " ]";
    }
    
}
