/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.controlador.backingBean;


import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.Correos;/**
 *
 * @author Jorge
 */


@Named(value = "BKCorreos")
@Scope("session")
public class BKCorreos {
    
     private static final long serialVersionUID = -874481855366455968L;
     private Correos correosFormulario;
     private Correos correosSeleccionado;
     private List<Correos> listaEdbCorreos;
   

             
    public BKCorreos() {
       
        listaEdbCorreos = new ArrayList<>();
        correosFormulario= new Correos();
        correosSeleccionado= new Correos();
        
    }

    public Correos getCorreosFormulario() {
        return correosFormulario;
    }

    public void setCorreosFormulario(Correos correosFormulario) {
        this.correosFormulario = correosFormulario;
    }

    public Correos getCorreosSeleccionado() {
        return correosSeleccionado;
    }

    public void setCorreosSeleccionado(Correos correosSeleccionado) {
        this.correosSeleccionado = correosSeleccionado;
    }

    public List<Correos> getListaEdbCorreos() {
        return listaEdbCorreos;
    }

    public void setListaEdbCorreos(List<Correos> listaEdbCorreos) {
        this.listaEdbCorreos = listaEdbCorreos;
    }

   

  

    


     
}
