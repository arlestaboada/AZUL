
package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PRACTICAS_DOCUMENTOS_FILES",schema = "BD_CVERDE")
public class PracticasDocumentosFiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOCUMENTO")
    private Integer iddocumento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ARCHIVO")
    private byte[] archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "IDDOCUMENTO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PracticasDocumentos practicasDocumentos;

    public PracticasDocumentosFiles() {
    }

    public PracticasDocumentosFiles(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public PracticasDocumentosFiles(Integer iddocumento, byte[] archivo, Date fechaReg) {
        this.iddocumento = iddocumento;
        this.archivo = archivo;
        this.fechaReg = fechaReg;
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

    public PracticasDocumentos getPracticasDocumentos() {
        return practicasDocumentos;
    }

    public void setPracticasDocumentos(PracticasDocumentos practicasDocumentos) {
        this.practicasDocumentos = practicasDocumentos;
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
        if (!(object instanceof PracticasDocumentosFiles)) {
            return false;
        }
        PracticasDocumentosFiles other = (PracticasDocumentosFiles) object;
        if ((this.iddocumento == null && other.iddocumento != null) || (this.iddocumento != null && !this.iddocumento.equals(other.iddocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.PracticasDocumentosFiles[ iddocumento=" + iddocumento + " ]";
    }
    
}
