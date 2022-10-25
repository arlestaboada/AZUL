
package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "PRACTICAS_DOCUMENTOS",schema = "BD_CVERDE")
public class PracticasDocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="BD_CVERDE.PRACTICAS_DOCUMENTOS_SEQ",sequenceName="BD_CVERDE.PRACTICAS_DOCUMENTOS_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.PRACTICAS_DOCUMENTOS_SEQ")
    @Basic(optional = false)
    @NotNull    
    @Column(name = "IDDOCUMENTO")
    private Integer iddocumento;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 300)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "TIPO")
    private Integer tipo;
    @JoinColumn(name = "IDPRACTICA", referencedColumnName = "IDPRACTICA")
    @ManyToOne(optional = false)
    private Practicas idpractica;

//    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "IDDOCUMENTO")
//    @ManyToOne(optional = false)
//    private PracticasDocumentosFiles document;

    
    @Transient
    private byte[] archivo;
    
    public PracticasDocumentos() {
    }

    public PracticasDocumentos(Integer iddocumento) {
        this.iddocumento = iddocumento;
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
 

    public Practicas getIdpractica() {
        return idpractica;
    }

    public void setIdpractica(Practicas idpractica) {
        this.idpractica = idpractica;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    
    
    
//    public PracticasDocumentosFiles getDocument() {
//        return document;
//    }
//
//    public void setDocument(PracticasDocumentosFiles document) {
//        this.document = document;
//    }
//    
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddocumento != null ? iddocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PracticasDocumentos)) {
            return false;
        }
        PracticasDocumentos other = (PracticasDocumentos) object;
        if ((this.iddocumento == null && other.iddocumento != null) || (this.iddocumento != null && !this.iddocumento.equals(other.iddocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.PracticasDocumentos[ iddocumento=" + iddocumento + " ]";
    }
    
}
