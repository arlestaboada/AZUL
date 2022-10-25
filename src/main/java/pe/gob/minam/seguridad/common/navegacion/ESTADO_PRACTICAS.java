/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author Jorge
 */
public enum ESTADO_PRACTICAS {
    
    REGISTRADO(1), ENVIADO(2);
    private int estado;

    private ESTADO_PRACTICAS(int estado) {
        this.estado = estado;
    }

    public int getValor() {
        return this.estado;
    }

    @Override
    public String toString() {
        return String.valueOf(estado);
    }
}
