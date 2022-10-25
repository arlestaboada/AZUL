/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.controlador.backingBean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.gob.minam.sistema.entidades.Rol;

/**
 *
 * @author Jorge
 */

@Named("BKRol")
@Scope("session")
public class BKRol implements Serializable{
    
    private Rol rolBusqueda;
    private Rol rolFormulario;
    private Rol rolSeleccionado;
    private List<Rol> listaRoles;

    public BKRol() {
        
        listaRoles = new ArrayList<>();
    }

    
    
    public Rol getRolBusqueda() {
        return rolBusqueda;
    }

    public void setRolBusqueda(Rol rolBusqueda) {
        this.rolBusqueda = rolBusqueda;
    }

    public Rol getRolFormulario() {
        return rolFormulario;
    }

    public void setRolFormulario(Rol rolFormulario) {
        this.rolFormulario = rolFormulario;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }
            
    
}
