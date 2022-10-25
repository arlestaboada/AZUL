/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "POSTULANTE_DOCUMENTOS",schema = "BD_CVERDE")

public class PostulanteDocumentos implements Serializable {
     private static final long serialVersionUID = 1L;
     @SequenceGenerator(name="BD_CVERDE.POSTULANTE_DOCUMENTOS_SEQ",sequenceName="BD_CVERDE.POSTULANTE_DOCUMENTOS_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.POSTULANTE_DOCUMENTOS_SEQ")
    @Basic(optional = false)
    @NotNull    
    @Column(name = "IDDOCUMENTO")
    private Integer iddocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 300)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private Integer tipo;
    @JoinColumn(name = "IDPOSTULANTE", referencedColumnName = "IDPOSTULANTE")
    @ManyToOne(optional = false)
    private Postulante idpostulante;

    public PostulanteDocumentos() {
    }

    public PostulanteDocumentos(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public PostulanteDocumentos(Integer iddocumento, String nombre, String extension, Date fechaReg, Integer tipo) {
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Postulante getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Postulante idpostulante) {
        this.idpostulante = idpostulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddocumento != null ? iddocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostulanteDocumentos)) {
            return false;
        }
        PostulanteDocumentos other = (PostulanteDocumentos) object;
        if ((this.iddocumento == null && other.iddocumento != null) || (this.iddocumento != null && !this.iddocumento.equals(other.iddocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.PostulanteDocumentos[ iddocumento=" + iddocumento + " ]";
    }
    
}
