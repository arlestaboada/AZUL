
package pe.gob.minam.sistema.controlador;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.controlador.backingBean.BKCorreos;
import pe.gob.minam.sistema.controlador.backingBean.BKCorreosDocumentos;
import pe.gob.minam.sistema.servicio.IServiceCorreos;
import pe.gob.minam.sistema.servicio.IServiceCorreosDocumentos;
import pe.gob.minam.sistema.entidades.Correos;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentosPK;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.controlador.backingBean.BKSession;




@Named(value = "MBCorreos")
@Scope("request")
public class MBCorreos extends MBGenerico implements IMantenedor, Serializable {

    private static Logger logger = Logger.getLogger(MBCorreos.class);

    
    @Inject
    private BKSession bkSession;
    @Inject
    private BKCorreos bkCorreos;    
    @Inject
    private BKCorreosDocumentos bkCorreosDocumentos;
    @Inject
    private IServiceCorreos serviceCorreos;   
    @Inject
    private IServiceCorreosDocumentos serviceCorreosDocumentos;
    
    
    @Override
    public String actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String guardar() {
        
          try {           
               
                bkCorreos.getCorreosFormulario().setEmisor(bkSession.getUsuario().getCorreo());
                bkCorreos.getCorreosFormulario().setHoraEnvio(new Date());              
                bkCorreos.getCorreosFormulario().setFechaEnvio(new Date());
                bkCorreos.getCorreosFormulario().setHoraEnvio(new Date());               
                ResultadoServicio resultado = serviceCorreos.guardarCorreos(bkCorreos.getCorreosFormulario());
                long idcorreo = Long.parseLong(String.valueOf(resultado.getID()));

                bkCorreos.getCorreosFormulario().setId(new BigDecimal(idcorreo));

                //guardamos los documentos adjuntos        
                if(bkCorreosDocumentos.getListaAdjuntos().size()>0){
                    int indice = 1;
                    for (CorreosDocumentos documento : bkCorreosDocumentos.getListaAdjuntos()) {

                        CorreosDocumentosPK iddocumento;               
                        iddocumento = new CorreosDocumentosPK(new BigDecimal(idcorreo), indice);
                        documento.setCorreosDocumentosPK(iddocumento);
                        documento.setCorreos(bkCorreos.getCorreosFormulario());               
                        serviceCorreosDocumentos.guardarCorreosDocumentos(documento);

                        indice++;
                    }
                }
        
                this.enviarEmail();                
                
                 mostrarMensaje("El mensaje se guardo satisfactoriamente.");
         } catch (Exception e) {
             mostrarError("Ocurrieron algunos problemas al enviar  la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {
        
        try {
            bkCorreos.setCorreosFormulario(bkCorreos.getCorreosSeleccionado());
            bkCorreosDocumentos.setListaAdjuntos(serviceCorreosDocumentos.listarAdjuntos(bkCorreos.getCorreosFormulario().getId().longValue()));            
            return URLPaginacion.Correos.URL_EDITAR_CORREOS;
        } catch (ServiceException ex) {
            java.util.logging.Logger.getLogger(MBCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }

    @Override
    public String nuevo() {
        bkCorreos.setCorreosFormulario(new Correos());
        bkCorreosDocumentos.setListaAdjuntos(new ArrayList<CorreosDocumentos>());
        return URLPaginacion.Correos.URL_CREAR_CORREOS;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String mostrar(){        
        try {
            bkCorreos.setListaEdbCorreos(serviceCorreos.listarCorreos());
        } catch (Exception e) {
            e.printStackTrace();
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        
        return URLPaginacion.Correos.URL_LISTAR_CORREOS;
    }    
    

    
     public void cargarArchivos(FileUploadEvent event) {
        try {
            if (event.getFile() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
            }
            InputStream file= null;
           
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
            
           
             
             CorreosDocumentos documento = new CorreosDocumentos();            
             documento.setArchivo(bytes);
             documento.setNombre(event.getFile().getFileName());
             documento.setExtension(event.getFile().getContentType());
             documento.setFechaReg(new Date());
           
             bkCorreosDocumentos.getListaAdjuntos().add(documento);
             
            file.close();
            bos.close();
            mostrarMensaje("El archivo ha sido adjuntado al correo.");
        } catch (IOException e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
          
 
    } 
      
    public void descargarArchivos() {
//        try {    
//            PolizaRgDocumentos documento =servicioPolizaRgDocumentos.getPolizaRgDocumentos(new PolizaRgDocumentosPK(bkPolizaRgDocumentos.getPolizaRgDocumentosSeleccionado().getIdpoliza(),
//                                                                                              bkPolizaRgDocumentos.getPolizaRgDocumentosSeleccionado().getIddocumento()));
//                FacesContext context = FacesContext.getCurrentInstance();
//                if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//                    // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
//                    
//                    System.out.println("entro al defecto");
//                    bkPolizaRgDocumentos.setReporte(new DefaultStreamedContent());
//
//                } else {
//                    // So, browser is requesting the media. Return a real StreamedContent with the media bytes.
//                    System.out.println("entro al reporte");
//                     StreamedContent file = new DefaultStreamedContent(new ByteArrayInputStream(documento.getFile()), documento.getExtension(),documento.getNombre()); 
//                     bkPolizaRgDocumentos.setReporte(file);
//                }
//                
//        } catch (ServiceException e) {
//             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
//            logger.error(e.getMessage(), e);
//        }
//        
    }
    
     public void eliminarArchivos() {
        try {
            serviceCorreosDocumentos.eliminarCorreosDocumentos(bkCorreosDocumentos.getCorreoDocumentoSeleccionado());
            bkCorreosDocumentos.getListaAdjuntos().remove(bkCorreosDocumentos.getCorreoDocumentoSeleccionado());
        } catch (Exception e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
    }
     
   
    
    private void enviarEmail(){

         try
	    {
                
            Properties properties = new Properties(); 
            String propFileName = "pe/gob/minam/configuracion/correo.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
                
            Properties props = new Properties();
            props.setProperty("mail.smtp.submitter",properties.getProperty("mail.email.usuario"));
            props.setProperty("mail.transport.protocol", properties.getProperty("mail.transport.protocol")); 
            props.setProperty("mail.smtp.host",properties.getProperty("mail.smtp.host"));
            props.setProperty("mail.smtp.starttls.enable", properties.getProperty("mail.smtp.starttls.enable"));
            props.setProperty("mail.smtp.port", properties.getProperty("mail.smtp.port"));  
            props.setProperty("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
            props.setProperty("mail.smtp.socketFactory.port", properties.getProperty("mail.smtp.socketFactory.port"));  
            props.setProperty("mail.smtp.socketFactory.fallback", properties.getProperty("mail.smtp.socketFactory.fallback"));
            
            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.email.usuario")));
            message.addRecipients(Message.RecipientType.TO,bkCorreos.getCorreosFormulario().getDestinatarios());
            
            if(bkCorreos.getCorreosFormulario().getCopia()!=null){
                if(bkCorreos.getCorreosFormulario().getCopia().length()>0){
                    message.addRecipients(Message.RecipientType.CC,bkCorreos.getCorreosFormulario().getCopia());
                }
            }
            
             if(bkCorreos.getCorreosFormulario().getCopiaoculta()!=null){
                if(bkCorreos.getCorreosFormulario().getCopiaoculta().length()>0){
                     message.addRecipients(Message.RecipientType.BCC,bkCorreos.getCorreosFormulario().getCopiaoculta());
                }
            }
          
            message.setSubject(bkCorreos.getCorreosFormulario().getAsunto());

            MimeMultipart multiParte = new MimeMultipart();
            if(!bkCorreosDocumentos.getListaAdjuntos().isEmpty()){
                    for (int i = 0; i < bkCorreosDocumentos.getListaAdjuntos().size(); i++) {
                            CorreosDocumentos archivo = bkCorreosDocumentos.getListaAdjuntos().get(i);
                            BodyPart adjunto = new MimeBodyPart();
                            adjunto.setDataHandler(new DataHandler(archivo.getArchivo(),archivo.getExtension()));
                            adjunto.setFileName(archivo.getNombre());
                            multiParte.addBodyPart(adjunto);
                    }

            }
            BodyPart texto = new MimeBodyPart();
            texto.setContent(bkCorreos.getCorreosFormulario().getMensaje(),"text/html");
            multiParte.addBodyPart(texto);            
            message.setContent(multiParte);

            String usuario = properties.getProperty("mail.email.usuario").trim();                
            String password = properties.getProperty("mail.email.password").trim();
            // Lo enviamos.
            Transport t = session.getTransport("smtp");               
            t.connect(usuario,password);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            mostrarMensaje("El Correo se envio satisfactoriamente.");
        }
        catch (Exception e)
        {
                 mostrarError("Correo no Enviado...Ocurrio un error al enviar el correo. Verifique  la conexion y  sus datos de envio ");
            e.printStackTrace();
        }
    }
}
