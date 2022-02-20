/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo5.ordenacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 *
 * @author Leonel
 */
public final class Capitulo5 {
    
    ArrayList<Usuario> listUsers = new ArrayList();
    
    public Capitulo5() {
        
        organizeListData();
        
        System.err.println("\n1-Ordenando usando comparator recém criado");
        Comparator<Usuario> comparator = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNome().compareTo(u2.getNome());
            }
        };
        listUsers.sort(comparator);
        showData();
        
        System.err.println("\n2-Ordenando usando Colletions.sort/comparator");
        Collections.sort(listUsers, comparator);
        showData();
        
        System.err.println("\n3-Ordenando usando um novo comparator");
        Collections.sort(listUsers, (u1, u2) -> u2.getNome().compareTo(u1.getNome()));
        showData();
        
        System.err.println("\n4-Ordenando de forma mais sucinta");
        listUsers.sort((u1, u2) -> u2.getNome().compareTo(u1.getNome()));
        showData();
        
        System.err.println(""
                + "\n5-Methods statics dentro das interfaces, a interface "
                + "comparador já tem alguns desses methodos exemplo: "
                + "O nosso listUsers pode ficar ainda mais curto.");
        Comparator<Usuario> comparator2 = Comparator.comparing(u -> u.getNome());
        listUsers.sort(comparator2);
        showData();
        
        System.err.println("\n5.1-Sem precisar criar a variável comparator");
        listUsers.sort(Comparator.comparing(u -> u.getNome()));
        showData();
        
        System.err.println("\n5.2-Importando a classe Comparador");
        listUsers.sort(comparing(u -> u.getNome()));
        showData();
        
        System.err.println("\n5.3-Criando um passo a passo do comparing.");
        Function<Usuario, Integer> extraiNome = u -> u.getPontos();
        Comparator<Usuario> comparator3 = Comparator.comparing(extraiNome);
        listUsers.sort(comparator3);
        showData();
        
        System.err.println("\n5.4-Indexando pela ordem natual");
        List<String> palavras = Arrays.asList("Casa do código", "Alura", "Caelum");
        palavras.sort(Comparator.naturalOrder());
        palavras.forEach(palavra -> {
            System.err.println(palavra);
        });
        
        System.err.println("\n5.5-Indexando pela ordem inversa");
        palavras = Arrays.asList("Casa do código", "Alura", "Caelum");
        palavras.sort(Comparator.reverseOrder());
        palavras.forEach(palavra -> {
            System.err.println(palavra);
        });
        
        System.err.println("\n5.6-Ordenando por pontos");
        listUsers.sort(comparing(u -> u.getPontos()));
        showData();
        
        System.err.println(""
                + "\n5.7-Usando o ToIntFunction para evitar o autoBoxing e também "
                + "o unboxing desnecessaris devido as conversões de int para Interger.");
        ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
        Comparator<Usuario> comparator4 = Comparator.comparingInt(extraiPontos);
        listUsers.sort(comparator4);
        showData();
        
        System.err.println("\n5.8- Usando o ComparingInt com apenas um linha");
        listUsers.sort(Comparator.comparingInt(u -> u.getPontos()));
        showData();
    }
    
    public void organizeListData() {
        
        Usuario u1 = new Usuario("Paulo Silveira", 150);
        Usuario u2 = new Usuario("Rodrigo Turini", 120);
        Usuario u3 = new Usuario("Guilherme Silveira", 190);
        
        listUsers = new ArrayList();
        listUsers.add(u1);
        listUsers.add(u2);
        listUsers.add(u3);
    }
    
    public void showData() {
        
        listUsers.forEach(usuario -> {
            System.err.println(usuario);
        });
    }
    
    public static void main(String[] args) {
        new Capitulo5();
    }
}
