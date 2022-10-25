/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.controlador.backingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.sistema.dto.DtoUbigeo;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;

/**
 *
 * @author Jorge
 */

@Named(value = "BKPostulante")
@Scope("session")
public class BKPostulante implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    private Postulante postulante;
    private PostulanteDocumentos postulanteDocumentos;
    private PostulanteDocumentosFiles postulanteDocumentosFiles;
    private List<PostulanteDocumentos> listaAdjuntos;
    private List<PostulanteDocumentos> listaDocumentos;
    private List<Postulante> listaPostulante;
    private  List<Postulante> listaPostulantes;
    private List<DtoUbigeo> listaDepartamentos;
    private List<DtoUbigeo> listaProvincias;
    private List<DtoUbigeo> listaDistritos;
    
     private int operacion;   

    public BKPostulante() {
        postulante = new Postulante();  
        postulanteDocumentos = new PostulanteDocumentos();
        postulanteDocumentosFiles = new PostulanteDocumentosFiles();
        listaDocumentos = new ArrayList<>();
        listaAdjuntos =  new ArrayList<>();
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public PostulanteDocumentos getPostulanteDocumentos() {
        return postulanteDocumentos;
    }

    public void setPostulanteDocumentos(PostulanteDocumentos postulanteDocumentos) {
        this.postulanteDocumentos = postulanteDocumentos;
    }

    public PostulanteDocumentosFiles getPostulanteDocumentosFiles() {
        return postulanteDocumentosFiles;
    }

    public void setPostulanteDocumentosFiles(PostulanteDocumentosFiles postulanteDocumentosFiles) {
        this.postulanteDocumentosFiles = postulanteDocumentosFiles;
    }

    public List<PostulanteDocumentos> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<PostulanteDocumentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<Postulante> getListaPostulante() {
        return listaPostulante;
    }

    public void setListaPostulante(List<Postulante> listaPostulante) {
        this.listaPostulante = listaPostulante;
    }
    
    public List<PostulanteDocumentos> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<PostulanteDocumentos> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }
    
     public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
   

    public int getNuevoOperacion() {
        return Constantes.Operacion.NUEVO;
    }

    public int getEditarOperacion() {
        return Constantes.Operacion.EDITAR;
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

    public List<Postulante> getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(List<Postulante> listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }

    
    
}
