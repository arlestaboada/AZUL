
package pe.gob.minam.sistema.controlador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarMensaje;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarWarning;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.*;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.sistema.controlador.backingBean.BKPostulante;
import pe.gob.minam.sistema.controlador.backingBean.BKPracticas;
import pe.gob.minam.sistema.controlador.backingBean.BKPracticasDocumentos;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.sistema.entidades.PracticasDocumentos;
import pe.gob.minam.sistema.entidades.PracticasDocumentosFiles;
import pe.gob.minam.sistema.servicio.IServiceGeneral;
import pe.gob.minam.sistema.servicio.IServicePracticas;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentos;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentosFiles;


@Named(value = "MBPracticas")
@Scope("session")
public class MBPracticas extends MBGenerico implements IMantenedor, Serializable {

    private static Logger logger = Logger.getLogger(MBPracticas.class);
    @Inject
    private IServicePracticas servicePracticas;
    @Inject
    private IServicePracticasDocumentos servicePracticasDocumentos;
    @Inject
    private IServicePracticasDocumentosFiles servicePracticasDocumentosFiles;
    @Inject
    private IServiceGeneral serviceGeneral;
    @Inject
    private BKPostulante bkPostulante;
    @Inject
    private BKPracticas bkPracticas;
    @Inject
    private BKPracticasDocumentos bkPracticasDocumentos;
    private StreamedContent file;
    private UploadedFile fileLoad;
    
    @Transient
     byte[] foto;
    
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

        if (bkPostulante.getPostulante() != null) {

            try {
                ResultadoServicio resultadoServicio = servicePracticas.actualizarPracticas(bkPracticas.getPracticas());
                if (resultadoServicio.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {
                    mostrarMensaje(resultadoServicio.getMensaje());
                    bkPracticas.setListaPracticas(servicePracticas.listarPracticas(bkPostulante.getPostulante()));
                } else {
                    mostrarError(resultadoServicio.getMensaje());
                }
            } catch (ServiceException ex) {
                mostrarError("Error al intentar guardar el registro");
                logger.error(ex.getMessage(), ex);
            }
        } else {
            mostrarError("No se puede guardar la buena práctica, no existe datos del postulante ");
        }

        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA;
    }

