/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captulo12.reflection.jvm;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leone
 */
public class RegatandoONomeDosParametrosDoConstrutor {

    public RegatandoONomeDosParametrosDoConstrutor() throws NoSuchMethodException {

        Constructor<Usuario> constructor = Usuario.class.getConstructor(String.class, int.class);
        Parameter[] parameters = constructor.getParameters();
        Arrays.asList(parameters).forEach(param -> System.out.println(param.getName()));
    }

    public static void main(String[] args) {

        try {
            RegatandoONomeDosParametrosDoConstrutor regatandoONomeDosParametrosDoConstrutor = new RegatandoONomeDosParametrosDoConstrutor();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(RegatandoONomeDosParametrosDoConstrutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
