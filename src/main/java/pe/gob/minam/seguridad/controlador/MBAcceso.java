package pe.gob.minam.seguridad.controlador;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.gob.minam.common.controlador.MBGenerico;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.POSTULANTE_CATEGORIA;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.controlador.backingBean.BKSession;
import pe.gob.minam.seguridad.controlador.helper.MenuBarHelper;
import pe.gob.minam.seguridad.servicio.IServiceMenuRol;
import pe.gob.minam.sistema.controlador.backingBean.BKPostulante;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;
import pe.gob.minam.sistema.servicio.IServiceGeneral;
import pe.gob.minam.sistema.servicio.IServicePostulante;

@Named(value = "MBAcceso")
@Scope("session")
public class MBAcceso extends MBGenerico implements Serializable {

    private static final long serialVersionUID = 9015531053058454499L;
    private static Logger logger = Logger.getLogger(MBAcceso.class);
    @Inject
    private BKSession bkSession;
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private IServiceMenuRol serviceMenuRol;
    @Inject
    private BKPostulante bkPostulante;
    @Inject
    private IServicePostulante servicePostulante;
    @Inject
    private IServiceGeneral serviceGeneral;
    private Menubar principalMenuBar;

    public MBAcceso() {
    }

    public String validarAcceso() {
        bkSession.setUsuario(new Usuario());
        bkSession.getUsuario().setUsuario(bkSession.getUser().toLowerCase());
        bkSession.getUsuario().setContracena(bkSession.getPass());
        logger.info("Validando acceso " + bkSession.getUsuario().getUsuario());
        Authentication request = new UsernamePasswordAuthenticationToken(bkSession.getUsuario().getUsuario(), bkSession.getUsuario().getContracena());
        Authentication resultado = authenticationManager.authenticate(request);
        logger.info("resultado " + resultado);
        if (resultado.isAuthenticated()) {
            Usuario usuario = (Usuario) resultado.getPrincipal();
            this.bkSession.setUsuario(usuario);
            SecurityContextHolder.getContext().setAuthentication(resultado);

            try {
                bkPostulante.setPostulante(servicePostulante.getPostulante(usuario.getIdReferencia()));
            } catch (ServiceException ex) {
                bkPostulante.setPostulante(null);
                logger.error("No cargó postulante");
            }
            System.out.println(URLPaginacion.Seguridad.URL_HOME);
            return URLPaginacion.Seguridad.URL_HOME;


        } else {
            this.bkSession.setUsuario(null);
            SecurityContextHolder.getContext().setAuthentication(null);
            mostrarError("Usuario no existe, ingrese correctamente su usuario y contraseña");
            return URLPaginacion.Seguridad.URL_LOGIN;
        }
    }

    public String validarAccesoAdmin() {
        bkSession.setUsuario(new Usuario());
        bkSession.getUsuario().setUsuario(bkSession.getUser().toLowerCase());
        bkSession.getUsuario().setContracena(bkSession.getPass());
        logger.info("Validando acceso " + bkSession.getUsuario().getUsuario());
        Authentication request = new UsernamePasswordAuthenticationToken(bkSession.getUsuario().getUsuario(), bkSession.getUsuario().getContracena());
        Authentication resultado = authenticationManager.authenticate(request);
        logger.info("resultado " + resultado);
        if (resultado.isAuthenticated()) {
            Usuario usuario = (Usuario) resultado.getPrincipal();
            this.bkSession.setUsuario(usuario);
            SecurityContextHolder.getContext().setAuthentication(resultado);

            return URLPaginacion.Seguridad.URL_HOME;


        } else {
            this.bkSession.setUsuario(null);
            SecurityContextHolder.getContext().setAuthentication(null);
            return URLPaginacion.Seguridad.URL_LOGIN;
        }
    }

    public String entrar() {
        logger.info("Entrar!!!");
        return URLPaginacion.Seguridad.URL_HOME;
    }

    public String cerrar() {
        this.bkSession.setUsuario(null);
        SecurityContextHolder.getContext().setAuthentication(null);
        return URLPaginacion.Seguridad.URL_LOGIN;
    }

    private void cargarMenuBar() {
        try {
            if (bkSession.getListaMenu() == null) {
                bkSession.setListaMenu(serviceMenuRol.obtenerListaMenuPorRol(this.bkSession.getUsuario().getIdRol().getId()));
            }
            this.setPrincipalMenuBar(MenuBarHelper.getMenuBar(bkSession.getListaMenu()));
        } catch (Exception e) {
            String msg = "no se pudo crear el componente menubar";
            mostrarError(msg);
            logger.error(e.getMessage(), e);
        }
    }

    public Menubar getPrincipalMenuBar() {
        cargarMenuBar();
        return principalMenuBar;
    }

    public void setPrincipalMenuBar(Menubar principalMenuBar) {
        this.principalMenuBar = principalMenuBar;
    }

    public String registrar() {
        bkPostulante.setPostulante(new Postulante());
        bkPostulante.getPostulante().setCategoria(POSTULANTE_CATEGORIA.A.getValor());
        bkPostulante.getPostulante().setGenero(1);
        bkPostulante.setPostulanteDocumentos(new PostulanteDocumentos());
        bkPostulante.setPostulanteDocumentosFiles(new PostulanteDocumentosFiles());
        bkPostulante.setOperacion(bkPostulante.getNuevoOperacion());
        bkPostulante.setListaDepartamentos(serviceGeneral.listarDepartamento());
        return "/registro/postulante.xhtml?faces-redirect=true";
    }
}
