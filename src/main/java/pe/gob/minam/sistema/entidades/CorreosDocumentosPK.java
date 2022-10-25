/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge
 */
@Embeddable
public class CorreosDocumentosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCORREO")
    private BigDecimal idcorreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOCUMENTO")
    private int iddocumento;

    public CorreosDocumentosPK() {
    }

    public CorreosDocumentosPK(BigDecimal idcorreo, int iddocumento) {
        this.idcorreo = idcorreo;
        this.iddocumento = iddocumento;
    }

    public BigDecimal getIdcorreo() {
        return idcorreo;
    }

    public void setIdcorreo(BigDecimal idcorreo) {
        this.idcorreo = idcorreo;
    }

    public int getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(int iddocumento) {
        this.iddocumento = iddocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorreo != null ? idcorreo.hashCode() : 0);
        hash += (int) iddocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreosDocumentosPK)) {
            return false;
        }
        CorreosDocumentosPK other = (CorreosDocumentosPK) object;
        if ((this.idcorreo == null && other.idcorreo != null) || (this.idcorreo != null && !this.idcorreo.equals(other.idcorreo))) {
            return false;
        }
        if (this.iddocumento != other.iddocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.CorreosDocumentosPK[ idcorreo=" + idcorreo + ", iddocumento=" + iddocumento + " ]";
    }
    
}
