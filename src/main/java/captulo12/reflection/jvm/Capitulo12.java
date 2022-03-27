/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captulo12.reflection.jvm;

import java.security.PrivilegedAction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 *
 * @author leone
 */
public final class Capitulo12 {

    ArrayList<Usuario> listUsers = new ArrayList();

    public Capitulo12() {

        organizeListData();

        System.out.println("\n12.1-Operador diamante melhorado");
        List<Usuario> lista = new ArrayList<>();

        System.out.println("\n12.1-Criando duas funções lambdas de exemplo");
        Supplier<LocalDate> supplierLocalDate = () -> LocalDate.now();
        System.out.println(supplierLocalDate.get());

        Supplier<String> supplier = () -> "retorna uma string1";
        System.out.println(supplier.get());

        metodo(() -> "retorna uma string, inferindo e usando o método");

        System.out.println("\n12.1-Inferindo o tipo em uma interface funcional");
        Supplier<String> supplier2 = () -> "executando um supplier";
        execute(supplier2);

        PrivilegedAction<String> action = () -> "executando uma ação";
        execute(action::run);

        System.out.println("\n12.2-Expressão Lambda");
        Runnable r = () -> {

            System.err.println("Eu sou um runnable");
        };
        new Thread(r).start();

        System.out.println("\n12.2-Expressão Lambda, inferindo a declaração");
        new Thread(() -> {
            System.err.println("Eu sou um runnable?");
        }).start();

        System.out.println("\n12.2-Expressão Lambda, Target Type tem o trabalho de inferir o tipo da expressão lambda");
        Callable<String> c = () -> "retorna uma string";
        PrivilegedAction<String> p = () -> "retorna uma string";

        System.out.println("\n12.2-Target Type trabalha também com a inferencia ao métodos de referêcnia");
        Callable<String> c2 = c::call;
        PrivilegedAction<String> p2 = p::run;

        System.out.println("\n12.3-Limitações a inferencia com o lambda");
        listUsers.sort(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome));
        showData();

        System.out.println("\n12.3-Se não usar o (Usuario u) não compila");
        listUsers.sort(Comparator.comparingInt((Usuario u) -> u.getPontos()).thenComparing(u -> u.getNome()));
        showData();

        System.out.println("\n12.3-Outra forma usando metodos referenciais");
        listUsers.sort(Comparator.comparingInt(Usuario::getPontos).reversed());
        listUsers.sort(Comparator.<Usuario, Integer>comparing(u -> u.getPontos()).reversed());
        showData();
    }

    public void metodo(Supplier<String> supplier) {

        System.err.println(supplier.get());
    }

    public void execute(Supplier<String> supplier) {

        System.err.println(supplier.get());
    }

    public void organizeListData() {

        Usuario u1 = new Usuario("Lucyene", 50);
        Usuario u2 = new Usuario("Rodrigo Turini", 60);
        Usuario u3 = new Usuario("Guilherme Silveira", 100);
        Usuario u4 = new Usuario("Amanda", 120);
        Usuario u5 = new Usuario("Agatha", 150);

        listUsers = new ArrayList();
        listUsers.add(u1);
        listUsers.add(u2);
        listUsers.add(u3);
        listUsers.add(u4);
        listUsers.add(u5);
    }

    public void showData() {

        listUsers.forEach(usuario -> {
            System.err.println(usuario);
        });
    }

    public static void main(String... args) throws Exception {

        Capitulo12 capitulo12 = new Capitulo12();
    }
}
