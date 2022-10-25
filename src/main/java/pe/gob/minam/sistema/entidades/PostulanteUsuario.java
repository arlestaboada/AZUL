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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "POSTULANTE_USUARIO",schema = "BD_CVERDE")
public class PostulanteUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPOSTULANTE")
    private Integer idpostulante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private BigInteger estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "IDPOSTULANTE", referencedColumnName = "IDPOSTULANTE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Postulante postulante;

    public PostulanteUsuario() {
    }

    public PostulanteUsuario(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

    public PostulanteUsuario(Integer idpostulante, String usuario, String password, BigInteger estado, Date fechaIns) {
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

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
        this.estado = estado;
    }

    public Date getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpostulante != null ? idpostulante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostulanteUsuario)) {
            return false;
        }
        PostulanteUsuario other = (PostulanteUsuario) object;
        if ((this.idpostulante == null && other.idpostulante != null) || (this.idpostulante != null && !this.idpostulante.equals(other.idpostulante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.PostulanteUsuario[ idpostulante=" + idpostulante + " ]";
    }
    
}
