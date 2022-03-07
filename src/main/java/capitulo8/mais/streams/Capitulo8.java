/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo8.mais.streams;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Leonel
 */
public final class Capitulo8 {

    ArrayList<Usuario> listUsers = new ArrayList();

    public Capitulo8() throws IOException {

        organizeListData();

        System.out.println("\n8.1-Ordenando a lista normal");
        listUsers.sort(Comparator.comparing(Usuario::getNome));
        showData();

        System.out.println("\n8.1-Ordenando a lista usando stream");
        Stream<Usuario> stream = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome));
        stream.forEach(System.out::println);

        System.out.println("\n8.1-Ordenando a lista usando stream reversed e retornando uma nova lista com o collect");
        List<Usuario> filtradorOrdenados = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome).reversed())
                .limit(100)
                .collect(Collectors.toList());
        filtradorOrdenados.forEach(System.out::println);

        System.out.println("\n8.2-m considernado que o stream é um conjunto de pipiline "
                + "de excuções muitos desses metodos são considerados como lazy e executam "
                + "realmente quando necessários para obter o resultado final");

        System.out.println("\n8.3-Usando o metodos finAny (operacao terminal) para "
                + "filtrar o primeiro usuário e não precisar passar por todo o stream");
        Optional<Usuario> usuarioOptional = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .findAny();
        System.out.println(usuarioOptional);

        System.out.println("\n8.4-Como podemos saber que o findAny, findFirt funcionou:"
                + "Podemos usar o peek para exibir os dados do usuário que achar primeiro "
                + "com mais de 100 pontos");
        organizeListData();
        Optional<Usuario> usuarioOptional2 = listUsers
                .stream()
                .filter(u -> u.getPontos() > 100)
                .peek(System.out::println)
                .findFirst();

        System.out.println("\n8.4-findAny se trata de uma operação terminal, porém "
                + "sorted é uma operação intermediaria que pode ser que precise passar "
                + "por todo stream");
        Optional<Usuario> usuarioOptional3 = listUsers
                .stream()
                .sorted(Comparator.comparing(Usuario::getNome).reversed())
                .peek(System.out::println)
                .findAny();
        System.out.println(usuarioOptional3);

        System.out.println("\n8.5-Trabalhando com as operações: max, sum");
        Optional<Usuario> usuarioOptional4 = listUsers
                .stream()
                .max(Comparator.comparing(Usuario::getPontos));
        System.out.println(usuarioOptional4);

        System.out.println("\n8.5-Trabalhando com as operações de Reduction (Redução que retorna os valores)");
        int totalPontos = listUsers
                .stream()
                .mapToInt(Usuario::getPontos)
                .sum();
        System.out.println("totalPontos:" + totalPontos);

        System.out.println("\n8.5-Essa mesma soma poderia ficar assim:");
        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;
        int totalPontos2 = listUsers
                .stream()
                .mapToInt(Usuario::getPontos)
                .reduce(valorInicial, operacao);
        System.out.println("totalPontos2:" + totalPontos2);

        System.out.println("\n8.5- ou assim");
        int totalPontos3 = listUsers
                .stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);
        System.out.println("totalPontos3:" + totalPontos3);

        System.out.println("\n8.5- ou assim");
        int totalPontos4 = listUsers
                .stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum);
        System.out.println("totalPontos4:" + totalPontos4);

        System.out.println("\n8.5-Criando um aperação de multiplicaçao");
        int multiplicacao = listUsers
                .stream()
                .mapToInt(Usuario::getPontos)
                .reduce(1, (a, b) -> a * b);
        System.out.println("multiplicacao:" + multiplicacao);

        System.out.println("\n8.5-Somando o valor sem o map");
        int totalPontos1 = listUsers
                .stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);
        System.out.println("totalPontos1:" + totalPontos1);

        System.out.println("\n8.6-Varrendo a lista com stream.iterator");
        listUsers.stream().iterator().forEachRemaining(System.out::println);

        System.out.println("\n8.6-Validar se existe moderador e se estiver parar o processo");
        boolean hasModerador = listUsers
                .stream()
                .anyMatch(Usuario::isModerador);
        System.out.println("hasModerador:" + hasModerador);

        System.out.println("\n8.6-Criar um stream vazio.");
        Stream<Usuario> stream1 = Stream.empty();
        System.out.println("stream1:" + stream1);

        System.out.println("\n8.6-Criar um stream com valores.");
        Stream<Usuario> stream2 = Stream.of(
                new Usuario("Lucyene", 50),
                new Usuario("Lucyene", 50),
                new Usuario("Lucyene", 50));
        stream2.forEach(System.out::println);

