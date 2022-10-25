/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "PRACTICAS",schema = "BD_CVERDE")
public class Practicas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpractica")
    private List<PracticasDocumentos> practicasDocumentosList;
    private static final long serialVersionUID = 1L;
     @SequenceGenerator(name="BD_CVERDE.PRACTICAS_SEQ",sequenceName="BD_CVERDE.PRACTICAS_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.PRACTICAS_SEQ")
    @Basic(optional = false)
    @NotNull    
    @Column(name = "IDPRACTICA")
    private Integer idpractica;
    @Column(name = "TEMAS")
    private Integer temas;
    @Size(max = 100)
    @Column(name = "TEMAS_OTROS")
    private String temasOtros;
    @Size(max = 100)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "DISTRITO")
    private String distrito;
    @Column(name = "ESTADO")
    private Integer estado;
    @Column(name = "FECHA_INI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIni;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "NROPERSONAS")
    private Integer nropersonas;
    @Size(max = 1000)
    @Column(name = "EXP_DESCRIPCION")
    private String expDescripcion;
    @Size(max = 1000)
    @Column(name = "EXP_RESULTADOS")
    private String expResultados;
    @Size(max = 1000)
    @Column(name = "PRA_PROBLEMA")
    private String praProblema;
    @Column(name = "PRA_COMPARTE")
    private Character praComparte;
    @Size(max = 100)
    @Column(name = "PRA_COMPARTE_OTRO")
    private String praComparteOtro;
    @Size(max = 1000)
    @Column(name = "PRA_COMPARTE_DES")
    private String praComparteDes;
    @Column(name = "PRA_APOYO")
    private Character praApoyo;
    @Size(max = 100)
    @Column(name = "PRA_APOYO_OTRO")
    private String praApoyoOtro;
    @Size(max = 1000)
    @Column(name = "PRA_APOYO_DES")
    private String praApoyoDes;
    @Size(max = 1000)
    @Column(name = "PRA_MEJORA")
    private String praMejora;
    @Size(max = 1000)
    @Column(name = "PRA_SUSTENTO")
    private String praSustento;
    @Column(name = "EVD_FOTO")
    private Integer evdFoto;
    @Size(max = 200)
    @Column(name = "EVD_VIDEO")
    private String evdVideo;
    @Column(name = "INS_RECONOCE")
    private Integer insReconoce;
    @Size(max = 1000)
    @Column(name = "INS_INSTITUCION")
    private String insInstitucion;

    @Size(max = 100)
    @Column(name = "PERSONA1")
    private String persona1;    
    @Column(name = "DNI1")
    private Integer dni1;
    @Size(max = 200)
    @Column(name = "INSTITUCION1")
    private String institucion1;
    @Size(max = 100)
    @Column(name = "PERSONA2")
    private String persona2;    
    @Column(name = "DNI2")
    private Integer dni2;
    @Size(max = 200)
    @Column(name = "INSTITUCION2")
    private String institucion2;
    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "ESTADO_REG")
    private Integer estadoReg;
    
    
    @JoinColumn(name = "IDPOSTULANTE", referencedColumnName = "IDPOSTULANTE")
    @ManyToOne(optional = false)
    private Postulante idpostulante;
    
     @Transient
     private String nombresPostulante;

     @Transient
     private String apellidosPostulante;

  
 
    public Practicas() {
    }

    public Practicas(Integer idpractica) {
        this.idpractica = idpractica;
    }

    public Integer getIdpractica() {
        return idpractica;
    }

    public void setIdpractica(Integer idpractica) {
        this.idpractica = idpractica;
    }

    public Integer getTemas() {
        return temas;
    }

    public void setTemas(Integer temas) {
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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

    public Integer getNropersonas() {
        return nropersonas;
    }

    public void setNropersonas(Integer nropersonas) {
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

    public Integer getEvdFoto() {
        return evdFoto;
    }

    public void setEvdFoto(Integer evdFoto) {
        this.evdFoto = evdFoto;
    }

    public String getEvdVideo() {
        return evdVideo;
    }

    public void setEvdVideo(String evdVideo) {
        this.evdVideo = evdVideo;
    }

    public Integer getInsReconoce() {
        return insReconoce;
    }

    public void setInsReconoce(Integer insReconoce) {
        this.insReconoce = insReconoce;
    }
  

    public String getInsInstitucion() {
        return insInstitucion;
    }

    public void setInsInstitucion(String insInstitucion) {
        this.insInstitucion = insInstitucion;
    }

    public String getPersona1() {
        return persona1;
    }

    public void setPersona1(String persona1) {
        this.persona1 = persona1;
    }

    public Integer getDni1() {
        return dni1;
    }

    public void setDni1(Integer dni1) {
        this.dni1 = dni1;
    }

    public String getInstitucion1() {
        return institucion1;
    }

    public void setInstitucion1(String institucion1) {
        this.institucion1 = institucion1;
    }

    public String getPersona2() {
        return persona2;
    }

    public void setPersona2(String persona2) {
        this.persona2 = persona2;
    }

    public Integer getDni2() {
        return dni2;
    }

    public void setDni2(Integer dni2) {
        this.dni2 = dni2;
    }

    public String getInstitucion2() {
        return institucion2;
    }

    public void setInstitucion2(String institucion2) {
        this.institucion2 = institucion2;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Integer getEstadoReg() {
        return estadoReg;
    }

    public void setEstadoReg(Integer estadoReg) {
        this.estadoReg = estadoReg;
    }
    
    

    public Postulante getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Postulante idpostulante) {
        this.idpostulante = idpostulante;
    }

    public String getNombresPostulante() {
        return nombresPostulante;
    }

    public void setNombresPostulante(String nombresPostulante) {
        this.nombresPostulante = nombresPostulante;
    }

    
    public String getApellidosPostulante() {
        return apellidosPostulante;
    }

    public void setApellidosPostulante(String apellidosPostulante) {
        this.apellidosPostulante = apellidosPostulante;
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
        if (!(object instanceof Practicas)) {
            return false;
        }
        Practicas other = (Practicas) object;
        if ((this.idpractica == null && other.idpractica != null) || (this.idpractica != null && !this.idpractica.equals(other.idpractica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Practicas[ idpractica=" + idpractica + " ]";
    }

    @XmlTransient
    public List<PracticasDocumentos> getPracticasDocumentosList() {
        return practicasDocumentosList;
    }

    public void setPracticasDocumentosList(List<PracticasDocumentos> practicasDocumentosList) {
        this.practicasDocumentosList = practicasDocumentosList;
    }
    
}
