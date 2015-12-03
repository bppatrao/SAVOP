/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            if (id.equalsIgnoreCase(deputados[i][0])) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (encontrado == true) {
            //out.format("%s%n", "Deputado Encontrado");
            return i;
        } else {
            return -1;
        }
    }

    public static boolean validaNome(String nome) {

        if ("".equals(nome)) {
            //System.out.println("O campo não pode ficar em branco!");
            return false;
        } else if (nome.matches("\\p{Alpha}*")) {
            //System.out.println("Nome Validado!");
            return true;
        } else {
            //System.out.println("O nome só pode conter Letras");
            return false;
        }
    }

    public static boolean validaPartido(String partido) {
        if ("".equals(partido)) {
            System.out.println("O campo não pode ficar em branco!");
            return false;
        } else if (partido.matches("\\p{Alpha}*")) {
            System.out.println("Partido Validado!");
            return true;
        } else {
            System.out.println("O Partido só pode conter LETRAS");
            return false;
        }
    }

    public static boolean validadeDataDeNascimento(String datadenascimento) throws java.text.ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = new GregorianCalendar();

        /**
         * gera calendário
         */
        cal.setTime(df.parse(datadenascimento));

        /**
         * separando os dados da string para comparacao e validacao
         */
        
        String ano = datadenascimento.substring(0,4);
        String mes = datadenascimento.substring(4,6);
        String dia = datadenascimento.substring(6,8);
        

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
        } else if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {
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

    public static void limpaVotacoes(char[] votacoes) {
        int i = 0;
        char F = 'F';
        for (i = 0; i < votacoes.length; i++) {
            votacoes[i] = F;
        }
    }

    public static void cabecalhoresultadosfaixaetaria(String assuntovotado) {
        System.out.println(
                "#======== Resultados em % por faixa etária " + assuntovotado + "  =========#");
        System.out.printf("%-20s# %-15s# %-15s# %-15s%n", "FAIXA ETÁRIA", "VOTOS A FAVOR",
                "VOTOS CONTRA", "ABSTENÇOES");
        System.out.println(
                "=================================================================");
    }

    public static void cabecalhoresultadosvotacoes(String assuntovotado) {
        System.out.println(
                "#================  Resultados " + assuntovotado + "  =====================#");
        System.out.printf("%-20s# %-15s# %-15s# %-15s%n", "PARTIDO", "VOTOS A FAVOR",
                "VOTOS CONTRA", "ABSTENÇOES");
        System.out.println(
                "=================================================================");

    }

    public static void cabecalholistavotacoes() {
        System.out.printf("%-6s# %-30s# %-10s# %-6s%n", "ID", "NOME",
                "PARTIDO", "VOT");
        System.out.println(
                "#=======================  Listagem Votações  =========================#");
    }

    public static void cabecalho() {
        System.out.printf("%-6s# %-30s# %-10s# %-12s%n", "ID", "NOME",
                "PARTIDO", "DATA NASC");
        System.out.println(
                "#==========================  Deputados  =============================#");
    }

    public static void continuar() {
        out.format("%n%s%n", "\nPara continuar digite (char Enter)");
        in.nextLine();
    }

    public static String nomePrimeiroUltimo(String nomecompleto) {
        String nomes[] = nomecompleto.split("\\ ");
        String nome1 = nomes[0];
        String sobrenome = nomes[nomes.length - 1];
        String nomeabreviado = nome1+" "+sobrenome;
        return nomeabreviado;
    }

    public static int calcularIdade(String datadenascimento) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dataNascInput = null;
        try {

            dataNascInput = sdf.parse(datadenascimento);
        } catch (Exception e) {
        }
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNascInput);

// Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();

// Obtém a idade baseado no ano
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        dateOfBirth.add(Calendar.YEAR, age);
        if (today.before(dateOfBirth)) {
            age--;
        }

        return age;

    }

    public static int[] totaisVotacao(int[][] votospartido, int npartidos, int[] totaisvotacao) {
        for (int i = 0; i < totaisvotacao.length; i++) {
            totaisvotacao[i] = 0;
        }
        for (int j = 0; j < totaisvotacao.length; j++) {
            for (int k = 0; k < npartidos; k++) {
                totaisvotacao[j] = totaisvotacao[j] + votospartido[k][j + 1];
            }
        }
        return totaisvotacao;

    }
    public static double[][]passarPercentagem(double[][]votosfaixaetaria,int nvotacoes){
        double percentagem=0;
         for(int i=0;i<votosfaixaetaria.length;i++){
            for(int j=0;j<votosfaixaetaria[0].length;j++){
                percentagem = (votosfaixaetaria[i][j]/nvotacoes*100);
                votosfaixaetaria[i][j] = percentagem;
            }
        }
        return votosfaixaetaria;
    }
}
