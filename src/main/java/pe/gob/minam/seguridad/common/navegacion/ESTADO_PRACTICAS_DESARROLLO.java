/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author Jorge
 */
public enum ESTADO_PRACTICAS_DESARROLLO {
    
    DESARROLLO(1), FINALIZADO(2);
    private int estado;

    private ESTADO_PRACTICAS_DESARROLLO(int estado) {
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
