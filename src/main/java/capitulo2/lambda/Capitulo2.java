/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo2.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author leone
 */
public class Capitulo2 {

    public Capitulo2() {

        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        System.err.println("\n1-Listando os dados normais");
        for (Usuario usuario : usuarios) {

            System.err.println(usuario.getNome());
        }

        System.err.println("\n2-Comsumidor para o forEach");
        Mostrador mostrador = new Mostrador();
        usuarios.forEach(mostrador);

        System.err.println("\n3-Sem precisar criar a classe anonima");
        Consumer<Usuario> mostrador2 = new Consumer<Usuario>() {
            @Override
            public void accept(Usuario u) {
                System.err.println(u.getNome());
            }
        };
        usuarios.forEach(mostrador2);

        System.err.println("\n4-Sem precisar criar a variável mostrador");
        usuarios.forEach(new Consumer<Usuario>() {
            @Override
            public void accept(Usuario u) {
                System.err.println(u.getNome());
            }
        });

        System.err.println("\n5-trabalhando com Lambda, o accept é inferido pelo compilador");
        Consumer<Usuario> mostrador3 = (Usuario u) -> {
            System.err.println(u.getNome());
        };
        usuarios.forEach(mostrador3);

        System.err.println("\n6-trabalhando com Lambda, Inferindo também o tipo");
        Consumer<Usuario> mostrador4 = u -> {
            System.err.println(u.getNome());
        };
        usuarios.forEach(mostrador4);

        System.err.println("\n7-trabalhando com Lambda, se estiver apenas uma instrução podemos omitir também as {}");
        Consumer<Usuario> mostrador5 = u -> System.err.println(u.getNome());
        usuarios.forEach(mostrador5);

        System.err.println("\n8-trabalhando com Lambda, sem precisar criar a variável mostrador");
        usuarios.forEach(u -> System.err.println(u.getNome()));

        System.err.println("\n8-trabalhando com Lambda, tornando todos moderadores");
        usuarios.forEach(u -> u.tornaModerador());
        usuarios.forEach(u -> System.err.println(u.toString()));
    }

    //*************************************2************************************
    class Mostrador implements Consumer<Usuario> {

        @Override
        public void accept(Usuario u) {
            System.err.println(u.getNome());
        }
    }

    public static void main(String[] args) {

        new Capitulo2();
    }
}
