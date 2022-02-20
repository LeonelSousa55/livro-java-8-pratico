/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo6.method.references;

import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

/**
 *
 * @author Leonel
 */
public final class Capitulo6 {

    ArrayList<Usuario> listUsers = new ArrayList();

    public Capitulo6() {

        organizeListData();

        System.err.println("\n6.1-Tornando os users moderadores com forEach");
        listUsers.forEach(u -> u.tornaModerador());
        showData();

        System.err.println("\n6.1-Usando um método por referencia para tornar moderadores");
        listUsers.forEach(Usuario::deixarDeSerModerador);
        showData();

        System.err.println("\n6.1-Criando um Consumer passo a passo");
        Consumer<Usuario> tornarModerador = Usuario::tornaModerador;
        listUsers.forEach(tornarModerador);
        showData();

        System.err.println("\n6.2-Ordenação com lambda usando o comparing");
        listUsers.sort(Comparator.comparing(u -> u.getNome()));
        showData();

        System.err.println("\n6.2-Ordenação com referencia usando o comparing");
        listUsers.sort(Comparator.comparing(Usuario::getNome));
        showData();

        System.err.println("\n6.2-Criando uma Function para ficar mais legivel");
        Function<Usuario, String> byNome = Usuario::getNome;
        listUsers.sort(comparing(byNome));
        showData();

        System.err.println(""
                + "\n6.3-Ordenação por pontos como já vimos usando o "
                + "comparingInt para eviter o Boxing");
        listUsers.sort(Comparator.comparingInt(u -> u.getPontos()));
        showData();

        System.err.println("\n6.3-Usando o método por referencia.");
        listUsers.sort(Comparator.comparingInt(Usuario::getPontos));
        showData();

        System.err.println("\n6.3-Ordenação com mais de uma opção caso dê empate");
        Comparator<Usuario> comparator = Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome);
        listUsers.sort(comparator);
        showData();

        System.err.println("\n6.3-Passando as duas comparações direto no sort");
        listUsers.sort(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome));
        showData();

        System.err.println(""
                + "\n6.3-Colocando os usuários nulls para o final da lista e "
                + "ordenando os outros pelo nome");
        listUsers.add(null);
        listUsers.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));
        showData();
        listUsers.remove(3);

        System.err.println(""
                + "\n6.3-Ordenar por pontos de forma inversa");
        listUsers.sort(Comparator.comparing(Usuario::getPontos).reversed());
        showData();

        System.err.println("\n6.4-Referenciando um metodo do usuario e invokando ele");
        Usuario rodrigo = new Usuario("Rodrigo Turini", 50);
        Runnable bloco = rodrigo::tornaModerador;
        bloco.run();
        System.out.println(rodrigo);

        System.out.println("\n6.4-Esses dois metodos são equivalente");
        Usuario rodrigo2 = new Usuario("Rodrigo Turini", 50);
        Runnable bloco1 = rodrigo2::tornaModerador;
        Runnable bloco2 = () -> rodrigo2.tornaModerador();
        bloco1.run();
        bloco2.run();
        System.out.println(rodrigo2);

        System.out.println("\n6.4-Chamar o consumer tornarModerador pelo accept");
        Usuario rodrigo3 = new Usuario("Rodrigo Turini", 50);
        Consumer<Usuario> consumer = Usuario::tornaModerador;
        consumer.accept(rodrigo3);
        System.out.println(rodrigo3);

        System.out.println("\n6.5-Referenciando metodos que recebem argumentos");
        listUsers.forEach(System.out::println);

        System.out.println(""
                + "\n6.6-Referenciando construtores, criando um Supplier que guarda "
                + "a referência da instância e quando se usa o .get nós teremos "
                + "um novo usuario");
        Supplier<Usuario> criadorDeUsuarios = Usuario::new;
        Usuario user = criadorDeUsuarios.get();
        System.out.println(user);

        System.out.println("\n6.6-Criando um novo usuario a partir do Apply com apenas um parametro");
        Function<String, Usuario> criadorDeUsuario2 = Usuario::new;
        Usuario rodrigo4 = criadorDeUsuario2.apply("Rodrigo");
        Usuario paulo = criadorDeUsuario2.apply("Paulo");
        System.out.println(rodrigo4);
        System.out.println(paulo);

        System.out.println("\n6.6-Criando um novo usuario a partir do BiFunction com mais de um parametro");
        BiFunction<String, Integer, Usuario> criadorDeUsuario3 = Usuario::new;
        Usuario rodrigo5 = criadorDeUsuario3.apply("Rodrigo", 50);
        Usuario paulo2 = criadorDeUsuario3.apply("Paulo", 30);
        System.out.println(rodrigo5);
        System.out.println(paulo2);
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
        new Capitulo6();
    }
}
