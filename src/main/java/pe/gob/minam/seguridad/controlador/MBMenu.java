package pe.gob.minam.seguridad.controlador;

import java.io.Serializable;
import javax.inject.Named;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.MBGenerico;
 
@Named(value = "MBMenu")
@Scope("session")
public class MBMenu extends MBGenerico implements Serializable {

    private static Logger logger = Logger.getLogger(MBMenu.class);
    
    private transient Menubar principalMenuBar;

    private void cargarMenuBar() {
        try {
            //	this.principalMenuBar = MenuBarHelper.getMenuBar(menuBO.obtenerListaOpcionesMenu(sessionMBean.getPerfilDto().getId().longValue()));
//            this.principalMenuBar = MenuBarHelper.getMenuBarHardCode();
//            this.principalMenuBar = MenuBarHelper.getMenuBar();
        } catch (Exception e) {
            String msg = "no se pudo crear el componente menubar";
            mostrarError(msg);
            logger.error(e.getMessage(),e);
        }
    }

    public Menubar getPrincipalMenuBar() {
        cargarMenuBar();
        return principalMenuBar;
    }

    public void setPrincipalMenuBar(Menubar principalMenuBar) {
        this.principalMenuBar = principalMenuBar;
    }
}
