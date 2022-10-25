/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author Jorge
 */
public enum POSTULANTE_CATEGORIA {
    
    A(1), B(2), C(3);
    private int categoria;

    private POSTULANTE_CATEGORIA(int categoria) {
        this.categoria = categoria;
    }

    public int getValor() {
        return this.categoria;
    }

    @Override
    public String toString() {
        return String.valueOf(categoria);
    }
}
