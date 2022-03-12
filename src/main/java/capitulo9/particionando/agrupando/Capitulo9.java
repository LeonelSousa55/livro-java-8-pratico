/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo9.particionando.agrupando;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author Leonel
 */
public final class Capitulo9 {

    ArrayList<Usuario> listUsers = new ArrayList();

    public Capitulo9() throws IOException {

        organizeListData();

        System.out.println("\n9.1-Resgatando os dados do file");
        Stream<String> strings = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .flatMap(p -> lines(p));
        strings.forEach(System.out::println);

        System.out.println("\n9.1-Pegando a quantidade de linha do file");
        LongStream lines = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .mapToLong(p -> lines(p).count());
        lines.forEach(System.out::println);

        System.out.println("\n9.1-Pegando a quantidade de linha de todos files");
        List<Long> listLines = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .map(p -> lines(p).count())
                .collect(Collectors.toList());
        listLines.forEach(System.out::println);

        System.out.println("\n9.1-Criando um Map com Path/Lines");
        Map<Path, Long> linesPerFile = new HashMap<>();
        Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .forEach(p -> linesPerFile.put(p, lines(p).count()));
        System.out.println(linesPerFile);

        System.out.println("\n9.1-Otimizando para não ter efeito colateral");
        Map<Path, Long> linesPerFile2 = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toMap(p -> p, p -> lines(p).count()));
        System.out.println(linesPerFile2);

        System.out.println("\n9.1-Gerando um map com todos os dados do file");
        Map<Path, List<String>> content = Files.list(Paths.get("C:\\Meus Projetos\\livro-java-8-pratico\\src\\main\\java\\capitulo9\\particionando\\agrupando"))
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toMap(Function.identity(), p -> lines(p).collect(Collectors.toList())));
        System.out.println(content);

        System.out.println("\n9.1-Mapeando os usuarios no map");
        Map<String, Usuario> nomeToUser = listUsers
                .stream()
                .collect(Collectors.toMap(Usuario::getNome, Function.identity()));
        System.out.println(nomeToUser);

        System.out.println("\n9.2-Agrupando os usuario por pontos, computeIfAbsent"
                + "faz o trabalho de verificar caso ainda não tenha esse número de "
                + "pontos no map ele cria, e depois adiciona na lista");
        Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
        for (Usuario u : listUsers) {

            pontuacao.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
        }
        System.out.println(pontuacao);

        System.out.println("\n9.2-Agrupando os usuario por pontos, usando stream e grouping");
        Map<Integer, List<Usuario>> pontuacao1 = listUsers
                .stream()
                .collect(Collectors.groupingBy(Usuario::getPontos));
        System.out.println(pontuacao1);

        System.out.println("\n9.2-Agrupando os usuario por moderador, usando stream e grouping");
        Map<Boolean, List<Usuario>> moderadores = listUsers
                .stream()
                .collect(Collectors.partitioningBy(Usuario::isModerador));
        System.out.println(moderadores);

        System.out.println("\n9.2-Agrupando os usuario por moderador, salvando apenas os nomes");
        Map<Boolean, List<String>> nomesPorTipos = listUsers
                .stream()
                .collect(Collectors.partitioningBy(
                        Usuario::isModerador,
                        Collectors.mapping(Usuario::getNome, Collectors.toList())));
        System.out.println(nomesPorTipos);

        System.out.println("\n9.2-Agrupando os usuario por moderador, e somando os seus pontos");
        Map<Boolean, Integer> pontuacaoPorTipo = listUsers
                .stream()
                .collect(Collectors.partitioningBy(
                        Usuario::isModerador,
                        Collectors.summingInt(Usuario::getPontos)));
        System.out.println(pontuacaoPorTipo);

        System.out.println("\n9.2-Agrupando os usuario dos usuarios");
        String nomes = listUsers.stream().map(Usuario::getNome).collect(Collectors.joining("', '", "in('", "')"));
        System.out.println(nomes);

        System.out.println("\n9.2-Trabalhando com pipilene em paralelo, somente uma thread");
        List<Usuario> filtrandoOrdenandos = listUsers.stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
        filtrandoOrdenandos.forEach(System.out::println);

        System.out.println("\n9.3-Trabalhando com pipilene em paralelo, mais de "
                + "uma thread, o stream que decide como ele irá gerenciar a "
                + "quantidade de threads, recomendado quando a lista for muito grande");
        List<Usuario> filtrandoOrdenandos1 = listUsers.parallelStream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
        filtrandoOrdenandos1.forEach(System.out::println);

        System.out.println("\n9.3-Comparando a performance com o parallel");
        long sum = LongStream.range(0, 1_000_000_000)
                .parallel()
                .filter(x -> x % 2 == 0)
                .sum();
        System.out.println(sum);

        System.out.println("\n9.3-Comparando a performance sem o parallel");
        long sum1 = LongStream.range(0, 1_000_000_000)
                .filter(x -> x % 2 == 0)
                .sum();
        System.out.println(sum1);

        System.out.println("\n9.4-Operações não determinísticas");
        LongStream.range(0, 1_000).forEach(System.out::println);
        
    }

    public void organizeListData() {

        Usuario u1 = new Usuario("Lucyene", 150, true);
        Usuario u2 = new Usuario("Rodrigo Turini", 120, true);
        Usuario u3 = new Usuario("Guilherme Silveira", 90);
        Usuario u4 = new Usuario("Amanda", 120);
        Usuario u5 = new Usuario("Agatha", 100);

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
            new Capitulo9();
        } catch (IOException ex) {
            Logger.getLogger(Capitulo9.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
