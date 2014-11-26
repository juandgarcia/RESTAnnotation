package processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ElementKind;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import annotation.RESTAnnotation;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;

/**
 * Procesador de la anotación RESTAnnotation para hacer el llamado a un servicio
 * REST
 *
 * @author Juan David García Manrique
 */
@SupportedAnnotationTypes("annotation.RESTAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class RESTAnnotationProcessor extends AbstractProcessor {

    public RESTAnnotationProcessor() {
    }

    /**
     * Procesa la anotación, asegurándose que se anotó sobre un FIELD y que la
     * librería es JAX.RS
     *
     * @param elements
     * @param env
     * @return
     */
    public boolean process(Set<? extends TypeElement> elements, RoundEnvironment env) {
        System.out.println(">>> ENTRA AL PROCESADOR");
        for (Element element : env.getElementsAnnotatedWith(RESTAnnotation.class)) {
            //Verifica que se haya anotado sobre un FIELD
            if (element.getKind() != ElementKind.FIELD) {
                System.out.println(">>> NO ES FIELD");
                return false;
            } else {
                //Obtiene la anotación y sus parámetros
                RESTAnnotation annotation = element.getAnnotation(RESTAnnotation.class);
                String path = annotation.path();
                String library = annotation.library();
                String method = annotation.method();
                String[] keys = annotation.keys();
                String[] values = annotation.values();

                //Verifica que la librería sea jax.rs
                if (library.equals("jax.rs")) {
                    //Crea el Client de JAX.RS y asigna el target con el path introducido en la anotación
                    Client client = ClientBuilder.newClient();
                    WebTarget target = client.target(path);

                    //Agrega cada uno de los parámetros ingresados en la anotación
                    //Ej. /{param1}/{param2}
                    for (int i = 0; i < keys.length; i++) {
                        //path += "/{" + keys[i] + "}";
                        target = target.path("{" + keys[i] + "}").resolveTemplate(keys[i], values[i]);
                    }

                    System.out.println(">>> URI: " + target.getUri());

                    //Realiza el request y guarda la respuesta
                    try {
                        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
                        switch (method) {
                            case "GET":
                                String respuesta = builder.get(String.class);
                                System.out.println(">>> RESPONSE: " + respuesta);
                                break;
                            case "POST":
                                // TODO: revisar caso POST
                                break;
                        }
                    } catch (NotFoundException e) {
                        System.out.println(">>> NO EXISTE EL SERVICIO (HTTP 404)");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    client.close();
                } else {
                    System.out.println(">>> NO SELECCIONÓ JAX.RS");
                }
            }
        }
        return true;
    }
}
