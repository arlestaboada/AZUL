package pe.gob.minam.seguridad.controlador.helper;

import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.common.util.Constantes;
import pe.gob.minam.seguridad.common.util.ConstantesWeb;



public class MenuBarHelper {

    private static Logger logger  = Logger.getLogger(MenuBarHelper.class);
    
    public MenuBarHelper() {
    }
    
    public static Menubar getMenuBar(List<Menu> listaMenu) throws Exception{
        Menubar menuBar =  new Menubar();
        
        List<UIMenuItem> listaUIMenuItemPlantilla = cargarMenuPlantilla();
        menuBar.getChildren().add(listaUIMenuItemPlantilla.get(0));
        menuBar.setAutoDisplay(false);
        for (Menu menu : listaMenu) {
            UISubmenu subMenu = null;
            if(menu.getIdMenuPadre()==null){
                subMenu = new UISubmenu();
                subMenu.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM + menu.getId());
                subMenu.setLabel(menu.getNombre().toUpperCase());
                subMenu.setIcon("ui-icon ui-icon-newwin");
                
                menuBar.getChildren().add(subMenu);
                cargarHijos(menu,listaMenu,subMenu);
            }
            
        }
        
        menuBar.getChildren().add(listaUIMenuItemPlantilla.get(1));
        menuBar.getChildren().add(listaUIMenuItemPlantilla.get(2));
        return menuBar;
    }

    private static void cargarHijos(Menu menuPadre,List<Menu> listaHijos,UISubmenu subMenu)throws Exception{
        FacesContext facesCtx = FacesContext.getCurrentInstance();
	ELContext elCtx = facesCtx.getELContext();
	ExpressionFactory expFact = facesCtx.getApplication().getExpressionFactory();
        UISubmenu subMenuHijo = null;
        UIMenuItem menuItem = null;
        for (Menu menuHijo : listaHijos) {
             if(menuHijo.getIdMenuPadre()!=null){
                if(menuPadre.getId() == menuHijo.getIdMenuPadre().getId()){
                    if(verificarHijos(menuHijo, listaHijos)){
                        subMenuHijo =  new UISubmenu();
			subMenuHijo.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM+ menuHijo.getId());
			subMenuHijo.setLabel(menuHijo.getNombre());
                        //subMenuHijo.setStyle("color:#E17009 !important;");
			subMenu.getChildren().add(subMenuHijo);
                        cargarHijos(menuHijo,listaHijos,subMenuHijo);
                    }else{
                        menuItem = new UIMenuItem();
			menuItem.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM + menuHijo.getId());
			menuItem.setValue(menuHijo.getNombre());
                       // menuItem.setStyle("color:#336699");
                        menuItem.setAjax(false);
//                        menuItem.setUpdate("divPnlContenido");
                        
                       
                        
                        MethodExpression aEx = expFact.createMethodExpression(elCtx,menuHijo.getAccion(), String.class, new Class[0]);
                        
                        
                        
                        menuItem.setActionExpression(aEx);
			subMenu.getChildren().add(menuItem);
                }
                }
             } 
        }
    }

    private static boolean verificarHijos(Menu padre, List<Menu> listaMenu){
        for (Menu menu : listaMenu) {
            if(menu.getIdMenuPadre()!=null){
                if(menu.getIdMenuPadre().getId()==padre.getId()){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static List<UIMenuItem> cargarMenuPlantilla(){
        List<UIMenuItem> listaMenuPlantilla = new ArrayList<UIMenuItem>();
        UIMenuItem menuItem;
        menuItem = new UIMenuItem();
        menuItem.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM + Constantes.Menu.VARIABLE_ID_MENU_ITEM_INICIO);
	menuItem.setValue("INICIO");
	menuItem.setIcon("ui-icon ui-icon-home");
        menuItem.setAjax(true);
//        menuItem.setUpdate("divPnlContenido");
        menuItem.setUrl(URLPaginacion.Seguridad.URL_HOME);
	listaMenuPlantilla.add(menuItem);	
        menuItem = new UIMenuItem();
        menuItem.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM + Constantes.Menu.VARIABLE_ID_MENU_ITEM_AYUDA);
	menuItem.setValue("AYUDA");
        menuItem.setAjax(true);
//        menuItem.setUpdate("divPnlContenido");
        menuItem.setUrl(URLPaginacion.Seguridad.URL_PAGINA_AYUDA);
	menuItem.setIcon("ui-icon ui-icon-help");
        listaMenuPlantilla.add(menuItem);	
        menuItem = new UIMenuItem();
        menuItem.setId(Constantes.Menu.VARIABLE_ID_MENU_ITEM + Constantes.Menu.VARIABLE_ID_MENU_ITEM_SALIR);
	menuItem.setValue("SALIR");
	menuItem.setIcon("ui-icon ui-icon-close");
        menuItem.setUrl(URLPaginacion.Seguridad.URL_PAGINA_LOGOUT);
        listaMenuPlantilla.add(menuItem);
        return listaMenuPlantilla;
    }
    
    
   
}
