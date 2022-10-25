/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Jorge
 */

public class DtoPracticas implements Serializable {

    private BigDecimal idpractica;
 
    private BigInteger temas;
  
    private String temasOtros;
  
    private String titulo;
  
    private BigInteger departamento;
  
    private BigInteger provincia;
  
    private BigInteger distrito;
  
    private Character estado;
   
    private Date fechaIni;
  
    private Date fechaFin;
  
    private BigInteger nropersonas;
   
    private String expDescripcion;
  
    private String expResultados;
  
    private String praProblema;
   
    private Character praComparte;
    
    private String praComparteOtro;
   
    private String praComparteDes;
   
    private Character praApoyo;
 
    private String praApoyoOtro;
   
    private String praApoyoDes;
   
    private String praMejora;
  
    private String praSustento;
   
    private BigInteger evdFoto;
   
    private String evdVideo;
  
    private String insReconoce;
  
    private String insInstitucion;
  
    private DtoPostulante idpostulante;

    public DtoPracticas() {
    }

    public DtoPracticas(BigDecimal idpractica) {
        this.idpractica = idpractica;
    }

    public BigDecimal getIdpractica() {
        return idpractica;
    }

    public void setIdpractica(BigDecimal idpractica) {
        this.idpractica = idpractica;
    }

    public BigInteger getTemas() {
        return temas;
    }

    public void setTemas(BigInteger temas) {
        this.temas = temas;
    }

    public String getTemasOtros() {
        return temasOtros;
    }

    public void setTemasOtros(String temasOtros) {
        this.temasOtros = temasOtros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigInteger getDepartamento() {
        return departamento;
    }

    public void setDepartamento(BigInteger departamento) {
        this.departamento = departamento;
    }

    public BigInteger getProvincia() {
        return provincia;
    }

    public void setProvincia(BigInteger provincia) {
        this.provincia = provincia;
    }

    public BigInteger getDistrito() {
        return distrito;
    }

    public void setDistrito(BigInteger distrito) {
        this.distrito = distrito;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigInteger getNropersonas() {
        return nropersonas;
    }

    public void setNropersonas(BigInteger nropersonas) {
        this.nropersonas = nropersonas;
    }

    public String getExpDescripcion() {
        return expDescripcion;
    }

    public void setExpDescripcion(String expDescripcion) {
        this.expDescripcion = expDescripcion;
    }

    public String getExpResultados() {
        return expResultados;
    }

    public void setExpResultados(String expResultados) {
        this.expResultados = expResultados;
    }

    public String getPraProblema() {
        return praProblema;
    }

    public void setPraProblema(String praProblema) {
        this.praProblema = praProblema;
    }

    public Character getPraComparte() {
        return praComparte;
    }

    public void setPraComparte(Character praComparte) {
        this.praComparte = praComparte;
    }

    public String getPraComparteOtro() {
        return praComparteOtro;
    }

    public void setPraComparteOtro(String praComparteOtro) {
        this.praComparteOtro = praComparteOtro;
    }

    public String getPraComparteDes() {
        return praComparteDes;
    }

    public void setPraComparteDes(String praComparteDes) {
        this.praComparteDes = praComparteDes;
    }

    public Character getPraApoyo() {
        return praApoyo;
    }

    public void setPraApoyo(Character praApoyo) {
        this.praApoyo = praApoyo;
    }

    public String getPraApoyoOtro() {
        return praApoyoOtro;
    }

    public void setPraApoyoOtro(String praApoyoOtro) {
        this.praApoyoOtro = praApoyoOtro;
    }

    public String getPraApoyoDes() {
        return praApoyoDes;
    }

    public void setPraApoyoDes(String praApoyoDes) {
        this.praApoyoDes = praApoyoDes;
    }

    public String getPraMejora() {
        return praMejora;
    }

    public void setPraMejora(String praMejora) {
        this.praMejora = praMejora;
    }

    public String getPraSustento() {
        return praSustento;
    }

    public void setPraSustento(String praSustento) {
        this.praSustento = praSustento;
    }

    public BigInteger getEvdFoto() {
        return evdFoto;
    }

    public void setEvdFoto(BigInteger evdFoto) {
        this.evdFoto = evdFoto;
    }

    public String getEvdVideo() {
        return evdVideo;
    }

    public void setEvdVideo(String evdVideo) {
        this.evdVideo = evdVideo;
    }

    public String getInsReconoce() {
        return insReconoce;
    }

    public void setInsReconoce(String insReconoce) {
        this.insReconoce = insReconoce;
    }

    public String getInsInstitucion() {
        return insInstitucion;
    }

    public void setInsInstitucion(String insInstitucion) {
        this.insInstitucion = insInstitucion;
    }

    public DtoPostulante getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(DtoPostulante idpostulante) {
        this.idpostulante = idpostulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpractica != null ? idpractica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtoPracticas)) {
            return false;
        }
        DtoPracticas other = (DtoPracticas) object;
        if ((this.idpractica == null && other.idpractica != null) || (this.idpractica != null && !this.idpractica.equals(other.idpractica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Practicas[ idpractica=" + idpractica + " ]";
    }
    
}