    @Override
    public String guardar() {

        if (bkPostulante.getPostulante() != null) {

            try {
                bkPracticas.getPracticas().setFechaReg(new Date());
                bkPracticas.getPracticas().setEstadoReg(ESTADO_PRACTICAS.REGISTRADO.getValor());
                bkPracticas.getPracticas().setDepartamento(bkPracticas.getPracticas().getDepartamento().trim());
                bkPracticas.getPracticas().setProvincia(bkPracticas.getPracticas().getProvincia().trim());
                bkPracticas.getPracticas().setDistrito(bkPracticas.getPracticas().getDistrito().trim());
                bkPracticas.getPracticas().setIdpostulante(bkPostulante.getPostulante());
                ResultadoServicio resultadoServicio = servicePracticas.guardarPracticas(bkPracticas.getPracticas());
                if (resultadoServicio.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {

                    //GUARDAMOS LAS IMAGENES
                    int idpractica = Integer.parseInt(String.valueOf(resultadoServicio.getID()));

                    //FOTO
                    if (fileLoad != null) {
                        bkPracticasDocumentos.getPracticasDocumentoFormulario().setIdpractica(servicePracticas.getPracticas(idpractica));
                        bkPracticasDocumentos.getPracticasDocumentoFormulario().setNombre(fileLoad.getFileName());
                        bkPracticasDocumentos.getPracticasDocumentoFormulario().setExtension(fileLoad.getContentType());
                        bkPracticasDocumentos.getPracticasDocumentoFormulario().setFechaReg(new Date());
                        bkPracticasDocumentos.getPracticasDocumentoFormulario().setTipo(Constantes.TipoImagen.FOTO_PRACTICA);

                        ResultadoServicio resultadoFoto = servicePracticasDocumentos.guardarPracticasDocumentos(bkPracticasDocumentos.getPracticasDocumentoFormulario());
                        Integer iddoc = Integer.parseInt(String.valueOf(resultadoFoto.getID()));

                        bkPracticasDocumentos.getPracticasDocumentosFiles().setIddocumento(iddoc);
                        //esto no carga imagen
                        bkPracticasDocumentos.getPracticasDocumentosFiles().setArchivo(foto);
                        bkPracticasDocumentos.getPracticasDocumentosFiles().setFechaReg(new Date());
                        //documento nulo
                        bkPracticasDocumentos.getPracticasDocumentosFiles().setPracticasDocumentos(servicePracticasDocumentos.getPracticasDocumentos(iddoc));
                        //no guarda
                        servicePracticasDocumentosFiles.guardarPracticasDocumentosFiles(bkPracticasDocumentos.getPracticasDocumentosFiles());

                    }
                    //DOCUMENTOS
                    if (bkPracticasDocumentos.getListaAdjuntos().size() > 0) {
                        for (PracticasDocumentos documento : bkPracticasDocumentos.getListaAdjuntos()) {

                            documento.setTipo(Constantes.TipoImagen.DOCUMENTO);
                            documento.setIdpractica(servicePracticas.getPracticas(idpractica));
                            ResultadoServicio resultadoDoc = servicePracticasDocumentos.guardarPracticasDocumentos(documento);
                            int idDocumento = Integer.parseInt(String.valueOf(resultadoDoc.getID()));

                            PracticasDocumentosFiles files = new PracticasDocumentosFiles();
                            files.setIddocumento(idDocumento);
                            files.setArchivo(documento.getArchivo());
                            files.setPracticasDocumentos(servicePracticasDocumentos.getPracticasDocumentos(idDocumento));
                            files.setFechaReg(new Date());

                            servicePracticasDocumentosFiles.guardarPracticasDocumentosFiles(files);
                        }
                    }

                    mostrarMensaje(resultadoServicio.getMensaje());
                   
                } else {
                    mostrarError(resultadoServicio.getMensaje());
                }
            } catch (ServiceException ex) {
                mostrarError("Error al intentar guardar el registro");
                logger.error(ex.getMessage(), ex);
            }
        } else {
            mostrarError("No se puede guardar la buena práctica, no existe datos del postulante ");
        }

        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA_Exito;
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {
        bkPracticas.setEliminar(Boolean.FALSE);
        try {
            file = new DefaultStreamedContent();
            fileLoad = new DefaultUploadedFile();
            Practicas miPractica = bkPracticas.getPracticas();
            bkPracticas.setCodigoDept(miPractica.getDepartamento().trim());
            bkPracticas.setCodigoProv(miPractica.getProvincia().trim());
            bkPracticas.setCodigoDist(miPractica.getDistrito().trim());
            bkPracticas.setOperacion(bkPracticas.getEditarOperacion());
            bkPracticas.setOperacion(bkPracticas.getEditarOperacion());
            bkPracticasDocumentos.setListaAdjuntos(new ArrayList<PracticasDocumentos>());
            bkPracticas.setListaDepartamentos(serviceGeneral.listarDepartamento());
            bkPracticas.setListaProvincias(serviceGeneral.listarProvincia(bkPracticas.getCodigoDept().trim()));
            bkPracticas.setListaDistritos(serviceGeneral.listarDistrito(bkPracticas.getCodigoDept().trim(), bkPracticas.getCodigoProv().trim()));
            bkPracticasDocumentos.setListaAdjuntos(servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(), Constantes.TipoImagen.DOCUMENTO));


            PracticasDocumentos practicasDocumentos = null;
            List<PracticasDocumentos> lista = servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(), Constantes.TipoImagen.FOTO_PRACTICA);
            if (lista != null && !lista.isEmpty()) {
                practicasDocumentos = lista.get(0);
            }
            if (practicasDocumentos != null) {
                bkPracticasDocumentos.setPracticasDocumentoFormulario(practicasDocumentos);
                PracticasDocumentosFiles archivo = servicePracticasDocumentosFiles.getPracticasDocumentosFiles(practicasDocumentos.getIddocumento());
                if (archivo != null && archivo.getArchivo() != null) {
                    bkPracticasDocumentos.setPracticasDocumentosFiles(archivo);
                    InputStream inputProfile = new ByteArrayInputStream(archivo.getArchivo());
                    file = new DefaultStreamedContent(inputProfile, "image/jpeg");
                }
            } else {
                bkPracticasDocumentos.setPracticasDocumentoFormulario(null);
            }

        } catch (ServiceException ex) {
            mostrarError("Error al listar documentos");
            logger.error(ex.getMessage(), ex);
        }
        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_NUEVO;
    }

