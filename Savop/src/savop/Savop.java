/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

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
                case 1:

                    continuar();
                    break;
                case 2:

                    continuar();
                    break;
                case 3:

                    continuar();
                    break;
                case 4:

                    continuar();
                    break;
                case 5:
                    continuar();
                    break;
                case 6:
                    continuar();
                    break;
                case 7:
                    continuar();
                    break;
                case 8:
                    continuar();
                    break;
                default:
                    out.format("%n%s%n", "Opção Incorrecta.");
                    continuar();
                    break;

            }

        } while (opcao != 0);

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

    /**
     * Carrega informação dos deputados para memória a partir de ficheiro de
     * texto de nome FILE_DEPUTADOS
     *
     * @param deputados - matriz de strings para guardar a info de deputados
     * @return o número de deputados inseridos na matriz
     * @throws FileNotFoundException
     */
    private static int lerInfoFicheiro(String[][] deputados) throws FileNotFoundException {
        Scanner fin = new Scanner(new File(FILE_DEPUTADOS));
        int nDeputados = 0;
        while (fin.hasNext() && nDeputados < MAX_DEPUTADOS) {
            String linha = in.nextLine();
            // Verifica se linha não está em branco
            if (linha.length() > 0) {
                nDeputados = guardarDadosDeputado(linha, deputados, nDeputados);
            }
        }
        fin.close();
        return nDeputados;
    }

    /**
     * Acede à informação de uma linha do ficheiro e guarda na estrutura dados
     * deputados se a linha tiver a estrutura correta e o id com 5 carateres
     *
     * @param linha - String com o conteúdo de uma linha do ficheiro com info de
     * um deputado
     * @param deputados - matriz de strings com a informação dos deputados
     * @param nDeputados - número de deputados existentes na matriz deputados
     * @return o novo número de deputados
     */
    private static int guardarDadosDeputado(String linha, String[][] deputados, int nDeputados) {
        // separador de dados por linha
        String[] temp = linha.split(";");
        if (temp.length == 4) {
            String id = temp[0].trim();
            if (id.length() == 5) {
                deputados[nDeputados][0] = id;
                deputados[nDeputados][1] = temp[1].trim();
                deputados[nDeputados][2] = temp[2].trim();
                deputados[nDeputados][3] = temp[3].trim();
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
