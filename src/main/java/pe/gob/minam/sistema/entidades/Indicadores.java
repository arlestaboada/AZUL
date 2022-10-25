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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jmarinc
 */
@Entity
@Table(name = "INDICADORES",schema = "BD_CVERDE")
public class Indicadores implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="BD_CVERDE.INDICADORES_SEQ",sequenceName="BD_CVERDE.INDICADORES_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.INDICADORES_SEQ")
    @Basic(optional = false)
     @NotNull    
    @Column(name = "IDINDICADORES")
    private Integer idindicadores;
    @Size(max = 20)
   
    @Column(name = "NUMERO")
    private String numero;
    @Size(max = 1000)
    @Column(name = "INDICADOR")
    private String indicador;
    @JoinColumn(name = "IDCRITERIO", referencedColumnName = "IDCRITERIO")
    @ManyToOne
    private Criterios idcriterio;

    public Indicadores() {
    }

    public Indicadores(Integer idindicadores) {
        this.idindicadores = idindicadores;
    }

    public Integer getIdindicadores() {
        return idindicadores;
    }

    public void setIdindicadores(Integer idindicadores) {
        this.idindicadores = idindicadores;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

   

    public Criterios getIdcriterio() {
        return idcriterio;
    }

    public void setIdcriterio(Criterios idcriterio) {
        this.idcriterio = idcriterio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindicadores != null ? idindicadores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicadores)) {
            return false;
        }
        Indicadores other = (Indicadores) object;
        if ((this.idindicadores == null && other.idindicadores != null) || (this.idindicadores != null && !this.idindicadores.equals(other.idindicadores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Indicadores[ idindicadores=" + idindicadores + " ]";
    }
    
}
