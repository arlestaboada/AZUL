package pe.gob.minam.seguridad.controlador;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.controlador.backingBean.BKUsuarioDocumentos;
import pe.gob.minam.sistema.servicio.IServiceInstituciones;
import pe.gob.minam.sistema.servicio.IServiceUsuarioDocumentos;
import pe.gob.minam.sistema.servicio.IServiceUsuarioFiles;
import pe.gob.minam.sistema.entidades.Rol;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.sistema.entidades.UsuarioDocumentos;
import pe.gob.minam.sistema.entidades.UsuarioFiles;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.controlador.backingBean.BKRol;
import pe.gob.minam.seguridad.controlador.backingBean.BKUsuario;
import pe.gob.minam.seguridad.servicio.IServiceRol;
import pe.gob.minam.seguridad.servicio.IServiceUsuario;
import pe.gob.minam.sistema.servicio.IServiceGeneral;


@Named(value="MBUsuario")
@Scope("session")
public class MBUsuario extends MBGenerico implements IMantenedor, Serializable {

	private static final long serialVersionUID = 7763651480661834960L;
	
	private static Logger logger = Logger.getLogger(MBUsuario.class);
	
    @Inject
    private BKUsuario bkUsuario;
    
    @Inject
    private BKRol bkRol;
    
    @Inject
    private BKUsuarioDocumentos bkUsuarioDocumentos;
    
    @Inject
    private IServiceUsuario serviceUsuario;
    
    @Inject
    private IServiceRol serviceRol;
 
    @Inject
    private IServiceInstituciones serviceInstituciones;
    
    @Inject
    private IServiceUsuarioDocumentos serviceUsuarioDocumentos;
    
    @Inject
    private IServiceUsuarioFiles serviceUsuarioFiles;
    
    @Inject
    private IServiceGeneral serviceGeneral;

    public MBUsuario() {
    }

    @Override
    public String mostrar() {
        
        try {
            bkUsuario.setUsuarioBusqueda(new Usuario());
            bkUsuario.setListaUsuarios(serviceUsuario.listarUsuarios());

        } catch (ServiceException ex) {
             mostrarError("Error al actualizar la información");
        }
        return URLPaginacion.Usuario.URL_LISTA_USUARIO;
    }
    

