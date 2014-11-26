package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Anotación para hacer el llamado a un servicio REST
 *
 * @author Juan David García Manrique
 */
@Target(ElementType.FIELD)
public @interface RESTAnnotation {

    public String path() default "/MyService";

    public String library() default "jax.rs";
    
    public String method() default "GET";
    
    public String[] keys();
    
    public String[] values();
}
