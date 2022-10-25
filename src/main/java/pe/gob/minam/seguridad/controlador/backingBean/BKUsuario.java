package pe.gob.minam.seguridad.controlador.backingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.gob.minam.sistema.dto.DtoUbigeo;
import pe.gob.minam.sistema.entidades.Instituciones;
import pe.gob.minam.sistema.entidades.Usuario;


@Named("BKUsuario")
@Scope("session")
public class BKUsuario implements Serializable{
    
    private Usuario usuarioBusqueda;
    private Usuario usuarioFormulario;
    private Usuario usuarioSeleccionado;
    private List<Usuario> listaUsuarios;
    private List<Instituciones> listaInstituciones;
    
    
    private List<DtoUbigeo> listaDepartamentos;
    private List<DtoUbigeo> listaProvincias;
    private List<DtoUbigeo> listaDistritos;
    
    private int rol;
    private int institucion;
    
    private SelectItem[] listaRol;
    
    private String confirmarContracena;

    public BKUsuario() {
        usuarioBusqueda = new Usuario();
        listaInstituciones = new ArrayList<>();
    }
    

    /**
     * @return the usuarioFormulario
     */
    public Usuario getUsuarioFormulario() {
        return usuarioFormulario;
    }

    /**
     * @param usuarioFormulario the usuarioFormulario to set
     */
    public void setUsuarioFormulario(Usuario usuarioFormulario) {
        this.usuarioFormulario = usuarioFormulario;
    }

    /**
     * @return the usuarioSeleccionado
     */
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the usuarioBusqueda
     */
    public Usuario getUsuarioBusqueda() {
        return usuarioBusqueda;
    }

    /**
     * @param usuarioBusqueda the usuarioBusqueda to set
     */
    public void setUsuarioBusqueda(Usuario usuarioBusqueda) {
        this.usuarioBusqueda = usuarioBusqueda;
    }

    /**
     * @return the confirmarContracena
     */
    public String getConfirmarContracena() {
        return confirmarContracena;
    }

    /**
     * @param confirmarContracena the confirmarContracena to set
     */
    public void setConfirmarContracena(String confirmarContracena) {
        this.confirmarContracena = confirmarContracena;
    }

    /**
     * @return the listaRol
     */
    public SelectItem[] getListaRol() {
        return listaRol;
    }

    /**
     * @param listaRol the listaRol to set
     */
    public void setListaRol(SelectItem[] listaRol) {
        this.listaRol = listaRol;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public List<Instituciones> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<Instituciones> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public int getInstitucion() {
        return institucion;
    }

    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    public List<DtoUbigeo> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<DtoUbigeo> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<DtoUbigeo> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<DtoUbigeo> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<DtoUbigeo> getListaDistritos() {
        return listaDistritos;
    }

    public void setListaDistritos(List<DtoUbigeo> listaDistritos) {
        this.listaDistritos = listaDistritos;
    }
 
    
    
    
}
