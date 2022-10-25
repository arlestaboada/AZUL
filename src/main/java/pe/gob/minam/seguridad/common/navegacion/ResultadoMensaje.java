/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author 
 */
public class ResultadoMensaje {
    
    public static final String MENSAJE_ERROR = "2";
    public static final String MENSAJE_OK = "1";
    
    private String idMensaje;
    private String descMensaje;
    private String tipoMensaje;

    public ResultadoMensaje(){
        idMensaje = "";
        descMensaje = "";
        tipoMensaje = "";
    }
    /**
     * @return the idMensaje
     */
    public String getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje the idMensaje to set
     */
    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return the descMensaje
     */
    public String getDescMensaje() {
        return descMensaje;
    }

    /**
     * @param descMensaje the descMensaje to set
     */
    public void setDescMensaje(String descMensaje) {
        this.descMensaje = descMensaje;
    }

    /**
     * @return the tipoMensaje
     */
    public String getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * @param tipoMensaje the tipoMensaje to set
     */
    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }
    
    
    
}
