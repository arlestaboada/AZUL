
package pe.gob.minam.sistema.controlador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.seguridad.common.util.PasswordGenerator;
import pe.gob.minam.seguridad.controlador.backingBean.BKUsuario;
import pe.gob.minam.seguridad.servicio.IServiceUsuario;
import pe.gob.minam.sistema.controlador.backingBean.BKPostulante;
import pe.gob.minam.sistema.entidades.Instituciones;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;
import pe.gob.minam.sistema.entidades.Rol;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.sistema.servicio.IServiceGeneral;
import pe.gob.minam.sistema.servicio.IServicePostulante;
import pe.gob.minam.sistema.servicio.IServicePostulanteDocumentos;
import pe.gob.minam.sistema.servicio.IServicePostulanteDocumentosFiles;


@Named(value = "MBPostulantes")
@Scope("session")
public class MBPostulantes extends MBGenerico implements IMantenedor, Serializable {

    private static Logger logger = Logger.getLogger(MBPostulantes.class);
    @Inject
    private IServicePostulante servicePostulante;
    @Inject
    private IServicePostulanteDocumentos servicePostulanteDocumentos;
    @Inject
    private IServicePostulanteDocumentosFiles servicePostulanteDocumentosFiles;
    @Inject
    private IServiceUsuario serviceUsuario;
    @Inject
    private BKUsuario bkUsuario;
    @Inject
    private BKPostulante bkPostulante;
    @Inject
    private IServiceGeneral serviceGeneral;
 
    private StreamedContent file;
    private UploadedFile fileLoad;

    @Transient
    private byte[] foto;
    
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


        ResultadoServicio resultado = servicePostulante.actualizarPostulante(bkPostulante.getPostulante());
        if (resultado.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {
            mostrarMensaje("Tu información se  actualizó correctamente");
            return null;
        } else {
            mostrarError("Ocurrieron algunos problemas al actualizar la información. Vuelva a intentar.");
            return null;
        }



    }

