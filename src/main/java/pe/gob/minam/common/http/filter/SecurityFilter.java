/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.common.http.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author jmarinc
 */
public class SecurityFilter implements Filter{

  
    FilterConfig filterConfig2;
    private String paginaSesionExpirada = "/sessionExpirada.xhtml";
    private String paginaInicio = "/login.xhtml";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       filterConfig2=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        
        HttpSession httpSession = httpServletRequest.getSession(false);
        
        
         if(httpSession != null){
	    	if(httpSession.getAttribute("USER_LOGGED") != null){
	    		System.out.println("Esta logueado");
	    	}else{
	    		System.out.println("No esta logueado");
	    	}
	    }else{
	    	System.out.println("La session es nula");
	    }
    
	    String requestedSessionId = httpServletRequest.getRequestedSessionId();
	    System.out.println("requestedSessionId: " + requestedSessionId);

	    if(httpSession != null){
	    	if(httpSession.getAttribute("USER_LOGGED") != null){
	    		System.out.println("Esta logueado");
	    	}else{
	    		System.out.println("No esta logueado");
	    	}
	    }else{
	    	System.out.println("La session es nula");
	    }	  
	    System.out.println(httpServletRequest.getRequestURI());
	    
	    if (esSesionInvalida(httpServletRequest)) {
			if (!verificaRecursoNecesitaSesionActiva(httpServletRequest)) {
				Validate.isTrue(response instanceof HttpServletResponse);	           
	            handleSessionExpired(httpServletRequest, httpServletResponse);	
	            return;
			}
            }
	    	    	 
	    chain.doFilter(request, response);	
         
    }

    @Override
    public void destroy() {
      
    }
    
    
    private void handleSessionExpired( HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) 
        throws IOException, ServletException {	
        System.out.println("expired session | id: " + httpServletRequest.getRequestedSessionId());
        sendSessionExpiredResponse(httpServletRequest, httpServletResponse);
    }  

    private void sendSessionExpiredResponse(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) 
            throws IOException, ServletException {

        httpServletRequest.getSession(true); // force valid session to exist
        httpServletRequest.setAttribute("isExpired", true);      
        RequestDispatcher rd = filterConfig2.getServletContext().getRequestDispatcher(getPaginaSesionExpirada());
        rd.forward(httpServletRequest, httpServletResponse);
    }

    private boolean verificaRecursoNecesitaSesionActiva(
                    HttpServletRequest httpServletRequest) {

            String requestPath = httpServletRequest.getRequestURI();		
            boolean controlRequired = contains(requestPath,
                            getPaginaSesionExpirada())
                            || contains(requestPath, paginaInicio);	

            return controlRequired;
    }

    private boolean esSesionInvalida(HttpServletRequest httpServletRequest) {

            boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
                            && !httpServletRequest.isRequestedSessionIdValid();
            return sessionInValid;
    }

    public String getPaginaSesionExpirada() {
            return paginaSesionExpirada;
    }

    public void setPaginaSesionExpirada(String paginaSesionExpirada) {
            this.paginaSesionExpirada = paginaSesionExpirada;
    }

    private boolean contains(String strCadena1, String strCadena2) {
            boolean blnResultado = true;
            blnResultado = !(strCadena1 == null || strCadena2 == null);
            if (blnResultado) {
                    blnResultado = strCadena1.contains(strCadena2);
            }
            return blnResultado;
    }
    
}
