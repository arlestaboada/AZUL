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

public class DtoPostulanteDocumentosFiles implements Serializable {
 
    private Integer iddocumento;
   
    private byte[] archivo;
  
    private Date fechaReg;

    public DtoPostulanteDocumentosFiles() {
    }

    public Integer getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

  
    
}
