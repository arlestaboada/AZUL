/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jorge
 */

public class DtoPostulanteUsuario implements Serializable {

  
    private Integer idpostulante;
   
    private String usuario;
  
    private String password;
   
    private int estado;
  
    private Date fechaIns;
 
    private Integer postulante;

    public DtoPostulanteUsuario() {
    }

    public DtoPostulanteUsuario(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

    public DtoPostulanteUsuario(Integer idpostulante, String usuario, String password, int estado, Date fechaIns) {
        this.idpostulante = idpostulante;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
        this.fechaIns = fechaIns;
    }

    public Integer getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    public Integer getPostulante() {
        return postulante;
    }

    public void setPostulante(Integer postulante) {
        this.postulante = postulante;
    }


  
}
