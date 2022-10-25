/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.MBGenerico;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.seguridad.controlador.backingBean.BKMenuRol;
import pe.gob.minam.seguridad.controlador.backingBean.BKRol;
import pe.gob.minam.seguridad.servicio.IServiceMenuRol;
import pe.gob.minam.seguridad.servicio.IServiceRol;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.sistema.entidades.MenuRol;
import pe.gob.minam.sistema.entidades.MenuRolPK;
import pe.gob.minam.sistema.entidades.Rol;


/**
 *
 * @author Jorge
 */
@Named(value = "MBOpciones")
@Scope("request")
public class MBOpciones extends MBGenerico implements Serializable{
 
	private static final long serialVersionUID = 9015531053058454499L;

	private static Logger logger = Logger.getLogger(MBOpciones.class);
    
        @Inject
        private BKMenuRol bkMenuRol;
        @Inject
        private BKRol bkRol;     
        @Inject
        private IServiceRol serviceRol;    
        @Inject
        private IServiceMenuRol serviceMenuRol;
    
        
        
     public String mostrar() {
        
        try {
            bkRol.setRolFormulario(new Rol());
            bkRol.setListaRoles(serviceRol.listarRoles());

        } catch (ServiceException ex) {
             mostrarError("Error al obtener la información");
        }
        return URLPaginacion.Opciones.URL_LISTA_OPCIONES;
    }
       
    public String editar(){
        
         try {
           
            List<Menu> lista = serviceMenuRol.obtenerListaMenuPorRolPadre();
            
            List<Menu> listaOpciones = new ArrayList<>();
            System.out.println("AQUI:"+lista.size());
            
            for(int i = 0;i < lista.size();i++){
                if(lista.get(i).getIdMenuPadre()==null){
                    lista.get(i).setNivel(0);
                    listaOpciones.add(lista.get(i));      
                    System.out.println("AQUI:"+lista.get(i).getNombre());
                    adicionarHijos(lista.get(i), listaOpciones,0);
                }
            }
            
            bkMenuRol.setListaMenu(listaOpciones);
            
            
            bkMenuRol.setListaMenuSeleccionados(serviceMenuRol.obtenerListaPermisos(bkRol.getRolSeleccionado().getId()));

             return URLPaginacion.Opciones.URL_EDITAR_OPCIONES;
        } catch (ServiceException ex) {
             mostrarError("Error al obtener la información");
             return null;
        }
       
    } 
    
    public String guardar(){
       
        System.out.println("tamaño:"+bkMenuRol.getListaMenuSeleccionados().size());
          try {
            for (int i = 0; i <bkMenuRol.getListaMenu().size(); i++) {
                if(bkMenuRol.getListaMenuSeleccionados().contains(bkMenuRol.getListaMenu().get(i))){

                        MenuRol opcion = new MenuRol();
                        opcion = serviceMenuRol.getMenuRol(new MenuRolPK(bkRol.getRolSeleccionado().getId(), bkMenuRol.getListaMenu().get(i).getId()));
                        if(opcion!=null){
                            opcion.setEstadoRegistro(Constantes.HABILITADO.ACTIVO);
                            serviceMenuRol.actualizarMenuRol(opcion);
                        }else{
                            opcion = new MenuRol();
                            opcion.setMenuRolPK(new MenuRolPK(bkRol.getRolSeleccionado().getId(), bkMenuRol.getListaMenu().get(i).getId()));
                            opcion.setRol(bkRol.getRolSeleccionado());
                            opcion.setMenu(bkMenuRol.getListaMenu().get(i));
                            opcion.setEstadoRegistro(Constantes.HABILITADO.ACTIVO);
                            serviceMenuRol.guardarMenuRol(opcion);
                        }
                }else{
                        MenuRol opcion = new MenuRol();
                        opcion = serviceMenuRol.getMenuRol(new MenuRolPK(bkRol.getRolSeleccionado().getId(), bkMenuRol.getListaMenu().get(i).getId()));
                        if(opcion!=null){
                            opcion.setEstadoRegistro(Constantes.HABILITADO.NOACTIVO);
                            serviceMenuRol.actualizarMenuRol(opcion);
                        }else{
                            opcion = new MenuRol();
                            opcion.setMenuRolPK(new MenuRolPK(bkRol.getRolSeleccionado().getId(), bkMenuRol.getListaMenu().get(i).getId()));
                            opcion.setRol(bkRol.getRolSeleccionado());
                            opcion.setMenu(bkMenuRol.getListaMenu().get(i));
                            opcion.setEstadoRegistro(Constantes.HABILITADO.NOACTIVO);
                            serviceMenuRol.guardarMenuRol(opcion);
                        }
                }
            }
        } catch (ServiceException ex) {
                   mostrarError("Error al actualizar la información");
         }finally{
              mostrarMensaje("Actualizar Permisos","Se actualizaron los remisos para el rol" +bkRol.getRolSeleccionado().getNombre());
          }
        return mostrar();
    }
    
    
    private void adicionarHijos(Menu padre, List<Menu> hijos,int nivel) throws ServiceException{
        nivel++;
        List<Menu> fuente = serviceMenuRol.obtenerListaMenuPorRolHijo(padre.getId());
        
        if(fuente.size()>0){
            for(int i =0;i<fuente.size();i++){
                
                System.out.println("AQUI:"+fuente.get(i).getNombre());
                    String nombre=""+"->";                    
                     for (int c = 0; c < nivel; c++) {
                        nombre=nombre.concat("...");
                    }
                    nombre=nombre.concat(fuente.get(i).getNombre());
                     fuente.get(i).setNombre(nombre);
                     fuente.get(i).setNivel(nivel);
                             
                    hijos.add(fuente.get(i));
                    adicionarHijos(fuente.get(i),hijos,nivel);
            }
        }
        
    }
}
