/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7.streams.collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Leonel
 */
public final class Capitulo7 {

    ArrayList<Usuario> listUsers = new ArrayList();

    public Capitulo7() {

        organizeListData();

        System.out.println("\n7.1-Tornando 3 moderadore os usuário com mais pontos");
        listUsers.sort(Comparator.comparing(Usuario::getPontos).reversed());
        listUsers.subList(0, 3).forEach(Usuario::tornaModerador);
        showData();

        System.out.println("\n7.2-Trabalhando com Stream");
        Stream<Usuario> stream = listUsers.stream().filter(u -> u.getPontos() > 100);
        stream.forEach(System.out::println);

        System.out.println("\n7.2-Removendo a variável temp");
        listUsers.stream().filter(u -> u.getPontos() < 100).forEach(System.out::println);

        System.out.println("\n7.2-Com Stream não existe efeito colateral na lista original");
        listUsers.stream().filter(u -> u.getPontos() < 100);
        listUsers.forEach(System.out::println);

        System.out.println("\n7.2-Filtar os usuário e deixar de ser moderador");
        listUsers.stream()
                .filter(u -> u.getPontos() < 100)
                .forEach(Usuario::deixarDeSerModerador);
        showData();

        System.out.println("\n7.2-Filtar os usuário moderadores");
        listUsers.stream().filter(u -> u.isModerador()).forEach(System.out::println);

        System.out.println("\n7.3-Recebendo de volta a lista do stream");
        List<Usuario> maisQue100 = new ArrayList<>();
        listUsers.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(u -> maisQue100.add(u));
        maisQue100.forEach(System.out::println);

        System.out.println("\nOU");
        maisQue100.clear();
        listUsers.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(maisQue100::add);
        maisQue100.forEach(System.out::println);

        System.out.println("\n7.4-Trabalhando com collect para retornar o Lista");
        Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
        BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
        BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;
        List<Usuario> maisQue100_2 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .collect(supplier, accumulator, combiner);

        System.out.println("\n7.4-Trabalhando com collect simplificando um pouco");
        maisQue100_2 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println("\n7.4-Trabalhando com collect retornando usando a interface Collector");
        maisQue100_2 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toList());

        System.out.println("\n7.4-Trabalhando com collect usanod o import statico de Collector");
        maisQue100_2 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .collect(toList());
        maisQue100_2.forEach(System.out::println);

        System.out.println("\n7.5-Avançado: retornando um set<>");
        Set<Usuario> maisQue100_3 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toSet());
        maisQue100_3.forEach(System.out::println);

        System.out.println("\n7.6-Nova lista com apenas os pontos");
        List<Integer> pontos = new ArrayList<>();
        listUsers.forEach(u -> pontos.add(u.getPontos()));
        pontos.forEach(System.out::println);

        System.out.println("\n7.6-Nova lista com apenas os pontos, usando o Map para não causar efeitos colaterais com o lambda");
        List<Integer> pontos2 = listUsers.stream()
                .map(u -> u.getPontos())
                .collect(toList());
        pontos2.forEach(System.out::println);

        System.out.println("\n7.6-Com o map ainda é possível usar os method reference");
        List<Integer> pontos3 = listUsers.stream()
                .map(Usuario::getPontos)
                .collect(toList());
        pontos3.forEach(System.out::println);

        System.out.println("\n7.7-Tratando o boxing dos nossos inteiros (Forma errada)");
        Stream<Integer> stream2 = listUsers.stream().map(Usuario::getPontos);
        stream2.forEach(System.out::println);

        System.out.println("\n7.7-INTSTREAM - Tratando o boxing dos nossos inteiros (Forma certa)");
        IntStream stream3 = listUsers
                .stream()
                .filter(u -> u.getPontos() < 100)
                .mapToInt(Usuario::getPontos);
        stream3.forEach(System.out::println);

        System.out.println("\n7.7-Resgatando a média dos pontos");
        Double media = listUsers.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();
        System.err.println("media:" + media);

        System.out.println("\n7.7-Resgatando o valor máximo");
        Integer max = listUsers.stream()
                .mapToInt(Usuario::getPontos)
                .max()
                .getAsInt();
        System.err.println("max:" + max);

        System.out.println("\n7.8-OptionalDouble com orelse");
        OptionalDouble media2 = listUsers.stream()
                .mapToInt(Usuario::getPontos)
                .average();

        double pontuacao = media2.orElse(0.0);
        System.err.println("pontuacao:" + pontuacao);

        System.out.println("\n7.8-OptionalDouble com orelse em apenas uma lisata");
        listUsers.clear();
        Double pontuacaoMedia = listUsers.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0);
        System.err.println("pontuacaoMedia:" + pontuacaoMedia);

////////        System.out.println("\n7.8-OptionalDouble com orelse lançando uma Excepiton");
////////        Double pontuacaoMedia2 = listUsers.stream()
////////                .mapToInt(Usuario::getPontos)
////////                .average()
////////                .orElseThrow(IllegalStateException::new);
        organizeListData();
        System.out.println("\n7.8-Pegar o usuário com max pontos");
        Optional<Usuario> maxUserPontos = listUsers
                .stream()
                .max(Comparator.comparing(Usuario::getPontos));
        System.err.println("maxUser:" + maxUserPontos);

        System.out.println("\n7.8-Pegar o nome do usuario com mais pontos");
        Optional<String> maxNomeByPontos = listUsers
                .stream()
                .max(Comparator.comparing(Usuario::getPontos))
                .map(Usuario::getNome);
        System.err.println("maxNomeByPontos:" + maxNomeByPontos);
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

    public static void main(String[] args) {
        new Capitulo7();
    }
}
