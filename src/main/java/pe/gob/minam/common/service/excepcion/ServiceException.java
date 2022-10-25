package pe.gob.minam.common.service.excepcion;

public class ServiceException extends Exception {
 
	private static final long serialVersionUID = 8475252968355364738L;

	public ServiceException() {
	
	}
	
	public ServiceException(String mensaje,Throwable e){
		super(mensaje,e);
	}

}
