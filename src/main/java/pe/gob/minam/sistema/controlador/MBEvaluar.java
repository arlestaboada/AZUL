/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.controlador;

/**
 *
 * @author Jorge
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.sistema.controlador.backingBean.BKEvaluar;
import pe.gob.minam.sistema.controlador.backingBean.BKIndicadores;
import pe.gob.minam.sistema.controlador.backingBean.BKPracticas;
import pe.gob.minam.sistema.controlador.backingBean.BKPracticasDocumentos;
import pe.gob.minam.sistema.entidades.Evaluar;
import pe.gob.minam.sistema.entidades.Indicadores;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.sistema.entidades.PracticasDocumentos;
import pe.gob.minam.sistema.entidades.PracticasDocumentosFiles;
import pe.gob.minam.sistema.servicio.IServiceEvaluar;
import pe.gob.minam.sistema.servicio.IServiceIndicadores;
import pe.gob.minam.sistema.servicio.IServicePracticas;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentos;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentosFiles;

@Named(value = "MBEvaluar")
@Scope("session")
public class MBEvaluar extends MBGenerico implements IMantenedor, Serializable {
    
     private static Logger logger = Logger.getLogger(MBEvaluar.class);
    
    
    @Inject
    private IServicePracticas servicePracticas;
    @Inject
    private IServicePracticasDocumentos servicePracticasDocumentos;
    @Inject
    private IServicePracticasDocumentosFiles servicePracticasDocumentosFiles;
   
    @Inject
    private BKPracticas bkPracticas;
    @Inject
    private BKPracticasDocumentos bkPracticasDocumentos;    

    @Inject
    private BKIndicadores bkIndicadores;
    @Inject
    private IServiceIndicadores serviceIndicadores;

    
    
    @Inject
    private BKEvaluar bkEvaluar;
    @Inject
    private IServiceEvaluar serviceEvaluar;
    
    private StreamedContent file;	
    private UploadedFile	fileLoad;

   

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public UploadedFile getFileLoad() {
        return fileLoad;
    }

    public void setFileLoad(UploadedFile fileLoad) {
        this.fileLoad = fileLoad;
    }
    
    
    
    
    @Override
    public String actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String guardar() {
        int suma=0;
        Practicas practica=new Practicas();
        practica=bkPracticas.getPracticas();
   
      
        if(bkEvaluar.getListaEvaluar().size()>0){
            for(Evaluar evaluar: bkEvaluar.getListaEvaluar()){
                suma=suma+evaluar.getValor();
                evaluar.setIdPracticas(bkPracticas.getPracticas().getIdpractica());
                serviceEvaluar.guardarEvaluar(evaluar);
            }
        }
       
         if(suma>=8){
            serviceEvaluar.actualizarEvaluarEstado(4,practica.getIdpractica());
           
        }
        else{
             
             serviceEvaluar.actualizarEvaluarEstado(3,practica.getIdpractica());
        }
      

        return URLPaginacion.Evaluador.URL_GUARDO_PRACTICA_Evaluada;
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        
    }

    @Override
    public String editar() {
         try {
            file = new DefaultStreamedContent();
            fileLoad = new DefaultUploadedFile();
            
            bkPracticas.setOperacion(bkPracticas.getEditarOperacion());
            bkPracticasDocumentos.setListaAdjuntos(new ArrayList<PracticasDocumentos>());
            
            bkPracticasDocumentos.setListaAdjuntos(servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(),Constantes.TipoImagen.DOCUMENTO));
            
            
            PracticasDocumentos practicasDocumentos =  null;
            List<PracticasDocumentos> lista = servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(),Constantes.TipoImagen.FOTO_PRACTICA);
            if(lista!=null && !lista.isEmpty()){
                practicasDocumentos = lista.get(0);
            }
            if(practicasDocumentos!=null){
                bkPracticasDocumentos.setPracticasDocumentoFormulario(practicasDocumentos);
                PracticasDocumentosFiles  archivo = servicePracticasDocumentosFiles.getPracticasDocumentosFiles(practicasDocumentos.getIddocumento());                
                if(archivo!=null){
                        bkPracticasDocumentos.setPracticasDocumentosFiles(archivo);
                        InputStream inputProfile = new ByteArrayInputStream(archivo.getArchivo());  	
                        file = new DefaultStreamedContent(inputProfile, "image/jpeg"); 
                }
            }else{
                 bkPracticasDocumentos.setPracticasDocumentoFormulario(null);
            }
           
            bkIndicadores.setListaIndicadores(serviceIndicadores.listarIndicadores()); 
            
            bkEvaluar.getListaEvaluar().clear();
                    
             for (Indicadores indicador : bkIndicadores.getListaIndicadores()) {
                    
                 Evaluar evaluar = new Evaluar();
                 //aqui esta id de indicador 
                 evaluar.setIdIndicador(indicador.getIdindicadores());
                 evaluar.setIndicador(indicador.getIndicador());
                 evaluar.setCriterio(indicador.getIdcriterio().getNombre());
                 evaluar.setNumero(indicador.getNumero());
                 evaluar.setValor(servicePracticas.getValor(bkPracticas.getPracticas().getIdpractica(),indicador.getIdindicadores()));
                 bkEvaluar.getListaEvaluar().add(evaluar);
                 
             }
             
            
        } catch (ServiceException ex) {
            mostrarError("Error al listar documentos");
            logger.error(ex.getMessage(), ex);
        }
         
         
         
        return URLPaginacion.Evaluador.URL_BUENA_PRACTICA_EVALUAR;
    }

    @Override
    public String nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aceptar() {
         
         return URLPaginacion.Seguridad.URL_SAME;
    }

    @Override
    public String retroceder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String mostrar() {
         try {
            bkPracticas.setListaPracticas(servicePracticas.listarPracticas());
        } catch (ServiceException ex) {
             mostrarError("Error al listar las buenas prácticas");
            logger.error(ex.getMessage(), ex);
        }
        return URLPaginacion.Evaluador.URL_BUENA_PRACTICA_LISTA;
    }

    
    public void descargarArchivos(){
        
    }
    
    
    
}
