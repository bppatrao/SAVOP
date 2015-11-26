/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import static savop.Savop.out;

/**
 *
 * @author BigBoss
 */
public class Utilitarios {
    
        public static int procurarDeputados(String[][] deputados, String id) {
        int i = 0;
        boolean encontrado = false;

        while (i < deputados.length && !encontrado) {
            if (id.equals(deputados[i][0])) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (encontrado == true) {
            out.format("%s%n", "Deputado Encontrado");
            return i;
        } else {
            return -1;
        }
    }
}
