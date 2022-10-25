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
public class DtoCorreoDocumento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private long idcorreo;    
    private int iddocumento;    
    private String nombre;    
    private String extension;    
    private Date fechaReg;

    public long getIdcorreo() {
        return idcorreo;
    }

    public void setIdcorreo(long idcorreo) {
        this.idcorreo = idcorreo;
    }

    public int getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(int iddocumento) {
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

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }
    
    
}
