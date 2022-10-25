package pe.gob.minam.common.controlador;

import javax.faces.event.ActionEvent;

/**
 *
 * @author zer0
 */
public interface IMantenedor {
    
    public String actualizar();
    public String guardar();
    public void eliminar(ActionEvent actionEvent);
    public String editar();
    public String nuevo();
    public String ver();
    public String aceptar();
    public String retroceder();
    public String cancelar();
    public String mostrar();
    
}
