/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.controlador.backingBean;

/**
 *
 * @author Jorge
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.Criterios;
import pe.gob.minam.sistema.entidades.Indicadores;


/**
 *
 * @author Jorge
 */
@Named(value = "BKIndicadores")
@Scope("session")
public class BKIndicadores implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    private Indicadores indicadoresFormulario;
    private Indicadores indicadoresSeleccionado;
    private  List<Indicadores> listaIndicadores;
    private  List<Criterios> listaCriterios;
    private int idCriterio;

    private int accion;
    
    public BKIndicadores() {
        indicadoresFormulario=new Indicadores();
        listaIndicadores = new ArrayList<>();
    }

    public Indicadores getIndicadoresFormulario() {
        return indicadoresFormulario;
    }

    public void setIndicadoresFormulario(Indicadores indicadoresFormulario) {
        this.indicadoresFormulario = indicadoresFormulario;
    }

    public Indicadores getIndicadoresSeleccionado() {
        return indicadoresSeleccionado;
    }

    public void setIndicadoresSeleccionado(Indicadores indicadoresSeleccionado) {
        this.indicadoresSeleccionado = indicadoresSeleccionado;
    }

    public List<Indicadores> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicadores> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<Criterios> getListaCriterios() {
        return listaCriterios;
    }

    public void setListaCriterios(List<Criterios> listaCriterios) {
        this.listaCriterios = listaCriterios;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

   
    
    
    
}
