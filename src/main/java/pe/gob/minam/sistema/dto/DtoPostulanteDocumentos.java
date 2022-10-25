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

public class DtoPostulanteDocumentos implements Serializable {
   
    private Integer iddocumento;
   
    private String nombre;
   
    private String extension;
  
    private String comentario;
   
    private Date fechaReg;
 
    private int tipo;
   
    private Integer idpostulante;

    public DtoPostulanteDocumentos() {
    }

    public DtoPostulanteDocumentos(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public DtoPostulanteDocumentos(Integer iddocumento, String nombre, String extension, Date fechaReg, int tipo) {
        this.iddocumento = iddocumento;
        this.nombre = nombre;
        this.extension = extension;
        this.fechaReg = fechaReg;
        this.tipo = tipo;
    }

    public Integer getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

  
    
}
