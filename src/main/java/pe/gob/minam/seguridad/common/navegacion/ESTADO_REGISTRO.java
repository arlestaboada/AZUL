package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author zer0
 */
public enum ESTADO_REGISTRO {

    ACTIVO('A'), INACTIVO('I');
    private char estado;

    private ESTADO_REGISTRO(char estado) {
        this.estado = estado;
    }

    public char getValor() {
        return this.estado;
    }

    @Override
    public String toString() {
        return String.valueOf(estado);
    }
}
