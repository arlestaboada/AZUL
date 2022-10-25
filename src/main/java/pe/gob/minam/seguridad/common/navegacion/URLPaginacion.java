package pe.gob.minam.seguridad.common.navegacion;

public class URLPaginacion {

    public static class Seguridad {

        public static final String URL_SAME = null;
        public static final String URL_HOME = "/principal.xhtml";
        public static final String URL_LOGIN = "/login";
        public static final String URL_PASARELA = "/pasarela";
        public static final String URL_PAGINA_ERROR = "/errorPagina";
        public static final String URL_PAGINA_AYUDA = "/ayuda.xhtml";
        public static final String URL_PAGINA_LOGOUT = "/j_spring_security_logout";
    }

    public static class Usuario {

        public static final String URL_LISTA_USUARIO = "/paginas/seguridad/usuario/listarUsuario";
        public static final String URL_CREAR_USUARIO = "/paginas/seguridad/usuario/crearUsuario";
        public static final String URL_EDITAR_USUARIO = "/paginas/seguridad/usuario/editarUsuario";
        public static final String URL_EDITAR_USUARIO_CONTRACENA = "/paginas/seguridad/usuario/editarUsuarioClave";
    }

    public static class Intituciones {

        public static final String URL_LISTA_Instituciones = "/paginas/instituciones/Listarinstituciones.xhtml";
    }

    public static class Roles {

        public static final String URL_LISTA_ROL = "/paginas/seguridad/roles/listarRol";
        public static final String URL_CREAR_ROL = "/paginas/seguridad/roles/crearRol";
        public static final String URL_EDITAR_ROL = "/paginas/seguridad/roles/editarRol";
    }

    public static class Opciones {

        public static final String URL_LISTA_OPCIONES = "/paginas/seguridad/opciones/listarOpciones";
        public static final String URL_EDITAR_OPCIONES = "/paginas/seguridad/opciones/editarOpciones";
    }

    public static class Postulante {

        public static final String URL_FICHA_INSCRIPCION = "/paginas/postulante/ficha_inscripcion.xhtml";
        public static final String URL_BUENA_PRACTICA_LISTA = "/paginas/postulante/lista_practica.xhtml";
        public static final String URL_BUENA_PRACTICA_NUEVO = "/paginas/postulante/nueva_practica.xhtml";
        public static final String URL_BUENA_PRACTICA_EDITA = "/paginas/postulante/edita_practica.xhtml";
        public static final String URL_FICHA_INSCRIPCION_OK = "/registro/postulanteOk.xhtml";
        public static final String URL_BUENA_PRACTICA_LISTA_Exito = "/paginas/postulante/Agregar_Practica_exito.xhtml";
         public static final String URL_POSTULANTES= "/paginas/Reportes/Postulantes.xhtml";
    }

    public static class Evaluador {

        public static final String URL_BUENA_PRACTICA_LISTA = "/paginas/evaluador/listar_practicas.xhtml";
        public static final String URL_BUENA_PRACTICA_EVALUAR = "/paginas/evaluador/evaluar_practica.xhtml";
        public static final String URL_practicas_a_evaluar = "/paginas/evaluador/Practicas_a_Evaluar.xhtml";
        public static String URL_GUARDO_PRACTICA_Evaluada="/paginas/evaluador/Guardar_Practica_Evaluada.xhtml";
     
    }

    public static class Maestros {

        public static final String URL_INDICADORES_LISTA = "/paginas/maestros/indicadores.xhtml";
        public static final String URL_CRITERIOS_LISTA = "/paginas/maestros/criterios.xhtml";
    }

    public static class Reportes {

       
        public static final String URL_REPORTES2 = "/paginas/reportes/reporte2";
        public static final String URL_REPORTES3 = "/paginas/reportes/reporte3";
       
    }

    public static class Correos {

        public static final String URL_LISTAR_CORREOS = "/paginas/utilitario/correos/listarCorreos";
        public static final String URL_CREAR_CORREOS = "/paginas/utilitario/correos/crearCorreos";
        public static final String URL_EDITAR_CORREOS = "/paginas/utilitario/correos/editarCorreos";
    }
}
