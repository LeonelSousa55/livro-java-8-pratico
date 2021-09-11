/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo3.interfaces.funcionais;

/**
 *
 * @author leone
 */
public class Capitulo3_4_Acessando_Variaveis_Locais {

    public Capitulo3_4_Acessando_Variaveis_Locais() {

        final int numero = 5;

        new Thread(() -> {

            System.err.println(numero);
        }).start();
    }

    public static void main(String[] args) {

        new Capitulo3_4_Acessando_Variaveis_Locais();
    }
}
