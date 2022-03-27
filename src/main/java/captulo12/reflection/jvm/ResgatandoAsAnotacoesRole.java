/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captulo12.reflection.jvm;

import java.util.Arrays;

/**
 *
 * @author leone
 */
public class ResgatandoAsAnotacoesRole {

    public ResgatandoAsAnotacoesRole() {

        RelatorioController controller = new RelatorioController();
        Role[] annotationsByType = controller.getClass().getAnnotationsByType(Role.class);
        Arrays.asList(annotationsByType).forEach(a -> System.out.println(a.value()));
    }

    public static void main(String[] args) {

        new ResgatandoAsAnotacoesRole();
    }
}
