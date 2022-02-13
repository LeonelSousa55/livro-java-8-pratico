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
public class Capitulo3_2_SuaPropriaInterface {

    public Capitulo3_2_SuaPropriaInterface() {

        /*Validação normal criando uma classe abstrata*/
        Capitulo3_2_InterfaceValidador<String> validadorCEP = new Capitulo3_2_InterfaceValidador<String>() {
            @Override
            public boolean valida(String valor) {
                return valor.matches("[0-9]{5}-[0-9]{3}");
            }
        };
        System.err.println(validadorCEP.valida("75072-150"));

        /*Validação com lambda*/
        Capitulo3_2_InterfaceValidador<String> validadorCEPComLambda = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
        System.err.println(validadorCEPComLambda.valida("75072-150"));
    }

    public static void main(String[] args) {

        new Capitulo3_2_SuaPropriaInterface();
    }  
}
