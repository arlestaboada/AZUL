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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jmarinc
 */
@Entity
@Table(name = "EVALUAR",schema = "BD_CVERDE")
public class Evaluar implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="BD_CVERDE.EVALUAR_SEQ",sequenceName="BD_CVERDE.EVALUAR_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.EVALUAR_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EVALUAR")
    private int idEvaluar;
    @Column(name = "ID_PRACTICAS")
    private int idPracticas;
    @Column(name = "ID_INDICADOR")
    private int idIndicador;
    @Column(name = "VALOR")
    private int valor;
    @Transient
    private String criterio;
    @Transient
    private String indicador;
    @Transient
    private String numero;
    
    

    
    public Evaluar() {
    }

    public Evaluar(int idEvaluar) {
        this.idEvaluar = idEvaluar;
    }

    public int getIdEvaluar() {
        return idEvaluar;
    }

    public void setIdEvaluar(int idEvaluar) {
        this.idEvaluar = idEvaluar;
    }

    public int getIdPracticas() {
        return idPracticas;
    }

    public void setIdPracticas(int idPracticas) {
        this.idPracticas = idPracticas;
    }

    public int getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(int idIndicador) {
        this.idIndicador = idIndicador;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
   

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Evaluar[ idEvaluar=" + idEvaluar + " ]";
    }
    
}
