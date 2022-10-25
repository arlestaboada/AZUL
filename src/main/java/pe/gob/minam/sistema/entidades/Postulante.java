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
@Table(name = "POSTULANTE",schema = "BD_CVERDE")
public class Postulante implements Serializable {
  
    private static final long serialVersionUID = 1L;
     @SequenceGenerator(name="BD_CVERDE.POSTULANTE_SEQ",sequenceName="BD_CVERDE.POSTULANTE_SEQ")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BD_CVERDE.POSTULANTE_SEQ")
    @Basic(optional = false)
    @NotNull    
    @Column(name = "IDPOSTULANTE")
    private Integer idpostulante;
    @Column(name = "NROINSCRIPCION")
    private Integer nroinscripcion;
    @Column(name = "CATEGORIA")
    private Integer categoria;
    @Size(max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "NRODNI")
    private Integer nrodni;
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanac;
    @Column(name = "GENERO")
    private Integer genero;
    @Column(name = "EDAD")
    private Integer edad;
    @Size(max = 200)
    @Column(name = "PADRETUTOR")
    private String padretutor;
    @Size(max = 200)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "DISTRITO")
    private String distrito;
    @Size(max = 50)
    @Column(name = "TELEFONOS")
    private String telefonos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TIPOOCUPACION")
    private Integer tipoocupacion;
    @Size(max = 200)
    @Column(name = "INST_EDUCATIVA")
    private String instEducativa;
    @Size(max = 200)
    @Column(name = "CENTRO_LABORAL")
    private String centroLaboral;
    @Column(name = "FECHAREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
   
    @Transient
     private int buenasPractica   ;  
    
    public Postulante() {
    }

    public Postulante(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

    public Integer getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Integer idpostulante) {
        this.idpostulante = idpostulante;
    }

    public Integer getNroinscripcion() {
        return nroinscripcion;
    }

    public void setNroinscripcion(Integer nroinscripcion) {
        this.nroinscripcion = nroinscripcion;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    public String getSgenero(){
        String migenero;
        if(genero==1){
            migenero="Hombre";
        }
        else{
          migenero="Mujer"  ;
        }
        return migenero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getNrodni() {
        return nrodni;
    }

    public void setNrodni(Integer nrodni) {
        this.nrodni = nrodni;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPadretutor() {
        return padretutor;
    }

    public void setPadretutor(String padretutor) {
        this.padretutor = padretutor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipoocupacion() {
        return tipoocupacion;
    }

    public void setTipoocupacion(Integer tipoocupacion) {
        this.tipoocupacion = tipoocupacion;
    }

    public String getInstEducativa() {
        return instEducativa;
    }

    public void setInstEducativa(String instEducativa) {
        this.instEducativa = instEducativa;
    }

    public String getCentroLaboral() {
        return centroLaboral;
    }

    public void setCentroLaboral(String centroLaboral) {
        this.centroLaboral = centroLaboral;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public int getBuenasPractica() {
        return buenasPractica;
    }

    public void setBuenasPractica(int buenasPractica) {
        this.buenasPractica = buenasPractica;
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
        if (!(object instanceof Postulante)) {
            return false;
        }
        Postulante other = (Postulante) object;
        if ((this.idpostulante == null && other.idpostulante != null) || (this.idpostulante != null && !this.idpostulante.equals(other.idpostulante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.minam.sistema.entidades.Postulante[ idpostulante=" + idpostulante + " ]";
    }

   
    
}
