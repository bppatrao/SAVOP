/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.text.DateFormat;
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

    /**
     * Método procurarDeputados é responsável por procurar o deputado pelo seu
     * ID e retornar a sua posição na matriz
     *
     * @param deputados
     * @param id
     * @return posição na matriz
     */
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

    /**
     * Método validaNome verifica se a string recebida só contém caracteres Alfa
     *
     * @param nome
     * @return Verdadeiro se o nome só contiver caracteres Alfa o false se isso
     * não acontecer
     */
    public static boolean validaNome(String nome) {
        if (nome.matches("^[\\p{L} .'-]+$")) {
            return true;
        }
        return false;
    }

    /**
     * Método validaPartidp verifica se a string recebida só contém caracteres
     * Alfa
     *
     * @param partido
     * @return Verdadeiro se o Partido só contiver caracteres Alfa o false se
     * isso não acontecer
     */
    public static boolean validaPartido(String partido) {
        if (partido.matches("^[\\p{L} .'-]+$")) {
            return true;
        }
        return false;
    }

    /**
     * O método validadeDataDeNascimento verifica se a data enviada como string
     * apresenta uma data com o formato correcto e se se trata de uma data
     * válida
     *
     * @param datadenascimento string recebida como parametro no método
     * @return Verdadeiro ou Falso se for uma data inválida
     * @throws java.text.ParseException
     */
    public static boolean validadeDataDeNascimento(String datadenascimento) throws java.text.ParseException {
        DateFormat dataemformato = new SimpleDateFormat("yyyyMMdd");
        Calendar calendario = new GregorianCalendar();

        /**
         * gera calendário
         */
        calendario.setTime(dataemformato.parse(datadenascimento));

        /**
         * separando os dados da string para comparacao e validacao
         */
        String ano = datadenascimento.substring(0, 4);
        String mes = datadenascimento.substring(4, 6);
        String dia = datadenascimento.substring(6, 8);

        /**
         * testando se ha discrepancia entre a data que foi inserida no
         * caledario e a data que foi passada como string. se houver diferenca,
         * a data passada era invalida
         *
         */
        if ((new Integer(dia)).intValue() != (new Integer(calendario.get(Calendar.DAY_OF_MONTH))).intValue()) {
            /**
             * dia invalido
             */
            return (false);
        } else if ((new Integer(mes)).intValue() != (new Integer(calendario.get(Calendar.MONTH) + 1)).intValue()) {
            /**
             * mes invalido
             */

            return (false);
        } else {
            if ((new Integer(ano)).intValue() != (new Integer(calendario.get(Calendar.YEAR))).intValue()) {
                /**
                 * ano invalido
                 */
                return (false);
            }

            return (true);
        }
    }

    /**
     * Método responsálvel por limpar os dados das votações a F (Falta) antes de
     * carregar qualquer dado nesse vector
     *
     * @param votacoes
     */
    public static void limpaVotacoes(char[] votacoes) {
        int i = 0;
        char F = 'F';
        for (i = 0; i < votacoes.length; i++) {
            votacoes[i] = F;
        }
    }

    /**
     * Cabeçalho da listagem Resultados por Faixa etária
     *
     * @param assuntovotado
     */
    public static void cabecalhoresultadosfaixaetaria(String assuntovotado) {
        System.out.println(
                "#======== Resultados em % por faixa etária " + assuntovotado + "  =========#");
        System.out.printf("%-20s# %-15s# %-15s# %-15s%n", "FAIXA ETÁRIA", "VOTOS A FAVOR",
                "VOTOS CONTRA", "ABSTENÇOES");
        System.out.println(
                "=================================================================");
    }

    /**
     * Cabeçalho da listagem Resultados das Votações
     *
     * @param assuntovotado
     */
    public static void cabecalhoresultadosvotacoes(String assuntovotado) {
        System.out.println(
                "#================  Resultados " + assuntovotado + "  =====================#");
        System.out.printf("%-20s# %-15s# %-15s# %-15s%n", "PARTIDO", "VOTOS A FAVOR",
                "VOTOS CONTRA", "ABSTENÇOES");
        System.out.println(
                "=================================================================");

    }

    /**
     * Cabeçalho da lista de votações
     */
    public static void cabecalholistavotacoes() {
        System.out.printf("%-6s# %-30s# %-10s# %-6s%n", "ID", "NOME",
                "PARTIDO", "VOT");
        System.out.println(
                "#=======================  Listagem Votações  =========================#");
    }

    /**
     * Cabeçalho da lista de deputados
     */
    public static void cabecalho() {
        System.out.printf("%-6s# %-30s# %-10s# %-12s%n", "ID", "NOME",
                "PARTIDO", "DATA NASC");
        System.out.println(
                "#==========================  Deputados  =============================#");
    }

    /**
     * Metodo para efectuar uma pausa n passagem de mensagens para o utilizador
     */
    public static void continuar() {
        out.format("%n%s%n", "\nPara continuar digite (char Enter)");
        in.nextLine();
    }

    /**
     * Método nomePrimeiroUltimo recebe como parametro nome completo e devolve
     * 1º e último nome
     *
     * @param nomecompleto
     * @return
     */
    public static String nomePrimeiroUltimo(String nomecompleto) {
        String nomes[] = nomecompleto.split("\\ ");
        String nome1 = nomes[0];
        String sobrenome = nomes[nomes.length - 1];
        String nomeabreviado = nome1 + " " + sobrenome;
        return nomeabreviado;
    }

    /**
     * Método calcularIdade calcula a idade mediante a data de nascimento dada
     * como parametro
     *
     * @param datadenascimento
     * @return
     */
    public static int calcularIdade(String datadenascimento) {
        DateFormat dataemformato = new SimpleDateFormat("yyyyMMdd");
        Date dataNasc = null;
        try {

            dataNasc = dataemformato.parse(datadenascimento);
        } catch (Exception e) {
        }
        Calendar datanascimentonocalendario = new GregorianCalendar();
        datanascimentonocalendario.setTime(dataNasc);

// Cria um objeto calendar com a data atual
        Calendar datahoje = Calendar.getInstance();

// Obtém a idade baseado no ano
        int idade = datahoje.get(Calendar.YEAR) - datanascimentonocalendario.get(Calendar.YEAR);
        datanascimentonocalendario.add(Calendar.YEAR, idade);
        if (datahoje.before(datanascimentonocalendario)) {
            idade--;
        }

        return idade;
    }

    /**
     * Método responsavel por somar o votos por partido e preencher o vector
     * totais votacao
     *
     * @param votospartido
     * @param npartidos
     * @param totaisvotacao
     * @return
     */
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

    /**
     * Método passarPercentagem efectua o calculo da percentagem dos votos
     * apurados por faixa etária
     *
     * @param votosfaixaetaria
     * @param nvotacoes
     * @return
     */
    public static double[][] passarPercentagem(double[][] votosfaixaetaria, int nvotacoes) {
        double percentagem = 0;
        for (int i = 0; i < votosfaixaetaria.length; i++) {
            for (int j = 0; j < votosfaixaetaria[0].length; j++) {
                percentagem = (votosfaixaetaria[i][j] / nvotacoes * 100);
                votosfaixaetaria[i][j] = percentagem;
            }
        }
        return votosfaixaetaria;
    }
}
