/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo9.particionando.agrupando;

import java.util.stream.LongStream;

/**
 *
 * @author Leonel
 */
public class UnsafeParallelStreamUsage {

    private static long total = 0;

    public static void main(String[] args) {

        LongStream.range(0, 1_000_000_000)
                .parallel()
                .filter(x -> x % 2 == 0)
                .forEach(n -> total += n);
        System.out.println(total);
    }
}
