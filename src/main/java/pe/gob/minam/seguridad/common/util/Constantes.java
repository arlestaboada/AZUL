package pe.gob.minam.seguridad.common.util;
 
public class Constantes {
    
  
    
    public static class Menu{
    
        public static final String VARIABLE_ID_MENU_ITEM = "idMenu";
        public static final String VARIABLE_ID_MENU_ITEM_INICIO = "Inicio";
        public static final String VARIABLE_ID_MENU_ITEM_AYUDA = "Ayuda";
        public static final String VARIABLE_ID_MENU_ITEM_SALIR = "Salir";
        public static final String VARIABLE_ = "";
        
    }
    
     public static class TipoImagen{
    
        public static final int FOTO_POSTULANTE = 1;
        public static final int FOTO_PRACTICA = 2;
        public static final int DOCUMENTO = 3;                
    }
     
     public static class TipoUsuario{
         
        public static final int POSTULANTE = 1;
        public static final int INTERNO = 2;                
    }
    
     public static class Operacion{
    
        public static final int NUEVO = 1;
        public static final int EDITAR = 2;
        public static final int BAJA = 3;
        public static final int ELIMINAR = 4;
        
    }
     
      public static class HABILITADO{
        public static final char ACTIVO = 'A';
        public static final char NOACTIVO = 'D';
    }  
    
}
