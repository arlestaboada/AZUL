/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "CORREOS_DOCUMENTOS",schema = "BD_CVERDE")
public class CorreosDocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CorreosDocumentosPK correosDocumentosPK;
    @Lob
    @Column(name = "ARCHIVO")
    private Serializable archivo;
    @Size(max = 500)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "EXTENSION")
    private String extension;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @JoinColumn(name = "IDCORREO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Correos correos;

    public CorreosDocumentos() {
    }

    public CorreosDocumentos(CorreosDocumentosPK correosDocumentosPK) {
        this.correosDocumentosPK = correosDocumentosPK;
    }

    public CorreosDocumentos(BigDecimal idcorreo, int iddocumento) {
        this.correosDocumentosPK = new CorreosDocumentosPK(idcorreo, iddocumento);
    }

    public CorreosDocumentosPK getCorreosDocumentosPK() {
        return correosDocumentosPK;
    }

    public void setCorreosDocumentosPK(CorreosDocumentosPK correosDocumentosPK) {
        this.correosDocumentosPK = correosDocumentosPK;
    }

    public Serializable getArchivo() {
        return archivo;
    }

    public void setArchivo(Serializable archivo) {
        this.archivo = archivo;
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

    public Correos getCorreos() {
        return correos;
    }

    public void setCorreos(Correos correos) {
        this.correos = correos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correosDocumentosPK != null ? correosDocumentosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreosDocumentos)) {
            return false;
        }
        CorreosDocumentos other = (CorreosDocumentos) object;
        if ((this.correosDocumentosPK == null && other.correosDocumentosPK != null) || (this.correosDocumentosPK != null && !this.correosDocumentosPK.equals(other.correosDocumentosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.CorreosDocumentos[ correosDocumentosPK=" + correosDocumentosPK + " ]";
    }
    
}