    @Override
    public String guardar() {

        try {
            Postulante existePostulante = servicePostulante.getPostulante(bkPostulante.getPostulante().getNrodni(), bkPostulante.getPostulante().getEmail());
            if (existePostulante == null) {

                bkPostulante.getPostulante().setFechareg(new Date());
                ResultadoServicio resultado = servicePostulante.guardarPostulante(bkPostulante.getPostulante());
                Integer identidad = Integer.parseInt(String.valueOf(resultado.getID()));

                Postulante postulante = null;
                try {
                    postulante = servicePostulante.getPostulante(identidad);
                } catch (ServiceException ex) {
                    logger.error(ex.getMessage());
                }

                if (postulante != null) {

                    String contrasena = PasswordGenerator.getPasswordMasyusculasNumeros(6);

                    try {
                        if (fileLoad != null) {
                            bkPostulante.getPostulanteDocumentos().setIdpostulante(postulante);

                            bkPostulante.getPostulanteDocumentos().setNombre(fileLoad.getFileName());
                            bkPostulante.getPostulanteDocumentos().setExtension(fileLoad.getContentType());

                            bkPostulante.getPostulanteDocumentos().setFechaReg(new Date());
                            bkPostulante.getPostulanteDocumentos().setTipo(1);

                            ResultadoServicio resultadoDoc = servicePostulanteDocumentos.guardarPostulanteDocumentos(bkPostulante.getPostulanteDocumentos());
                            Integer iddoc = Integer.parseInt(String.valueOf(resultadoDoc.getID()));

                            bkPostulante.getPostulanteDocumentosFiles().setIddocumento(iddoc);


                            bkPostulante.getPostulanteDocumentosFiles().setArchivo(foto);

                            bkPostulante.getPostulanteDocumentosFiles().setFechaReg(new Date());

                            servicePostulanteDocumentosFiles.guardarPostulanteDocumentosFiles(bkPostulante.getPostulanteDocumentosFiles());
                        }


                        //CREAMOS EL USUARIO
                        Instituciones instituciones = new Instituciones(1);
                        Rol rol = new Rol(2);


                        bkUsuario.setUsuarioFormulario(new Usuario());

                        bkUsuario.getUsuarioFormulario().setApellidos(bkPostulante.getPostulante().getApellidos());
                        bkUsuario.getUsuarioFormulario().setNombres(bkPostulante.getPostulante().getNombres());
                        bkUsuario.getUsuarioFormulario().setTelefono1(bkPostulante.getPostulante().getTelefonos());
                        bkUsuario.getUsuarioFormulario().setCorreo(bkPostulante.getPostulante().getEmail().toLowerCase());
                        bkUsuario.getUsuarioFormulario().setTipo(Constantes.TipoUsuario.POSTULANTE);
                        bkUsuario.getUsuarioFormulario().setIdInstitucion(instituciones);
                        bkUsuario.getUsuarioFormulario().setIdRol(rol);
                        bkUsuario.getUsuarioFormulario().setIdReferencia(bkPostulante.getPostulante().getIdpostulante());
                        bkUsuario.getUsuarioFormulario().setUsuario(bkPostulante.getPostulante().getEmail().trim().toLowerCase());
                        bkUsuario.getUsuarioFormulario().setContracena(contrasena);

                        ResultadoServicio resultadoUsuario = serviceUsuario.guardarUsuario(bkUsuario.getUsuarioFormulario());

                        if (resultadoUsuario.getEstadoResultado() == ESTADO_RESULTADO.EXITOSO) {

                            Properties props = new Properties();
                            String propFileName = "pe/gob/minam/configuracion/correo.properties";
                            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                            props.load(inputStream);

                            //ENVIAR CORREO
                            Properties properties = new Properties();
                            properties.put("mail.transport.protocol", props.getProperty("mail.transport.protocol"));
                            properties.put("mail.smtp.host", props.getProperty("mail.smtp.host"));
                            properties.put("mail.smtp.port", props.getProperty("mail.smtp.port"));
                            properties.put("mail.debug", props.getProperty("mail.smtp.debug"));
                            properties.put("mail.smtp.auth", props.getProperty("mail.smtp.auth"));
                            properties.put("mail.smtp.user", props.getProperty("mail.email.usuario"));

                            final String usuario = props.getProperty("mail.email.usuario");
                            final String password = props.getProperty("mail.email.password");

                            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(usuario, password);
                                }
                            });
                            MimeMessage message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(props.getProperty("mail.email.usuario")));
                            message.setSubject("Registro de postulante CVERDE");

                            String mensaje = "Hola "
                                    + bkPostulante.getPostulante().getNombres()
                                    + " tu usuario y contraseña para poder ingresar al aplicativo CVERDE es el siguiente: "
                                    + "Usuario: " + bkPostulante.getPostulante().getEmail() + " Contraseña: " + contrasena;

                            System.out.println("Aqui!!!" + bkPostulante.getPostulante().getEmail() + " contra:" + contrasena);
                            message.setText(mensaje);

                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(bkPostulante.getPostulante().getEmail().trim()));
                            Transport t = session.getTransport("smtp");
                            t.connect(props.getProperty("mail.smtp.host"), usuario, password);

                            try {
                                t.sendMessage(message, message.getAllRecipients());
                                t.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                logger.error(ex.getMessage());
                                mostrarError("Problema al enviar el correo");
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.error(ex.getMessage());
                        mostrarError("Ocurrieron algunos problemas crear el usuario. Vuelva a intentar.");

                    }


                    return URLPaginacion.Postulante.URL_FICHA_INSCRIPCION_OK;
                } else {
                    mostrarError("Ocurrieron algunos problemas al enviar  la informacion. Vuelva a intentar.");
                    return null;
                }
            } else {
                mostrarError("No se puede registrar, ya existe un postulante con este correo electrónico, verifique porfavor");
                return null;
            }
        } catch (ServiceException ex) {
            mostrarError("Ocurrieron algunos problemas al enviar  la informacion. Vuelva a intentar.");
            return null;
        }

    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {

        try {
            file = new DefaultStreamedContent();
            fileLoad = new DefaultUploadedFile();
            bkPostulante.setListaDepartamentos(serviceGeneral.listarDepartamento());
            bkPostulante.setOperacion(bkPostulante.getEditarOperacion());
            bkPostulante.setListaProvincias(serviceGeneral.listarProvincia(bkPostulante.getPostulante().getDepartamento().trim()));
            bkPostulante.setListaDistritos(serviceGeneral.listarDistrito(bkPostulante.getPostulante().getDepartamento().trim(), bkPostulante.getPostulante().getProvincia().trim()));
            bkPostulante.setListaAdjuntos(new ArrayList<PostulanteDocumentos>());

            PostulanteDocumentos postulanteDocumentos = null;
            List<PostulanteDocumentos> lista = servicePostulanteDocumentos.listarPostulanteDocumentos(bkPostulante.getPostulante(), Constantes.TipoImagen.FOTO_POSTULANTE);
            if (lista != null && !lista.isEmpty()) {
                postulanteDocumentos = lista.get(0);
            }
            if (postulanteDocumentos != null) {
                bkPostulante.setPostulanteDocumentos(postulanteDocumentos);
                PostulanteDocumentosFiles archivo = servicePostulanteDocumentosFiles.getPostulanteDocumentosFiles(postulanteDocumentos.getIddocumento());
                if (archivo != null) {
                    if (archivo.getArchivo() != null) {
                        bkPostulante.setPostulanteDocumentosFiles(archivo);
                        InputStream inputProfile = new ByteArrayInputStream(archivo.getArchivo());
                        file = new DefaultStreamedContent(inputProfile, "image/jpeg");
                    }

                }
            } else {
                bkPostulante.setPostulanteDocumentos(null);
            }

        } catch (ServiceException ex) {
            mostrarError("Error al listar documentos");
            logger.error(ex.getMessage(), ex);
        }

        return URLPaginacion.Postulante.URL_FICHA_INSCRIPCION;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String retroceder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cancelar() {
        return "/login.xhtml?faces-redirect=true";
    }

    @Override
    public String mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (bkPostulante.getOperacion() == bkPostulante.getNuevoOperacion()) {
            try {
                fileLoad = event.getFile();
          
                InputStream inputProfile = fileLoad.getInputstream();
                foto = IOUtils.toByteArray(fileLoad.getInputstream());
                file = new DefaultStreamedContent(inputProfile, "image/jpeg");

            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else if (bkPostulante.getOperacion() == bkPostulante.getEditarOperacion()) {
            try {
                fileLoad = event.getFile();
                InputStream inputProfile = fileLoad.getInputstream();
                foto = IOUtils.toByteArray(fileLoad.getInputstream());
                file = new DefaultStreamedContent(inputProfile, "image/jpeg");

                try {
                    //ELIMINAMOS LA IMAGEN ANTERIOR
                    // servicePostulanteDocumentosFiles.eliminarPostulanteDocumentosFiles(bkPostulanteDocumentos.getPostulanteDocumentosFiles());
                    if (bkPostulante.getPostulanteDocumentos() != null) {
                        servicePostulanteDocumentos.eliminarPostulanteDocumentos(bkPostulante.getPostulanteDocumentos());
                    }


                    //CARGAMOS LA NUEVA IMAGEN
                    PostulanteDocumentos documento = new PostulanteDocumentos();
                    documento.setNombre(event.getFile().getFileName());
                    documento.setExtension(event.getFile().getContentType());
                    documento.setFechaReg(new Date());
                    documento.setTipo(Constantes.TipoImagen.FOTO_POSTULANTE);
                    documento.setIdpostulante(bkPostulante.getPostulante());
                    ResultadoServicio resultadoDoc = servicePostulanteDocumentos.guardarPostulanteDocumentos(documento);
                    int idDocumento = Integer.parseInt(String.valueOf(resultadoDoc.getID()));

                    PostulanteDocumentosFiles files = new PostulanteDocumentosFiles();
                    files.setIddocumento(idDocumento);
                    files.setArchivo(fileLoad.getContents());
                    files.setFechaReg(new Date());
                    servicePostulanteDocumentosFiles.guardarPostulanteDocumentosFiles(files);

                } catch (Exception e) {
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

    public void changeListDepartamentos() {
        bkPostulante.setListaProvincias(serviceGeneral.listarProvincia(bkPostulante.getPostulante().getDepartamento().trim()));
    }

    public void changeListProvincias() {
        bkPostulante.setListaDistritos(serviceGeneral.listarDistrito(bkPostulante.getPostulante().getDepartamento().trim(), bkPostulante.getPostulante().getProvincia().trim()));
    }
}
