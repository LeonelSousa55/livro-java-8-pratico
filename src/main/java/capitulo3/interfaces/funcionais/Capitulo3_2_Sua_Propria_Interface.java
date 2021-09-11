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
public class Capitulo3_2_Sua_Propria_Interface {

    public Capitulo3_2_Sua_Propria_Interface() {

        /*Validação normal criando uma classe abstrata*/
        Validador<String> validadorCEP = new Validador<String>() {
            @Override
            public boolean valida(String valor) {
                return valor.matches("[0-9]{5}-[0-9]{3}");
            }
        };
        System.err.println(validadorCEP.valida("75072-150"));

        /*Validação com lambda*/
        Validador<String> validadorCEPComLambda = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
        System.err.println(validadorCEPComLambda.valida("75072-150"));
    }

    public static void main(String[] args) {

        new Capitulo3_2_Sua_Propria_Interface();
    }
}
