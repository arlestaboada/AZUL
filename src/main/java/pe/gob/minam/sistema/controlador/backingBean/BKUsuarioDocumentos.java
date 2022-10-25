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
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.UsuarioDocumentos;


/**
 *
 * @author 
 */

@Named(value = "BKUsuarioDocumentos")
@Scope("session")
public class BKUsuarioDocumentos implements Serializable {
    
    private static final long serialVersionUID = -1L;       
    
    private UsuarioDocumentos usuarioDocumentoFormulario;
    private UsuarioDocumentos usuarioDocumentoSeleccionado;    

    private List<UsuarioDocumentos> listaUsuarioDocumentos;
    private UploadedFile fileUpload;
    private StreamedContent fileDownload;

    public BKUsuarioDocumentos() {
        listaUsuarioDocumentos = new ArrayList<>();
    }

    
    
    public UsuarioDocumentos getUsuarioDocumentoFormulario() {
        return usuarioDocumentoFormulario;
    }

    public void setUsuarioDocumentoFormulario(UsuarioDocumentos usuarioDocumentoFormulario) {
        this.usuarioDocumentoFormulario = usuarioDocumentoFormulario;
    }

    public UsuarioDocumentos getUsuarioDocumentoSeleccionado() {
        return usuarioDocumentoSeleccionado;
    }

    public void setUsuarioDocumentoSeleccionado(UsuarioDocumentos usuarioDocumentoSeleccionado) {
        this.usuarioDocumentoSeleccionado = usuarioDocumentoSeleccionado;
    }

    public List<UsuarioDocumentos> getListaUsuarioDocumentos() {
        return listaUsuarioDocumentos;
    }

    public void setListaUsuarioDocumentos(List<UsuarioDocumentos> listaUsuarioDocumentos) {
        this.listaUsuarioDocumentos = listaUsuarioDocumentos;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }
    
    

    
    
    
    
}
