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


/**
 *
 * @author Jorge
 */
@Named(value = "BKCriterios")
@Scope("session")
public class BKCriterios implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    private Criterios criteriosFormulario;
    private Criterios criteriosSeleccionado;
    private  List<Criterios> listaCriterios;

    private int accion;
    
    public BKCriterios() {
        criteriosFormulario=new Criterios();
        listaCriterios = new ArrayList<>();
    }

    public Criterios getCriteriosFormulario() {
        return criteriosFormulario;
    }

    public void setCriteriosFormulario(Criterios criteriosFormulario) {
        this.criteriosFormulario = criteriosFormulario;
    }

    public Criterios getCriteriosSeleccionado() {
        return criteriosSeleccionado;
    }

    public void setCriteriosSeleccionado(Criterios criteriosSeleccionado) {
        this.criteriosSeleccionado = criteriosSeleccionado;
    }

    public List<Criterios> getListaCriterios() {
        return listaCriterios;
    }

    public void setListaCriterios(List<Criterios> listaCriterios) {
        this.listaCriterios = listaCriterios;
    }

  
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    
    
    
    
}
