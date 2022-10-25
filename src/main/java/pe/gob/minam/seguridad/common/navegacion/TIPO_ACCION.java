/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author Jorge
 */


public enum TIPO_ACCION {
    SINACCION(0), 
    GUARDAR(1), 
    ACTUALIZAR(2);

    private int accion;

    private TIPO_ACCION(int accion) {
        this.accion = accion;
    }

    public int getValor() {
        return this.accion;
    }

    @Override
    public String toString() {
        return String.valueOf(accion);
    } 
}
