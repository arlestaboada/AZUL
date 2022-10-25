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
import pe.gob.minam.sistema.entidades.Evaluar;


/**
 *
 * @author Jorge
 */
@Named(value = "BKEvaluar")
@Scope("session")
public class BKEvaluar implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    private Evaluar evaluarFormulario;
    private Evaluar evaluarSeleccionado;
    private  List<Evaluar> listaEvaluar;

    private int accion;
    
    public BKEvaluar() {
        evaluarFormulario=new Evaluar();
        listaEvaluar = new ArrayList<>();
    }

    public Evaluar getEvaluarFormulario() {
        return evaluarFormulario;
    }

    public void setEvaluarFormulario(Evaluar evaluarFormulario) {
        this.evaluarFormulario = evaluarFormulario;
    }

    public Evaluar getEvaluarSeleccionado() {
        return evaluarSeleccionado;
    }

    public void setEvaluarSeleccionado(Evaluar evaluarSeleccionado) {
        this.evaluarSeleccionado = evaluarSeleccionado;
    }

    public List<Evaluar> getListaEvaluar() {
        return listaEvaluar;
    }

    public void setListaEvaluar(List<Evaluar> listaEvaluar) {
        this.listaEvaluar = listaEvaluar;
    }

  
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    
    
    
    
}
