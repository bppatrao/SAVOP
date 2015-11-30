/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static savop.Savop.in;
import static savop.Savop.out;

/**
 *
 * @author BigBoss
 */
public class Utilitarios {

    String nome;

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

    public static boolean validaNome(String nome) {

        if ("".equals(nome)) {
            System.out.println("O campo não pode ficar em branco!");
            return false;
        } else {
            if (nome.matches("\\p{Alpha}*")) {
                System.out.println("Nome Validado!");
                return true;
            } else {
                System.out.println("O nome só pode conter Letras");
                return false;
            }
        }
    }

    public static boolean validaPartido(String partido) {
        if ("".equals(partido)) {
            System.out.println("O campo não pode ficar em branco!");
            return false;
        } else {
            if (partido.matches("\\p{Alpha}*")) {
                System.out.println("Partido Validado!");
                return true;
            } else {
                System.out.println("O Partido só pode conter LETRAS");
                return false;
            }
        }
    }

    public static boolean validadeDataDeNascimento(String datadenascimento) throws java.text.ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();

        /**
         * gera calendário
         */
        cal.setTime(df.parse(datadenascimento));

        /**
         * separando os dados da string para comparacao e validacao
         */
        String[] data = datadenascimento.split("-");
        String ano = data[0];
        String mes = data[1];
        String dia = data[2];

        /**
         * testando se ha discrepancia entre a data que foi inserida no
         * caledario e a data que foi passada como string. se houver diferenca,
         * a data passada era invalida
         *
         */
        if ((new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {
            /**
             * dia invalido
             */
            return (false);
        } else {
            if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {
                /**
                 * mes invalido
                 */

                return (false);
            } else {
                if ((new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue()) {
                    /**
                     * ano invalido
                     */
                    return (false);
                }

                return (true);
            }

        }
    }

    public static int limpaVotacoes(char[] votacoes) {
        int i = 0;
        char F = 'F';
        for (i = 0; i < votacoes.length; i++) {
            votacoes[i] = F;
        }
        return i;

    }

    public static void cabecalho() {
        System.out.printf("%-6s||%-30s||%-10s||%-12s%n", "ID", "NOME",
                "PARTIDO", "DATA NASC");
        System.out.println(
                "==========================…=======================");
    }

    public static void pausa() {
        System.out.println("\n\nPara continuar digite ENTER\n");
        in.nextLine();
    }

}
