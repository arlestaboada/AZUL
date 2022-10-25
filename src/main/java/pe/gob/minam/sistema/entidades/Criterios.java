/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jmarinc
 */
@Entity
@Table(name = "CRITERIOS",schema = "BD_CVERDE")
public class Criterios implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="BD_CVERDE.CRITERIOS_SEQ",sequenceName="BD_CVERDE.CRITERIOS_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.CRITERIOS_SEQ")
    @Basic(optional = false)
    @Column(name = "IDCRITERIO")
    private Integer idcriterio;
    @Size(max = 20)
    @Column(name = "NUMERO")
    private String numero;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;


    public Criterios() {
    }

    public Criterios(Integer idcriterio) {
        this.idcriterio = idcriterio;
    }

    public Integer getIdcriterio() {
        return idcriterio;
    }

    public void setIdcriterio(Integer idcriterio) {
        this.idcriterio = idcriterio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcriterio != null ? idcriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterios)) {
            return false;
        }
        Criterios other = (Criterios) object;
        if ((this.idcriterio == null && other.idcriterio != null) || (this.idcriterio != null && !this.idcriterio.equals(other.idcriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Criterios[ idcriterio=" + idcriterio + " ]";
    }
    
}
