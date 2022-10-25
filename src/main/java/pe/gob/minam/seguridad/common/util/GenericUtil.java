/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.common.util;

/**
 *
 * @author Jorge
 */


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author INFO
 */
public class GenericUtil {

  
    public static boolean isNumero(String cadena) {//no funca chekar
        char car;//caracter
        boolean bandera=true;
        int pos=0;
        while(pos<cadena.length() && bandera){
        	car=cadena.charAt(pos);
           if (!Character.isDigit(car)){
	            bandera=false;
           }
           pos++;
        }
	return bandera;
    }// Fin del metodo isNumero()
        
    public static boolean isNumeric(String cadena){
            try {
                    Integer.parseInt(cadena);
                    return true;
            } catch (NumberFormatException nfe){
                    return false;
            }
    }    
    
    public static void copyFile(java.io.File in, java.io.File out) throws Exception {
         FileInputStream fis = new FileInputStream(in);
         FileOutputStream fos = new FileOutputStream(out);
         byte[] buf = new byte[1024];
         int i = 0;
             while((i=fis.read(buf))!=-1) {
             fos.write(buf, 0, i);
         }
         fis.close();
         fos.close();
 }
//
//    public static Connection getConexion() throws SQLException{
//
//        Connection conec = null;
//
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bdseguros","root","sistema" );
//        }
//        catch(ClassNotFoundException e){
//             System.out.println("Oracle JDBC Driver not found.");
//             System.exit(1);
//        }
//        catch(SQLException sqle){
//           System.out.println("Error de conexión: " + sqle.getMessage());
//        }
//        return  conec;
//    }
      
    
 
    public static String obtenerRutaAbsoluta(){
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();		
		String realPath = ctx.getRealPath("/");		
		return realPath;		
    }
    
      
    public StreamedContent crearReportePdfPrimeFaces( Map parametros, String nombre,String nombrearchivo) throws JRException, IOException, SQLException, ServletException {
          StreamedContent file = null;
          
        Properties properties = new Properties(); 
        String propFileName = "pe/gob/minam/chm/configuracion/database.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(inputStream);
            
        Connection conec = null;

        try{
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            conec = (Connection) DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                                                             properties.getProperty("hibernate.connection.username"),
                                                             properties.getProperty("hibernate.connection.password") );
        }
        catch(ClassNotFoundException e){
             System.out.println("Oracle JDBC Driver not found.");
             System.exit(1);
        }
        catch(SQLException sqle){
           System.out.println("Error de conexión: " + sqle.getMessage());
        }
        
        String realPath = GenericUtil.obtenerRutaAbsoluta();			
        String strRutaReportes = realPath                               
                + File.separator + "resources"
                + File.separator + "reportes"+ File.separator;
                        
         try {

           
            parametros.put("SUBREPORT_DIR",strRutaReportes);
             
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            
             String reportSource = strRutaReportes+nombre+".jrxml";

            final JasperReport report = JasperCompileManager.compileReport(reportSource);            
            
             inputStream = null;
            
            ByteArrayOutputStream Teste = new ByteArrayOutputStream();           
            JasperPrint print = JasperFillManager.fillReport(report, parametros,conec);
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();
            inputStream = new ByteArrayInputStream(Teste.toByteArray());
            
            file = new DefaultStreamedContent(inputStream, "application/pdf", nombrearchivo+".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conec != null) {

                    System.out.println("ROLLBACK EJECUTADO");
                    conec.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    
//     public StreamedContent crearReportePdfPrimeFacesExcel( Map parametros, String nombre,String nombrearchivo) throws JRException, IOException, SQLException, ServletException {
//          StreamedContent file = null;
//         try {
//
//            parametros.put("LOGO", Reportes.URL_REPORTES+Reportes.LOGO);
//            parametros.put("SUBREPORT_DIR",Reportes.URL_REPORTES);
//            
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//            
//             String reportSource = Reportes.URL_REPORTES+nombre+Reportes.EXTENSION_JRXML;
//
//            final JasperReport report = JasperCompileManager.compileReport(reportSource);            
//            
//            InputStream inputStream = null;
//            
//            ByteArrayOutputStream Teste = new ByteArrayOutputStream();           
//            JasperPrint print = JasperFillManager.fillReport(report, parametros, GenericUtil.getConexion());
//            JRExporter exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.exportReport();
//            inputStream = new ByteArrayInputStream(Teste.toByteArray());
//            
//            file = new DefaultStreamedContent(inputStream, "application/vnd.ms-excel", nombrearchivo+".xls");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (GenericUtil.getConexion() != null) {
//
//                    System.out.println("ROLLBACK EJECUTADO");
//                    GenericUtil.getConexion().close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return file;
//    }
       
//      public StreamedContent crearReportePdfPrimeFacesWord( Map parametros, String nombre,String nombrearchivo) throws JRException, IOException, SQLException, ServletException {
//          StreamedContent file = null;
//         try {
//
//            parametros.put("LOGO", Reportes.URL_REPORTES+Reportes.LOGO);
//            parametros.put("SUBREPORT_DIR",Reportes.URL_REPORTES);
//            
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//            
//             String reportSource = Reportes.URL_REPORTES+nombre+Reportes.EXTENSION_JRXML;
//
//            final JasperReport report = JasperCompileManager.compileReport(reportSource);            
//            
//            InputStream inputStream = null;
//            
//            ByteArrayOutputStream Teste = new ByteArrayOutputStream();           
//            JasperPrint print = JasperFillManager.fillReport(report, parametros, GenericUtil.getConexion());
//            JRExporter exporter = new net.sf.jasperreports.engine.export.JRRtfExporter();
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.exportReport();
//            inputStream = new ByteArrayInputStream(Teste.toByteArray());
//            
//            file = new DefaultStreamedContent(inputStream, "application/msword;charset=utf-8", nombrearchivo+".doc");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (GenericUtil.getConexion() != null) {
//
//                    System.out.println("ROLLBACK EJECUTADO");
//                    GenericUtil.getConexion().close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return file;
//    }
//      
//    public static void crearReportePdf(Collection coleccion, Map parametros, String nombre) throws JRException, IOException {
//        System.out.println(" ** JASPER PDF");
//
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//
//        String reportTemplatesPath = request.getSession().getServletContext().getInitParameter("reportTemplatesPath");
//        String reportTemplatesExtension = request.getSession().getServletContext().getInitParameter("reportTemplatesExtension");
//        String reportTemplatePath =  reportTemplatesPath + nombre + reportTemplatesExtension;
//
//        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(coleccion.toArray());
//        InputStream reportIS = request.getSession().getServletContext().getResourceAsStream(reportTemplatePath);
//        if(reportIS == null){
//            System.out.println(reportTemplatePath+"no existe");
//        }
//
//       
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reportIS, parametros, dataSource);
//        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
//
//        response.setHeader("Content-disposition","attachment;filename="+nombre+".pdf");
//        response.setContentType("application/pdf");
//        response.setContentLength(bytes.length);
//        response.getOutputStream().write(bytes);
//        facesContext.responseComplete();
//    }

   
    
}
