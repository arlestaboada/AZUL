package pe.gob.minam.common.dao.excepcion;

public class DAOException extends Exception {

    private static final long serialVersionUID = -4604033844963699334L;
    
    public DAOException() {
	}
    
    public DAOException(String mensaje,Throwable ex){
    	super(mensaje, ex);
    }
    
}