//////        System.out.println("\n8.7-Criando streams infinitos");
//////        Random random = new Random(0);
//////        Supplier<Integer> supplier = () -> random.nextInt();
//////        Stream<Integer> stream4 = Stream.generate(supplier);
//////        stream4.forEach(System.out::println);
////        System.out.println("\n8.7-Tratando para não gerar boxing");
////        Random random2 = new Random(0);
////        IntStream stream5 = IntStream.generate(() -> random2.nextInt());
////        stream5.forEach(System.out::println);
        System.out.println("\n8.7-Organizando o curto circuito para parar a execução");
        Random random3 = new Random(0);
        IntStream stream6 = IntStream.generate(() -> random3.nextInt());
        List<Integer> list = stream6
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("\n8.7-Usando o genarete");
        Random random4 = new Random(0);
        List<Integer> list1 = IntStream
                .generate(() -> random4.nextInt())
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        list1.forEach(System.out::println);

        System.out.println("\n8.7-Implementando IntSupplier");
        class Fibonacci implements IntSupplier {

            private int anterior = 0;
            private int proximo = 1;

            @Override
            public int getAsInt() {
                proximo = proximo + anterior;
                anterior = proximo - anterior;
                return anterior;
            }
        }

        IntStream.generate(new Fibonacci())
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\n8.7-Pegando o primeiro elemento maior que 100");
        Integer maiorQue100 = IntStream
                .generate(new Fibonacci())
                .filter(f -> f > 100)
                .findFirst()
                .getAsInt();
        System.out.println(maiorQue100);

        System.out.println("\n8.8-A classe Files agora também tem o método de stream, para listar todos os files basta:");
        Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .forEach(System.out::println);

        System.out.println("\n8.8-Filtrar apenas os files .Java");
        Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(System.out::println);

        System.out.println("\n8.8-Usando o método statico para listar, para controlar a exception");
        Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .map(p -> lines(p))
                .forEach(System.out::println);

        System.out.println("\n8.8-Sem o uso do forEach");
        Stream<Stream<String>> strings = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .map(p -> lines(p));
        System.out.println("strings:" + strings);

        System.out.println("\n8.9-FlapMap, lendo todo o contéudo do arquivo");
        Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .filter(p -> p.toString().endsWith(".txt"))
                .flatMap(p -> lines(p))
                .forEach(System.out::println);

        System.out.println("\n8.9-FlapMap, resgatar todos os caracteres do file");
        IntStream chars = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo8\\mais\\streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .flatMap(p -> lines(p))
                .flatMapToInt((String s) -> s.chars());
        chars.forEach(System.out::print);

        System.out.println("\n\n8.9-FlapMap, Unindo dois grupos e removendo os usuarios iguais");
        Usuario user1 = new Usuario("Lucyene", 50);
        Usuario user2 = new Usuario("Rodrigo Turini", 60);
        Usuario user3 = new Usuario("Guilherme Silveira", 100);

        Grupo englishSpeakers = new Grupo();
        englishSpeakers.add(user1);
        englishSpeakers.add(user2);

        Grupo spanishSpeakers = new Grupo();
        spanishSpeakers.add(user2);
        spanishSpeakers.add(user3);

        List<Grupo> groups = Arrays.asList(englishSpeakers, spanishSpeakers);
        groups.stream()
                .flatMap(g -> g.getUsuarios().stream())
                .distinct()
                .forEach(System.out::println);

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

    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) {
        try {
            new Capitulo8();
        } catch (IOException ex) {
            Logger.getLogger(Capitulo8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