    public void buscarListaUsuario(ActionEvent event) {
        try {
            bkUsuario.setListaUsuarios(serviceUsuario.obtenerListaUsuarios(bkUsuario.getUsuarioBusqueda()));
        } catch (ServiceException ex) {
            mostrarError("Error al actualizar la información");
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public String nuevo() {
       
            try {
                bkUsuario.setUsuarioFormulario(new Usuario());
                bkRol.setListaRoles(new ArrayList<>(serviceRol.obtenerListaRolesActivos()));
                bkUsuario.setListaInstituciones(serviceInstituciones.listarInstituciones());  
                bkUsuario.setListaDepartamentos(serviceGeneral.listarDepartamento());
                 return URLPaginacion.Usuario.URL_CREAR_USUARIO;
            } catch (ServiceException ex) {
                 mostrarError("Error al actualizar la información");
                 logger.error(ex.getMessage(), ex);
                 return null;
            }
           
    }

    @Override
    public String actualizar() {
        try {
             bkUsuario.getUsuarioFormulario().setIdRol(serviceRol.getRol(bkUsuario.getRol()));
             bkUsuario.getUsuarioFormulario().setIdInstitucion(serviceInstituciones.getInstituciones(bkUsuario.getInstitucion()));
            this.serviceUsuario.actualizarUsuario(bkUsuario.getUsuarioFormulario());
            mostrarMensaje("Se actualizo la informacion satisfactoriamente");
            return mostrar();
        } catch (ServiceException ex) {
            mostrarError("Error al actualizar la información");
            logger.error(ex.getMessage(), ex);
            return URLPaginacion.Usuario.URL_EDITAR_USUARIO;
        }
    }

    @Override
    public String ver() {
        editar();
        return URLPaginacion.Usuario.URL_EDITAR_USUARIO;
    }

    @Override
    public void eliminar(ActionEvent event) {
        try {
          
            serviceUsuario.eliminacionLogica(bkUsuario.getUsuarioSeleccionado());
            bkUsuario.setListaUsuarios(serviceUsuario.obtenerListaUsuarios(bkUsuario.getUsuarioBusqueda()));
            mostrarMensaje("Se elimino el registro satisfactoriamente");
        } catch (ServiceException ex) {
            mostrarError("Error al actualizar la información");
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public String guardar() {
        if (bkUsuario.getUsuarioFormulario().getContracena().equalsIgnoreCase(bkUsuario.getConfirmarContracena())) {
            try {
                               
                bkUsuario.getUsuarioFormulario().setIdRol(serviceRol.getRol(bkUsuario.getRol()));
                bkUsuario.getUsuarioFormulario().setIdInstitucion(serviceInstituciones.getInstituciones(bkUsuario.getInstitucion()));
                serviceUsuario.guardarUsuario(bkUsuario.getUsuarioFormulario());
                mostrarMensaje("Se guardo el registro satisfactoriamente");
                
                return URLPaginacion.Usuario.URL_EDITAR_USUARIO;
            } catch (ServiceException ex) {
                mostrarError("Error al actualizar la información");
                logger.error(ex.getMessage(), ex);
                 return null;
            }
           
        } else {
            mostrarError("La contracena no coincide");
            return URLPaginacion.Usuario.URL_CREAR_USUARIO;
        }
    }
    
     public String actualizarContracena() {
        if (bkUsuario.getUsuarioFormulario().getContracena().equalsIgnoreCase(bkUsuario.getConfirmarContracena())) {
            try {
                                              
                serviceUsuario.actualizarContracena(bkUsuario.getUsuarioFormulario());
                mostrarMensaje("Se actualizó la contraseña satisfactoriamente");
            } catch (ServiceException ex) {
                mostrarError("Error al actualizar la información");
                logger.error(ex.getMessage(), ex);
            }
            return mostrar();
        } else {
            mostrarError("La contracena no coincide");
            return URLPaginacion.Usuario.URL_LISTA_USUARIO;
        }
    }

    @Override
    public String retroceder() {
        return URLPaginacion.Usuario.URL_LISTA_USUARIO;
    }

    @Override
    public String aceptar() {
        return URLPaginacion.Usuario.URL_LISTA_USUARIO;
    }

    @Override
    public String cancelar() {
        return mostrar();
    }

    @Override
    public String editar() {
        
        try {
            
            bkUsuario.setUsuarioFormulario(serviceUsuario.obtenerUsuarioPorId(bkUsuario.getUsuarioSeleccionado().getId()));           
          
            bkRol.setListaRoles(new ArrayList<Rol>());
            bkRol.setListaRoles(new ArrayList<>(serviceRol.obtenerListaRolesActivos()));
            bkUsuario.setListaInstituciones(serviceInstituciones.listarInstituciones());
            bkUsuario.setRol(bkUsuario.getUsuarioFormulario().getIdRol().getId());
            bkUsuario.setInstitucion(bkUsuario.getUsuarioFormulario().getIdInstitucion().getId());
            bkUsuario.setListaDepartamentos(serviceGeneral.listarDepartamento());
            if(bkUsuario.getUsuarioFormulario().getDepartamento()!=null){
             bkUsuario.setListaProvincias(serviceGeneral.listarProvincia(bkUsuario.getUsuarioFormulario().getDepartamento().trim()));
             if(bkUsuario.getUsuarioFormulario().getProvincia()!=null){
                  bkUsuario.setListaDistritos(serviceGeneral.listarDistrito(bkUsuario.getUsuarioFormulario().getDepartamento().trim(),bkUsuario.getUsuarioFormulario().getProvincia().trim()));
                }
            
            }
            
            
           
            this.listarUsuarioDocumentos();
            return URLPaginacion.Usuario.URL_EDITAR_USUARIO;
        } catch (ServiceException ex) {
            mostrarError("Error al actualizar la información");
            logger.error(ex.getMessage(), ex);
            return mostrar();
        }
    }

     public String editarContracena() {
       
            try {   
                bkUsuario.setUsuarioFormulario(serviceUsuario.obtenerUsuarioPorId(bkUsuario.getUsuarioSeleccionado().getId()));
                return URLPaginacion.Usuario.URL_EDITAR_USUARIO_CONTRACENA;
            } catch (ServiceException ex) {
                mostrarError("Error al actualizar la información");
                logger.error(ex.getMessage(), ex);
                return mostrar();
            }
        
      
    }
     
     
     //Funciones para subir archivos
     
      public void nuevoDocumento(){
        UsuarioDocumentos usuarioDocumentos = new UsuarioDocumentos();
        usuarioDocumentos.setFechaReg(new Date());
        bkUsuarioDocumentos.setUsuarioDocumentoFormulario(usuarioDocumentos);
        bkUsuarioDocumentos.setFileUpload(null);       
    }
      
    
     public void listarUsuarioDocumentos() {
        try {
            bkUsuarioDocumentos.setListaUsuarioDocumentos(serviceUsuarioDocumentos.listarUsuarioDocumentos(bkUsuario.getUsuarioFormulario()));
        } catch (ServiceException e) {
            mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
    }
     
     public void guardarDocumento(){
        try {
            String nombreArchivo =  bkUsuarioDocumentos.getFileUpload().getFileName();
            String extension = nombreArchivo!=null?nombreArchivo.substring(nombreArchivo.lastIndexOf(".")):null;
           
            bkUsuarioDocumentos.getUsuarioDocumentoFormulario().setIdUsuario(bkUsuario.getUsuarioFormulario());
            bkUsuarioDocumentos.getUsuarioDocumentoFormulario().setNombre(nombreArchivo);
            bkUsuarioDocumentos.getUsuarioDocumentoFormulario().setExtension(extension);
            ResultadoServicio rs = serviceUsuarioDocumentos.guardarUsuarioDocumento(bkUsuarioDocumentos.getUsuarioDocumentoFormulario());
            guardarArchivoDocumento((Integer) rs.getID());
                    
            this.listarUsuarioDocumentos();
        } catch (ServiceException e) {
            mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
    }

    private void guardarArchivoDocumento(Integer idUsuarioDocumento) {
       if (bkUsuarioDocumentos.getFileUpload()!= null) {
            try {
                UsuarioFiles usuarioFile = new UsuarioFiles();
                usuarioFile.setId(idUsuarioDocumento);
                usuarioFile.setArchivo(bkUsuarioDocumentos.getFileUpload().getContents());
                usuarioFile.setFechaReg(new Date());
                usuarioFile.setUsuarioDocumentos(bkUsuarioDocumentos.getUsuarioDocumentoFormulario());
                serviceUsuarioFiles.guardarFile(usuarioFile);
            } catch (ServiceException e) {
                mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
                logger.error(e.getMessage(), e);
            }
        }
    }
    
    public void eliminarDocumento(){
        try {
            serviceUsuarioDocumentos.eliminarDocumento(bkUsuarioDocumentos.getUsuarioDocumentoSeleccionado());
            serviceUsuarioFiles.eliminarFile(new UsuarioFiles(bkUsuarioDocumentos.getUsuarioDocumentoSeleccionado().getId()));                        
        } catch (ServiceException e) {
            mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        this.listarUsuarioDocumentos();
    }
    
    public void verArchivo(ActionEvent event){
        try {
            UsuarioDocumentos usuarioDocumento = (UsuarioDocumentos) event.getComponent().getAttributes().get("idUsuarioDocumento");
            UsuarioFiles usuarioFile = this.serviceUsuarioFiles.obtenerUsuarioFiles(usuarioDocumento.getId());
            InputStream myInputStream = new ByteArrayInputStream((byte[]) usuarioFile.getArchivo());
            DefaultStreamedContent ds = new DefaultStreamedContent(myInputStream);
            ds.setName(usuarioDocumento.getNombre());
            this.bkUsuarioDocumentos.setFileDownload(ds);
        } catch (ServiceException e) {
             mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
    }

   
      public void changeListDepartamentos(){
       bkUsuario.setListaProvincias(serviceGeneral.listarProvincia(bkUsuario.getUsuarioFormulario().getDepartamento().trim()));
    } 
    
     public void changeListProvincias(){
       bkUsuario.setListaDistritos(serviceGeneral.listarDistrito(bkUsuario.getUsuarioFormulario().getDepartamento().trim(),bkUsuario.getUsuarioFormulario().getProvincia().trim()));
    } 
     
}
