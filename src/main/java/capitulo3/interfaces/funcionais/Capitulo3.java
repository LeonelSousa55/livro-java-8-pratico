package capitulo3.interfaces.funcionais;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leone
 */
public class Capitulo3 {

    public Capitulo3() {

        /*A interface Runnable tem apenas um método abstrato por isso ela é chamada de interface funcional*/
        System.err.println("Cap. 3");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {

                    System.err.println(i);
                }
            }
        };
        new Thread(r).start();

        /*Por isso ela pode ser instacida com uma expressão lambda*/
        System.err.println("Cap. 3");
        Runnable r1 = () -> {
            for (int i = 0; i < 3; i++) {

                System.err.println(i);
            }
        };
        new Thread(r1).start();

        /*Organizando tudo em um único statement*/
        System.err.println("Cap. 3");
        new Thread(() -> {

            for (int i = 0; i < 3; i++) {

                System.err.println(i);
            }
        }).start();
    }

    public static void main(String[] args) {

        new Capitulo3();
    }
}
