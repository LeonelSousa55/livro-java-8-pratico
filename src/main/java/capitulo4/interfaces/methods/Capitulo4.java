/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo4.interfaces.methods;

import capitulo2.lambda.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author leone
 */
public class Capitulo4 {

    public static void main(String... args) {

        Usuario u1 = new Usuario("Paulo Silveira", 150);
        Usuario u2 = new Usuario("Rodrigo Turini", 120);
        Usuario u3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

        /*As interfaces agora podem tem methods default, o objetivo desses méthods 
        é que eles não precisam serem implementados obrigatoriamente.*/
        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());//////////
        usuarios.forEach(mostraMensagem.andThen(imprimeNome));

        /*Usando uma classe anonima Predicate que será usada para remover os dados da collection*/
        List<Usuario> usuarios2 = new ArrayList<>();
        usuarios2.addAll(usuarios);
        Predicate<Usuario> predicado = new Predicate<Usuario>() {
            public boolean test(Usuario u) {

                return u.getPontos() > 160;
            }
        };
        usuarios2.removeIf(predicado);
        usuarios2.forEach(u -> System.out.println(u));

        /*Removendo os dados da colection sem ser preciso criar a class Predicate apenas com lambd*/
        usuarios2.removeIf(u -> u.getPontos() > 160);
    }
}