    @Override
    public String nuevo() {
        fileLoad=null;
        bkPracticas.setEliminar(Boolean.FALSE);
        bkPracticas.setPracticas(new Practicas());
        bkPracticas.setOperacion(bkPracticas.getNuevoOperacion());
        bkPracticas.getPracticas().setEstado(ESTADO_PRACTICAS_DESARROLLO.DESARROLLO.getValor());
        bkPracticas.getPracticas().setInsReconoce(1);//CAMBIAR aqui
        bkPracticas.setListaDepartamentos(serviceGeneral.listarDepartamento());
        bkPracticasDocumentos.setListaAdjuntos(new ArrayList<PracticasDocumentos>());
        bkPracticasDocumentos.setPracticasDocumentoFormulario(new PracticasDocumentos());
        bkPracticasDocumentos.setPracticasDocumentoSeleccionado(new PracticasDocumentos());
        bkPracticasDocumentos.setPracticasDocumentosFiles(new PracticasDocumentosFiles());

        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_NUEVO;
    }

    @Override
    public String ver() {
        bkPracticas.setEliminar(Boolean.TRUE);
        try {
            file = new DefaultStreamedContent();
            fileLoad = new DefaultUploadedFile();

            bkPracticas.setOperacion(bkPracticas.getEditarOperacion());
            bkPracticasDocumentos.setListaAdjuntos(new ArrayList<PracticasDocumentos>());

            bkPracticasDocumentos.setListaAdjuntos(servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(), Constantes.TipoImagen.DOCUMENTO));


            PracticasDocumentos practicasDocumentos = null;
            List<PracticasDocumentos> lista = servicePracticasDocumentos.listarPracticasDocumentos(bkPracticas.getPracticas(), Constantes.TipoImagen.FOTO_PRACTICA);
            if (lista != null && !lista.isEmpty()) {
                practicasDocumentos = lista.get(0);
            }
            if (practicasDocumentos != null) {
                bkPracticasDocumentos.setPracticasDocumentoFormulario(practicasDocumentos);
                PracticasDocumentosFiles archivo = servicePracticasDocumentosFiles.getPracticasDocumentosFiles(practicasDocumentos.getIddocumento());
                if (archivo != null && archivo.getArchivo() != null) {
                    bkPracticasDocumentos.setPracticasDocumentosFiles(archivo);
                    InputStream inputProfile = new ByteArrayInputStream(archivo.getArchivo());
                    file = new DefaultStreamedContent(inputProfile, "image/jpeg");
                }
            } else {
                bkPracticasDocumentos.setPracticasDocumentoFormulario(null);
            }

        } catch (ServiceException ex) {
            mostrarError("Error al listar documentos");
            logger.error(ex.getMessage(), ex);
        }
        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_NUEVO;
    }

    @Override
    public String aceptar() {
        if (fileLoad != null) {
            if (bkPracticas.getOperacion() == bkPracticas.getNuevoOperacion()) {
                return guardar();
            } else if (bkPracticas.getOperacion() == bkPracticas.getEditarOperacion()) {
                return actualizar();
            } else {
                return null;
            }
        } else {
            mostrarWarning("Debes cargar la foto representativa de buena práctica");
            return null;
        }


    }

    @Override
    public String retroceder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cancelar() {
        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA;
    }

    @Override
    public String mostrar() {
        try {

            bkPracticas.setListaPracticas(servicePracticas.listarPracticas(bkPostulante.getPostulante()));

        } catch (ServiceException ex) {
            mostrarError("Error al listar las buenas prácticas");
            logger.error(ex.getMessage(), ex);
        }
        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA;

    }

    public String enviar() {
        bkPracticas.getPracticas().setEstadoReg(ESTADO_PRACTICAS.ENVIADO.getValor());
        ResultadoServicio resultadoServicio = servicePracticas.actualizarPracticas(bkPracticas.getPracticas());
        if (resultadoServicio.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {
            try {
                mostrarMensaje("La buena práctica se ha enviado satisfactoriamente");
                bkPracticas.setListaPracticas(servicePracticas.listarPracticas(bkPostulante.getPostulante()));
            } catch (ServiceException ex) {
                mostrarError("Error al intentar enviar el registro");
                logger.error(ex.getMessage(), ex);

            }
        } else {
            mostrarError(resultadoServicio.getMensaje());
        }
        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA;
    }

    public void handleFileUpload(FileUploadEvent event) {

        if (bkPracticas.getOperacion() == bkPracticas.getNuevoOperacion()) {
            try {
              
                fileLoad = event.getFile();
                
                InputStream inputProfile = fileLoad.getInputstream();
                foto = IOUtils.toByteArray(fileLoad.getInputstream());
                file = new DefaultStreamedContent(inputProfile, "image/jpeg");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else if (bkPracticas.getOperacion() == bkPracticas.getEditarOperacion()) {
            try {
                fileLoad = event.getFile();
                InputStream inputProfile = fileLoad.getInputstream();
                file = new DefaultStreamedContent(inputProfile, "image/jpeg");

                try {
                    //ELIMINAMOS LA IMAGEN ANTERIOR
                    // servicePracticasDocumentosFiles.eliminarPracticasDocumentosFiles(bkPracticasDocumentos.getPracticasDocumentosFiles());
                    if (bkPracticasDocumentos.getPracticasDocumentoFormulario() != null) {
                        servicePracticasDocumentos.eliminarPracticasDocumentos(bkPracticasDocumentos.getPracticasDocumentoFormulario());
                    }


                    //CARGAMOS LA NUEVA IMAGEN
                    PracticasDocumentos documento = new PracticasDocumentos();
                    documento.setArchivo(fileLoad.getContents());
                    documento.setNombre(event.getFile().getFileName());
                    documento.setExtension(event.getFile().getContentType());
                    documento.setFechaReg(new Date());
                    documento.setTipo(Constantes.TipoImagen.FOTO_PRACTICA);
                    documento.setIdpractica(bkPracticas.getPracticas());
                    ResultadoServicio resultadoDoc = servicePracticasDocumentos.guardarPracticasDocumentos(documento);
                    int idDocumento = Integer.parseInt(String.valueOf(resultadoDoc.getID()));

                    PracticasDocumentosFiles files = new PracticasDocumentosFiles();
                    files.setIddocumento(idDocumento);
                    files.setArchivo(documento.getArchivo());
                    files.setPracticasDocumentos(servicePracticasDocumentos.getPracticasDocumentos(idDocumento));
                    files.setFechaReg(new Date());
                    servicePracticasDocumentosFiles.guardarPracticasDocumentosFiles(files);

                } catch (ServiceException e) {
                    mostrarError("Ocurrieron algunos problemas al guadar los documentos");
                    logger.error(e.getMessage(), e);
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }

    }

    public StreamedContent getFile() {
        return file;
    }
//    public StreamedContent getFile() {
//            try {
//                    if ( fileLoad.getContents()!= null){
//                            InputStream inputProfile = new ByteArrayInputStream(fileLoad.getContents());  	
//                            file = new DefaultStreamedContent(inputProfile, "image/jpeg");
//                    }
//                    else {
//                            file = null;
//                    }
//            } catch (Exception e) {
//                    logger.error(e.getMessage());
//            }
//            return file;
//    }

    public void cargarArchivos(FileUploadEvent event) {

        if (bkPracticas.getOperacion() == bkPracticas.getNuevoOperacion()) {

            try {
                if (event.getFile() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
                }
                InputStream file = null;

                try {
                    file = event.getFile().getInputstream();
                } catch (IOException e) {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al leer el archivo" + e, null));
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[10240];

                try {
                    for (int readNum; (readNum = file.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum); //no doubt here is 0
                    }
                } catch (IOException ex) {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al leer el archivo,desborde de tamaño" + ex, null));
                }
                byte[] bytes = bos.toByteArray();



                PracticasDocumentos documento = new PracticasDocumentos();
                documento.setArchivo(bytes);
                documento.setNombre(event.getFile().getFileName());
                documento.setExtension(event.getFile().getContentType());
                documento.setFechaReg(new Date());

                bkPracticasDocumentos.getListaAdjuntos().add(documento);

                file.close();
                bos.close();
                mostrarMensaje("El archivo ha sido adjuntado a la buena práctica.");
            } catch (IOException e) {
                mostrarError("Ocurrieron algunos problemas al actualizar la información. Vuelva a intentar.");
                logger.error(e.getMessage(), e);
            }

        } else if (bkPracticas.getOperacion() == bkPracticas.getEditarOperacion()) {

            try {
                if (event.getFile() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
                }
                InputStream file = null;

                try {
                    file = event.getFile().getInputstream();
                } catch (IOException e) {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al leer el archivo" + e, null));
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[10240];

                try {
                    for (int readNum; (readNum = file.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum); //no doubt here is 0
                    }
                } catch (IOException ex) {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al leer el archivo,desborde de tamaño" + ex, null));
                }
                byte[] bytes = bos.toByteArray();


                try {
                    PracticasDocumentos documento = new PracticasDocumentos();
                    documento.setArchivo(bytes);
                    documento.setNombre(event.getFile().getFileName());
                    documento.setExtension(event.getFile().getContentType());
                    documento.setFechaReg(new Date());
                    documento.setTipo(Constantes.TipoImagen.DOCUMENTO);
                    documento.setIdpractica(bkPracticas.getPracticas());
                    ResultadoServicio resultadoDoc = servicePracticasDocumentos.guardarPracticasDocumentos(documento);
                    int idDocumento = Integer.parseInt(String.valueOf(resultadoDoc.getID()));

                    PracticasDocumentosFiles files = new PracticasDocumentosFiles();
                    files.setIddocumento(idDocumento);
                    files.setArchivo(documento.getArchivo());
                    files.setPracticasDocumentos(servicePracticasDocumentos.getPracticasDocumentos(idDocumento));
                    files.setFechaReg(new Date());
                    servicePracticasDocumentosFiles.guardarPracticasDocumentosFiles(files);

                    bkPracticasDocumentos.getListaAdjuntos().add(documento);

                } catch (ServiceException e) {
                    mostrarError("Ocurrieron algunos problemas al guardar los documentos");
                    logger.error(e.getMessage(), e);
                }

                file.close();
                bos.close();
                mostrarMensaje("El archivo ha sido adjuntado a su  buena práctica.");
            } catch (IOException e) {
                mostrarError("Ocurrieron algunos problemas al actualizar la información. Vuelva a intentar.");
                logger.error(e.getMessage(), e);
            }
        }




    }

    public String eliminarBuenaPractica() {
        if (bkPostulante.getPostulante() != null) {


            try {
                ResultadoServicio resultadoServicio = servicePracticas.eliminarPracticas(bkPracticas.getPracticas());
                if (resultadoServicio.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {
                    mostrarMensaje(resultadoServicio.getMensaje());
                    bkPracticas.setListaPracticas(servicePracticas.listarPracticas(bkPostulante.getPostulante()));
                } else {
                    mostrarError(resultadoServicio.getMensaje());
                }
            } catch (ServiceException ex) {
                mostrarError("Error al intentar guardar el registro");
                logger.error(ex.getMessage(), ex);
            }
        } else {
            mostrarError("No se puede guardar la buena práctica, no existe datos del postulante ");
        }

        return URLPaginacion.Postulante.URL_BUENA_PRACTICA_LISTA;
    }

    public void descargarArchivos() {
        System.out.println("tu tienes el póder");
        
    }

    public void eliminarArchivos() {
        try {
            servicePracticasDocumentos.eliminarPracticasDocumentos(bkPracticasDocumentos.getPracticasDocumentoSeleccionado());
            bkPracticasDocumentos.getListaAdjuntos().remove(bkPracticasDocumentos.getPracticasDocumentoSeleccionado());
        } catch (Exception e) {
            mostrarError("Ocurrieron algunos problemas al actualizar la información. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
    }

    public void changeListDepartamentos() {
        bkPracticas.getListaProvincias().clear();
        bkPracticas.getListaDistritos().clear();
        bkPracticas.setListaProvincias(serviceGeneral.listarProvincia(bkPracticas.getPracticas().getDepartamento().trim()));
    }

    public void changeListProvincias() {
        bkPracticas.setListaDistritos(serviceGeneral.listarDistrito(bkPracticas.getPracticas().getDepartamento().trim(), bkPracticas.getPracticas().getProvincia().trim()));
    }
}
