package restannotationdemo;

import annotation.RESTAnnotation;

/**
 * Método main para correr el demo de la anotación
 *
 * @author Juan David García Manrique
 */
public class Main {

    @RESTAnnotation(path = "http://localhost:8080/RESTService/webresources/MyService/myGetMethod", library = "jax.rs", method = "GET", keys = {"id"}, values = {"1"})
    private String stuff;

    public static void main(String[] args) {
        
    }
}
