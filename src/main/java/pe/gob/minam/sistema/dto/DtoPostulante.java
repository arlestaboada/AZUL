/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jorge
 */

public class DtoPostulante implements Serializable {
   
    private Integer idpostulante;
 
    private Integer nroinscripcion;
  
    private Integer categoria;
  
    private String nombres;
 
    private String apellidos;
   
    private Integer nrodni;
 
    private Date fechanac;
  
    private Integer genero;
  
    private Integer edad;
  
    private String padretutor;
 
    private String direccion;
  
    private Integer departamento;
 
    private Integer provincia;
  
    private Integer distrito;
   
    private String telefonos;
   
    private String email;
   
    private Integer tipoocupacion;
 
    private String instEducativa;
   
    private String centroLaboral;
   

    public DtoPostulante() {
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

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public Integer getDistrito() {
        return distrito;
    }

    public void setDistrito(Integer distrito) {
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

   
}
