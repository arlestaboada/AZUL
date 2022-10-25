/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.controlador.backingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_PRACTICAS;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.sistema.dto.DtoUbigeo;
import pe.gob.minam.sistema.entidades.Practicas;

/**
 *
 * @author jmarinc
 */
@Named(value = "BKPracticas")
@Scope("session")
public class BKPracticas implements Serializable {

    private static final long serialVersionUID = -1L;
    private Practicas practicas;
    private List<Practicas> listaPracticas;
    private List<DtoUbigeo> listaDepartamentos;
    private List<DtoUbigeo> listaProvincias;
    private List<DtoUbigeo> listaDistritos;
    private String codigoDept;
    private String codigoProv;
    private String codigoDist;
    private int operacion;
    private boolean eliminar;
    private List<Practicas> listaPracticasAEvaluar;

    public BKPracticas() {
        practicas = new Practicas();
        listaPracticas = new ArrayList<>();
        listaDepartamentos = new ArrayList<>();
        listaDistritos = new ArrayList<>();
        listaProvincias = new ArrayList<>();

        eliminar = false;

    }

    public Practicas getPracticas() {
        return practicas;
    }

    public void setPracticas(Practicas practicas) {
        this.practicas = practicas;
    }

    public List<Practicas> getListaPracticas() {
        return listaPracticas;
    }

    public void setListaPracticas(List<Practicas> listaPracticas) {
        this.listaPracticas = listaPracticas;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public int getNuevoOperacion() {
        return Constantes.Operacion.NUEVO;
    }

    public int getEditarOperacion() {
        return Constantes.Operacion.EDITAR;
    }

    public int getEliminarOperacion() {
        return Constantes.Operacion.ELIMINAR;
    }

    public int getEstadoEnviado() {
        return ESTADO_PRACTICAS.ENVIADO.getValor();
    }

    public int getEstadoRegistrado() {
        return ESTADO_PRACTICAS.REGISTRADO.getValor();
    }

    public List<DtoUbigeo> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<DtoUbigeo> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<DtoUbigeo> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<DtoUbigeo> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<DtoUbigeo> getListaDistritos() {
        return listaDistritos;
    }

    public void setListaDistritos(List<DtoUbigeo> listaDistritos) {
        this.listaDistritos = listaDistritos;
    }

    public String getCodigoDept() {
        return codigoDept;
    }

    public void setCodigoDept(String codigoDept) {
        this.codigoDept = codigoDept;
    }

    public String getCodigoProv() {
        return codigoProv;
    }

    public void setCodigoProv(String codigoProv) {
        this.codigoProv = codigoProv;
    }

    public String getCodigoDist() {
        return codigoDist;
    }

    public void setCodigoDist(String codigoDist) {
        this.codigoDist = codigoDist;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public List<Practicas> getListaPracticasAEvaluar() {
        return listaPracticasAEvaluar;
    }

    public void setListaPracticasAEvaluar(List<Practicas> listaPracticasAEvaluar) {
        this.listaPracticasAEvaluar = listaPracticasAEvaluar;
    }
    
}
