package pe.gob.minam.seguridad.controlador.backingBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.sistema.entidades.Usuario;




@Named(value = "BKSession")
@Scope("session")
public class BKSession implements Serializable {
 
	private static final long serialVersionUID = 5236996681181402320L;
	
	private Usuario usuario= new Usuario();
	private List<Menu> listaMenu;
	private SelectItem[] listaEmpresa;
       
        
        private String user;
        private String pass;
        private int type;

	public BKSession() {
		usuario = new Usuario();
                usuario.setUsuario("");
                usuario.setContracena("");

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public SelectItem[] getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(SelectItem[] listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
 
        
    
	
}
