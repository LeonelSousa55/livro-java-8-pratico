/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo4.interfaces.methods;

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

        System.out.println("\n***Usando forEach com lambda***");
        ArrayList<Usuario> arrayList = new ArrayList();
        arrayList.add(u1);
        arrayList.add(u2);
        arrayList.add(u3);
        arrayList.forEach(u -> System.out.println(u.getNome()));

        /*As interfaces agora podem tem methods default, o objetivo desses méthods 
        é que eles não precisam serem implementados obrigatoriamente.*/
        System.out.println("\n***Usando o andThen da interface consumer***");
        List<Usuario> usuarios = Arrays.asList(u1, u2, u3);
        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
        usuarios.forEach(mostraMensagem.andThen(imprimeNome));

        /*Usando uma classe anonima Predicate que será usada para remover os dados da collection,
        para se usar o removeIf a lista não pode ser imutável por isso nesse caso
        será usando o ArrayList*/
        System.out.println("\n***Usando o removeIf a partir de um predicate criado***");
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
        System.out.println("\n***Removendo os dados direto sem precisar criar o predicato***");
        usuarios2.removeIf(u -> u.getPontos() > 120);
        usuarios2.forEach(u -> System.out.println(u));
    }
}
