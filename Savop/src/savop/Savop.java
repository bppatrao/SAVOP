/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author BigBoss
 */
public class Savop {

    private final static int MAX_DEPUTADOS = 230;
    private final static String FILE_DEPUTADOS = "Deputados.txt";
    private final static String PAGINA_HTML = "Pagina.html";
    private final static int MAX_LINHAS_PAGINA = 5;

    public static Formatter out = new Formatter(System.out);
    public static Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char opcao;
        //teste1

        do {
            opcao = menu();
            switch (opcao) {
                case '1':
                    out.format("1");

                    continuar();
                    break;
                case '2':
                    out.format("2");

                    continuar();
                    break;
                case '3':
                    out.format("3");

                    continuar();
                    break;
                case '4':
                    out.format("4");

                    continuar();
                    break;
                case '5':
                    out.format("5");
                    continuar();
                    break;
                case '6':
                    out.format("6");
                    continuar();
                    break;
                case '7':
                    out.format("7");
                    continuar();
                    break;
                case '8':
                    out.format("8");
                    continuar();
                    break;
                default:
                    if (opcao != '0') {
                        out.format("%n%s%n", "Opção Incorrecta.");
                    } else {
                        out.format("%n%s%n", "Saiu");
                    }
                    continuar();
                    break;

            }

        } while (opcao != '0');

    }

    public static void continuar() {
        out.format("%n%s%n", "Para continuar digite (char Enter)");
        in.nextLine();
    }

    public static char menu() {

        String texto = "\n#================================  MENU  ==================================#"
                + "\n| Carregar Ficheiro de Texto sobre deputados para memória...........( 1 )..|"
                + "\n| Visualizar toda a informação existente em memória.................( 2 )..|"
                + "\n| Actualizar/Alterar informações sobre um deputado..................( 3 )..|"
                + "\n| Carregar Ficheiro de Texto sobre uma determinado votação..........( 4 )..|"
                + "\n| Visualizar a informação dos deputados devidamente ordenada........( 5 )..|"
                + "\n| Visualizar no ecrã os resultados da última votação introduzida....( 6 )..|"
                + "\n| Visualizar votação os resultados obtidos em função dafaixa etária.( 7 )..|"
                + "\n| Criar uma página HTML com a informação obtida no ponto 6..........( 8 )..|"
                + "\n#..............................................................SAIR.( 0 )..#"
                + "\nDigite a sua opção";
        out.format("%n%s%n", texto);
        String opcao = in.nextLine();
        return opcao.charAt(0);

    }

  
    private static int lerFicheiro(String[][] deputados) throws FileNotFoundException {
        /**
         * O metodo lerFicheiro vai receber como parametro vetor vazio
         * ler todos os dados do ficheiro deputados.txt
         * Com a utilização do metodo guardarDadosDeputado guarda os dados
         * no vetor vazio recebido
         * Retorna o número de linhas lidas
         */
        Scanner lerficheiro = new Scanner(new File(FILE_DEPUTADOS));
        int nDeputados = 0;
        while (lerficheiro.hasNext() && nDeputados < MAX_DEPUTADOS) {
            String linha = in.nextLine();
            // teste para verificar a linha ignorando as linhas vazias
            if (linha.length() > 0) {
                nDeputados = guardarDeputados(linha, deputados, nDeputados);
            }
        }
        lerficheiro.close();
        return nDeputados;
    }

   
    private static int guardarDeputados(String linha, String[][] deputados, int nDeputados) {
        /**
         * O metodo guardarDeputados é o responsável por guardar 
         * os dados lidos anteriormente de uma string 
         * acumulando num vetor de dados temporários essa mesma string partida por  ;
         * O vetor deputados guarda os dados recebidos do temporário
         * Retorna o número de linhas gravadas correctamente  
         */
        
        String[] dadostemporarios = linha.split(";");
        
        if (dadostemporarios.length == 4) {
            String id = dadostemporarios[0].trim();
            if (id.length() == 5) {
                deputados[nDeputados][0] = id;
                deputados[nDeputados][1] = dadostemporarios[1].trim();
                deputados[nDeputados][2] = dadostemporarios[2].trim();
                deputados[nDeputados][3] = dadostemporarios[3].trim();
                nDeputados++;
            } else {
                System.out.println("Linha incorreta porque id incorreto");
            }
        } else {
            System.out.println("Linha incorreta porque o nº de campos incorreto");
        }
        return nDeputados;
    }
}
