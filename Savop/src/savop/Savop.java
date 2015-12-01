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
import static savop.Utilitarios.procurarDeputados;

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
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int ndeputados = 0, opcao, nvotacoes = 0, auxnvotacoes=0;
        boolean valida = false;
        String id, assuntovotado;
        String[][] deputados;
        String[][] deputadosvotacoes;
        char[] votacoes;
        deputados = new String[MAX_DEPUTADOS][4];
        deputadosvotacoes = new String [MAX_DEPUTADOS][4];
        votacoes = new char[MAX_DEPUTADOS];
        //teste1

        do {

            opcao = menu();
            switch (opcao) {
                case 1:
                    ndeputados = lerFicheiro(deputados);

                    Utilitarios.continuar();
                    break;
                case 2:
                    listaDeputados(deputados, ndeputados);

                    Utilitarios.continuar();
                    break;
                case 3:
                    out.format("Digite o ID do Deputado do qual pretende alterar os dados:");
                    id = in.nextLine();
                    in.nextLine();
                    valida = alteraDadosDeputado(deputados, id);
                    if (valida = true) {
                        out.format("Deputado alterado com sucesso");
                    } else {
                        out.format("Deputado não alterado");
                    }
                    Utilitarios.continuar();
                    break;
                case 4:
                    out.format("Digite o assunto votado, correspondente ao nome do ficheiro a ler:");
                    assuntovotado = in.nextLine();
                    in.nextLine();
                    nvotacoes = carregarVotacoes(deputados, assuntovotado, votacoes);

                    Utilitarios.continuar();
                    break;
                case 5:
                    auxnvotacoes=Utilitarios.deputadosVotacoes(votacoes,deputados,deputadosvotacoes,ndeputados);
                    listaDeputadosvotacoes(deputadosvotacoes,auxnvotacoes);
                    Utilitarios.continuar();
                    break;
                case 6:
                    out.format("6");
                    Utilitarios.continuar();
                    break;
                case 7:
                    out.format("7");
                    Utilitarios.continuar();
                    break;
                case 8:
                    out.format("8");
                    Utilitarios.continuar();
                    break;
                default:
                    if (opcao != 0) {
                        out.format("%n%s%n", "Opção Incorrecta.");
                    } else {
                        out.format("%n%s%n", "Saiu");
                    }
                    Utilitarios.continuar();
                    break;

            }

        } while (opcao != 0);

    }


    public static int menu() {

        String menu = "\n#================================  MENU  ==================================#"
                + "\n| Carregar Ficheiro de Texto sobre deputados para memória...........( 1 )..|"
                + "\n| Visualizar toda a informação existente em memória.................( 2 )..|"
                + "\n| Actualizar/Alterar informações sobre um deputado..................( 3 )..|"
                + "\n| Carregar Ficheiro de Texto sobre uma determinado votação..........( 4 )..|"
                + "\n| Visualizar a informação dos deputados devidamente ordenada........( 5 )..|"
                + "\n| Visualizar no ecrã os resultados da última votação introduzida....( 6 )..|"
                + "\n| Visualizar votação os resultados obtidos em função da faixa etária( 7 )..|"
                + "\n| Criar uma página HTML com a informação obtida no ponto 6..........( 8 )..|"
                + "\n#..............................................................SAIR.( 0 )..#"
                + "\nDigite a sua opção";
        out.format("%n%s%n", menu);
        int opcao = in.nextInt();
        in.nextLine();
        return opcao;

    }

    private static void listaDeputadosvotacoes(String[][] auxdeputadosvotacoes, int auxnvotacoes) {
        int contPaginas = 0;
        for (int i = 0; i < auxnvotacoes; i++) {
            if (i % MAX_LINHAS_PAGINA == 0) {
                if (contPaginas > 0) {
                    Utilitarios.continuar();
                }
                contPaginas++;
                System.out.println("\nPÁGINA: " + contPaginas);
                Utilitarios.cabecalholistavotacoes();
            }
            System.out.printf("%-6s||%-30s||%-10s||%-12s%n", auxdeputadosvotacoes[i][0],
                    auxdeputadosvotacoes[i][1], auxdeputadosvotacoes[i][2], auxdeputadosvotacoes[i][3]);
        }
    }

    private static void listaDeputados(String[][] deputados, int ndeputados) {
        int contPaginas = 0;
        for (int i = 0; i < ndeputados; i++) {
            if (i % MAX_LINHAS_PAGINA == 0) {
                if (contPaginas > 0) {
                    Utilitarios.continuar();
                }
                contPaginas++;
                System.out.println("\nPÁGINA: " + contPaginas);
                Utilitarios.cabecalho();
            }
            System.out.printf("%-6s||%-30s||%-10s||%-12s%n", deputados[i][0],
                    deputados[i][1], deputados[i][2], deputados[i][3]);
        }
    }

    public static int carregarVotacoes(String[][] deputados, String assuntovotado, char[] votacoes) throws FileNotFoundException {
        /**
         * O metodo lerFicheiro vai receber como parametro vetor vazio ler todos
         * os dados do ficheiro deputados.txt Com a utilização do metodo
         * guardarDadosDeputado guarda os dados no vetor vazio recebido Retorna
         * o número de linhas lidas
         */
        int nvotacoes = 0, pos = 0, validalimpezavotacoes = 0;
        String id,filevotacoes;
        char votacao;
        filevotacoes = assuntovotado+".txt";
        validalimpezavotacoes = Utilitarios.limpaVotacoes(votacoes);
        Scanner lerfic = new Scanner(new File(filevotacoes));
        while (lerfic.hasNext() && nvotacoes < MAX_DEPUTADOS) {
            String linha = lerfic.nextLine();
            linha = linha.trim();
            if (linha.length() > 0) {
                id = linha.substring(0, 5);
                pos = procurarDeputados(deputados, id);
                if (pos != -1) {
                    votacao = linha.charAt(5);
                    votacoes[pos] = votacao;
                    nvotacoes++;
                } else {
                    //out.format("Erro");
                }
            }

        }
        return nvotacoes;
    }

    private static int lerFicheiro(String[][] deputados) throws FileNotFoundException {
        /**
         * O metodo lerFicheiro vai receber como parametro vetor vazio ler todos
         * os dados do ficheiro deputados.txt Com a utilização do metodo
         * guardarDadosDeputado guarda os dados no vetor vazio recebido Retorna
         * o número de linhas lidas
         */
        int nDeputados = 0;
        Scanner lerfic = new Scanner(new File(FILE_DEPUTADOS));
        while (lerfic.hasNext() && nDeputados < MAX_DEPUTADOS) {
            String linha = lerfic.nextLine();
            // teste para verificar a linha ignorando as linhas vazias
            if (linha.length() > 0) {
                nDeputados = guardarDeputados(linha, deputados, nDeputados);
            }
        }
        lerfic.close();
        return nDeputados;
    }

    private static int guardarDeputados(String linha, String[][] deputados, int nDeputados) {
        /**
         * O metodo guardarDeputados é o responsável por guardar os dados lidos
         * anteriormente de uma string acumulando num vetor de dados temporários
         * essa mesma string partida por ; O vetor deputados guarda os dados
         * recebidos do temporário Retorna o número de linhas gravadas
         * correctamente
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

    private static boolean alteraDadosDeputado(String[][] deputados, String id) {
        int posicao = Utilitarios.procurarDeputados(deputados, id), opcao;
        if (posicao != -1) {
            do {
                opcao = menuAlterarDadosDeputado(deputados[posicao]);
                switch (opcao) {
                    case 1:
                        out.format("Novo Nome:");
                        deputados[posicao][1] = in.nextLine();
                        break;
                    case 2:
                        out.format("Novo partido:");
                        deputados[posicao][2] = in.nextLine();
                        break;
                    case 3:
                        out.format("Alterar Data de nascimento");
                        deputados[posicao][3] = in.nextLine();
                        break;
                    default:
                        if (opcao != 0) {
                            out.format("%n%s%n", "Opção Incorrecta.");
                        } else {
                            out.format("%n%s%n", "Saiu");
                        }
                        Utilitarios.continuar();
                        break;
                }
            } while (opcao != 0);
        } else {
            System.out.printf("O deputado %s não foi encontrado!", id);
            return false;
        }
        return true;
    }

    private static int menuAlterarDadosDeputado(String[] deputados) {
        out.format("%6s-%30s-%7s-%12s%n", deputados[0], deputados[1], deputados[2], deputados[3]);

        String menu = "\n#================================  MENU  ==================================#"
                + "\n| Alterar Nome do Deputado..........................................( 1 )..|"
                + "\n| Alterar Partido do Deputado.......................................( 2 )..|"
                + "\n| Alterar Data de nascimento do Deputado............................( 3 )..|"
                + "\n#..............................................................SAIR.( 0 )..#"
                + "\nDigite a sua opção";
        out.format("%n%s%n", menu);
        int opcao = in.nextInt();
        in.nextLine();
        return opcao;
    }
}
