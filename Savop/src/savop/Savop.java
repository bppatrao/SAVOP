/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.io.FileNotFoundException;
import java.io.File;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author BigBoss
 */
public class Savop {

    private final static int MAX_DEPUTADOS = 230;
    private final static String FILE_DEPUTADOS = "Deputados.txt";
    private final static String PAGINA_HTML = "_Pagina.html";
    private final static int MAX_LINHAS_PAGINA = 10;

    public static Formatter out = new Formatter(System.out);
    public static Scanner in = new Scanner(System.in);

    /**
     * Metodo Main / Principal é onde iniciamos o programa e contrloamos todas
     * as opções
     *
     * @param ndeputados armazena o numero de deputados lidos do ficheiro
     * deputados
     * @param npartidos armazena o numero diferente de partidos existentes no
     * ficheiro deputados
     * @param opcao do menu escolhida
     * @param nvotacoes numero de votacoes lidas com sucesso
     * @param auxvotacoes é o numero de votacoes encontradas por e utilizo para
     * no valor para as organizar
     * @param valida é uma validação para controlar o sucesso da alteração dos
     * dados do deputado
     *
     *
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        int ndeputados = 0, npartidos = 0, opcao, nvotacoes = 0, auxnvotacoes = 0;
        boolean valida = false;
        File logErros = LogErros.criarLogErros();
        Formatter escreverlog = new Formatter(logErros);
        String id, assuntovotado = "";
        String[][] deputados;
        String[][] deputadosvotacoes;
        String[] partidos;
        int[][] votospartido;
        int[] totaisvotacao;
        double[][] votosfaixaetaria;
        char[] votacoes;
        deputados = new String[MAX_DEPUTADOS][4];
        deputadosvotacoes = new String[MAX_DEPUTADOS][4];
        votacoes = new char[MAX_DEPUTADOS];
        partidos = new String[MAX_DEPUTADOS];
        totaisvotacao = new int[3];

        do {

            opcao = menu();
            switch (opcao) {
                case 1:
                    ndeputados = lerFicheiro(deputados, escreverlog);
                    npartidos = partidos(deputados, ndeputados, partidos);

                    Utilitarios.continuar();
                    break;
                case 2:
                    if (ndeputados == 0) {
                        out.format("\nNão é possivel listar os dados pretendidos, pois não tem qualquer deputado carregado em sistema. Ponto 1");
                    } else {
                        listaDeputados(deputados, ndeputados);
                    }
                    Utilitarios.continuar();
                    break;
                case 3:
                    if (ndeputados == 0) {
                        out.format("\nNão é possivel alterar os dados pretendidos pois não tem qualquer deputado carregado em sistema. Ponto 1");
                    } else {
                        out.format("\nDigite o ID do Deputado do qual pretende alterar os dados:");
                        id = in.nextLine();
                        valida = alteraDadosDeputado(deputados, id);
                        if (valida == true) {
                            out.format("Deputado alterado com sucesso");
                        } else {
                            out.format("Deputado não alterado");
                        }
                    }
                    Utilitarios.continuar();
                    break;
                case 4:
                    if (ndeputados == 0) {
                        out.format("\nNão é possivel alterar os dados pretendidos pois não tem qualquer deputado carregado em sistema. Ponto 1");
                    } else {
                        do {
                            out.format("\nDigite o assunto votado, correspondente ao nome do ficheiro a ler (0 para cancelar):");
                            assuntovotado = in.nextLine();
                        } while (assuntovotado.length() == 0 && !assuntovotado.equals("0"));
                        if (!assuntovotado.equals("0")) {
                            nvotacoes = carregarVotacoes(deputados, assuntovotado, votacoes, escreverlog);
                        } else {
                            out.format("\nOptou por cancelar a leitura:");
                        }
                    }
                    Utilitarios.continuar();
                    break;
                case 5:
                    if (nvotacoes > 0) {
                        auxnvotacoes = deputadosVotacoes(votacoes, deputados, deputadosvotacoes, ndeputados);
                        listaDeputadosvotacoes(deputadosvotacoes, auxnvotacoes);
                    } else {
                        out.format("\nPara obter esta listagem necessita que os deputados Ponto 1, e votacao Ponto 4 estejam carregados");
                    }
                    Utilitarios.continuar();
                    break;
                case 6:
                    if (nvotacoes > 0) {
                        votospartido = new int[npartidos][4];
                        votospartido = votosPorPartido(deputados, ndeputados, partidos, npartidos, votacoes, votospartido);
                        ordenarVotosPorPartido(votospartido, partidos, npartidos);
                        listagemResultadosVotacoes(assuntovotado, partidos, votospartido, npartidos, totaisvotacao);
                        guardarListagemResultadosVotacoes(assuntovotado, partidos, votospartido, npartidos, totaisvotacao);
                    } else {
                        out.format("\nPara obter esta listagem necessita que os deputados Ponto 1, e votacao Ponto 4 estejam carregados");
                    }
                    Utilitarios.continuar();
                    break;
                case 7:
                    if (nvotacoes > 0) {
                        votosfaixaetaria = new double[3][3];
                        votosfaixaetaria = votosPorFaixaEtaria(deputados, ndeputados, votacoes, votosfaixaetaria, nvotacoes);
                        listagemResultadosFEtaria(assuntovotado, votosfaixaetaria);
                    } else {
                        out.format("\nPara obter esta listagem necessita que os deputados Ponto 1, e votacao Ponto 4 estejam carregados");
                    }
                    Utilitarios.continuar();
                    break;
                case 8:
                    if (nvotacoes > 0) {
                        votospartido = new int[npartidos][4];
                        votospartido = votosPorPartido(deputados, ndeputados, partidos, npartidos, votacoes, votospartido);
                        ordenarVotosPorPartido(votospartido, partidos, npartidos);
                        criarPaginaHTML(assuntovotado, votospartido, npartidos, partidos, totaisvotacao);
                    } else {
                        out.format("\nPara obter esta listagem necessita que os deputados Ponto 1, e votacao Ponto 4 estejam carregados");
                    }
                    Utilitarios.continuar();
                    break;
                default:
                    if (opcao != 0) {
                        out.format("%n%s%n", "Opção Incorrecta.");
                    } else {
                        escreverlog.close();
                        out.format("%n%s%n", "Saiu");
                    }
                    break;

            }

        } while (opcao != 0);

    }

    /**
     * Iniciar Menu da Aplicação
     *
     * @param opcao - retorna o valor digitado pelo utilizador "opcao" do Menu
     * pretendida
     */
    public static int menu() {

        String menu = "\n#================================  MENU  ==================================#"
                + "\n| Carregar Ficheiro de Texto sobre deputados para memória...........( 1 )..|"
                + "\n| Visualizar toda a informação existente em memória.................( 2 )..|"
                + "\n| Actualizar/Alterar informações sobre um deputado..................( 3 )..|"
                + "\n| Carregar Ficheiro de Texto sobre uma determinado votação..........( 4 )..|"
                + "\n| Visualizar no ecrã os deputados VS votacao devidamente ordenada...( 5 )..|"
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

    /**
     * Método responsável por Criar Pagina HTML com resultado da votação por
     * partido
     *
     * @param nomeFich nome do ficheiro a guardar em HTML
     * @param titulo Titulo da pagina HTLM
     * @param cabecalho Cabecalho da pagina HTLM
     * @param cabecalhotabela Cabeçalho da tabela dos valores apresentados
     * @param cabeçalhotabelab Divisão entre cabeçalho e dados
     *
     */
    private static void criarPaginaHTML(String assuntovotado, int[][] votospartido, int npartidos, String[] partidos, int[] totaisvotacao) throws FileNotFoundException {
        String nomeFich = "Resultados_"+assuntovotado+PAGINA_HTML;
        String titulo = "RESULTADOS DA VOTACAO " + assuntovotado;
        String cabecalho = "RESULTADOS DA VOTACAO " + assuntovotado;
        String[] cabecalhotabela = new String[]{"PARTIDOS", "VOTOS A FAVOR", "VOTOS CONTRA", "ABSTENÇOES"};
        String[] cabecalhotabelab = new String[]{"===========", "===========", "===========", "==========="};

        totaisvotacao = Utilitarios.totaisVotacao(votospartido, npartidos, totaisvotacao);

        Formatter pag = new Formatter(new File(nomeFich));

        PaginaHtml.iniciarPagina(pag, titulo);

        PaginaHtml.cabecalho(pag, 20, cabecalho);

        PaginaHtml.criarTabelaComDuasLinhasTitulos(pag, cabecalhotabela, cabecalhotabelab, votospartido, partidos, totaisvotacao, npartidos);

        PaginaHtml.fecharPaginaComData(pag);

        pag.close();

    }

    /**
     * Listagem para ecrã dos Resultados das votações por Faixa Etária
     *
     * @param contPagina - variável utilizada para controlo da paginação
     * pretendida
     */
    private static void listagemResultadosFEtaria(String assuntovotado, double[][] votosfaixaetaria) {
        int contPaginas = 0;
        Utilitarios.cabecalhoresultadosfaixaetaria(assuntovotado);
        System.out.printf("%-20s# %-15.2f# %-15.2f# %-15.2f%n", "<35anos",
                votosfaixaetaria[0][0], votosfaixaetaria[0][1], votosfaixaetaria[0][2]);

        System.out.println(
                "=================================================================");
        System.out.printf("%-20s# %-15.2f# %-15.2f# %-15.2f%n", ">=35anos e <=60anos",
                votosfaixaetaria[1][0], votosfaixaetaria[1][1], votosfaixaetaria[1][2]);

        System.out.println(
                "=================================================================");
        System.out.printf("%-20s# %-15.2f# %-15.2f# %-15.2f%n", ">60anos",
                votosfaixaetaria[2][0], votosfaixaetaria[2][1], votosfaixaetaria[2][2]);

        System.out.println(
                "=================================================================");
    }

    /**
     * Metodo responsável por preencher array de votos por Faixa Etária
     *
     * @param idade - vai ser utilizada para guardar a idade obtida pelo método
     * calcularIdade da class Utilitarios
     * @param votacao - valor da votacao utilizada no switch para salvaguardar
     * na respectiva faixa etária
     */
    private static double[][] votosPorFaixaEtaria(String[][] deputados, int ndeputados, char[] votacoes, double[][] votosfaixaetaria, int nvotacoes) {
        int idade;
        char votacao;
        for (int i = 0; i < ndeputados; i++) {
            idade = Utilitarios.calcularIdade(deputados[i][3]);
            if (idade < 35) {
                votacao = votacoes[i];
                switch (votacao) {
                    case 'S':
                        votosfaixaetaria[0][0]++;
                        break;
                    case 'N':
                        votosfaixaetaria[0][1]++;
                        break;
                    case 'A':
                        votosfaixaetaria[0][2]++;
                        break;
                    default:
                        break;
                }
            } else if (idade >= 35 && idade <= 60) {
                votacao = votacoes[i];
                switch (votacao) {
                    case 'S':
                        votosfaixaetaria[1][0]++;
                        break;
                    case 'N':
                        votosfaixaetaria[1][1]++;
                        break;
                    case 'A':
                        votosfaixaetaria[1][2]++;
                        break;
                    default:
                        break;
                }

            } else if (idade > 60) {
                votacao = votacoes[i];
                switch (votacao) {
                    case 'S':
                        votosfaixaetaria[2][0]++;
                        break;
                    case 'N':
                        votosfaixaetaria[2][1]++;
                        break;
                    case 'A':
                        votosfaixaetaria[2][2]++;
                        break;
                    default:
                        break;
                }

            }
        }
        votosfaixaetaria = Utilitarios.passarPercentagem(votosfaixaetaria, nvotacoes);
        return votosfaixaetaria;
    }

    /**
     * Metodo responsável guardar num Ficheiro txt os Resultados das Votacoes
     *
     * @param nomeFich - nome pelo qual vai ser guardado o ficheiro
     * @param escreverfich- objecto responsavél por escrever do respectivo
     * ficheiro
     */
    private static void guardarListagemResultadosVotacoes(String assuntovotado, String[] partidos, int[][] votospartido, int npartidos, int[] totaisvotacao) throws FileNotFoundException {
        String nomeFich = "Resultados_" + assuntovotado + ".txt";
        Formatter escreverfich = new Formatter(new File(nomeFich));
        escreverfich.format("%n%s%n", "Resultados: " + assuntovotado);
        escreverfich.format(
                "#================  Resultados " + assuntovotado + "  =====================#%n");
        escreverfich.format("%-20s# %-15s# %-15s# %-15s%n", "PARTIDO", "VOTOS A FAVOR",
                "VOTOS CONTRA", "ABSTENÇOES");
        escreverfich.format(
                "=================================================================%n");
        for (int i = 0; i < npartidos; i++) {
            escreverfich.format("%-20s# %-15s# %-15s# %-15s%n", partidos[i],
                    votospartido[i][1], votospartido[i][2], votospartido[i][3]);
        }
        escreverfich.format(
                "=================================================================%n");
        escreverfich.format("%-20s# %-15s# %-15s# %-15s%n", "Totais Votação",
                totaisvotacao[0], totaisvotacao[1], totaisvotacao[2]);
        escreverfich.format(
                "=================================================================");
        escreverfich.close();
    }

    /**
     * Metodo responsável por mostrar no ecrã a listagem dos Resultados das
     * Votacoes
     *
     * @param contPagina - variável utilizada para controlo da paginação
     * pretendida
     */
    private static void listagemResultadosVotacoes(String assuntovotado, String[] partidos, int[][] votospartido, int npartidos, int[] totaisvotacao) {
        int contPaginas = 0;
        totaisvotacao = Utilitarios.totaisVotacao(votospartido, npartidos, totaisvotacao);
        for (int i = 0; i < npartidos; i++) {
            if (i % MAX_LINHAS_PAGINA == 0) {
                if (contPaginas > 0) {
                    Utilitarios.continuar();
                }
                contPaginas++;
                System.out.println("\nPÁGINA: " + contPaginas);
                Utilitarios.cabecalhoresultadosvotacoes(assuntovotado);
            }
            System.out.printf("%-20s# %-15s# %-15s# %-15s%n", partidos[i],
                    votospartido[i][1], votospartido[i][2], votospartido[i][3]);
        }
        System.out.println(
                "=================================================================");
        System.out.printf("%-20s# %-15s# %-15s# %-15s%n", "Totais Votação",
                totaisvotacao[0], totaisvotacao[1], totaisvotacao[2]);
        System.out.println(
                "=================================================================");

    }

    /**
     * Metodo responsável por ordenar os votos por partido de maior
     * representatividade e no caso de igualdade por ordem ascendente pelo nome
     *
     */
    private static void ordenarVotosPorPartido(int[][] votospartido, String[] partidos, int npartidos) {
        for (int i = 0; i < npartidos - 1; i++) {
            for (int j = i + 1; j < npartidos; j++) {
                if (votospartido[i][0] < (votospartido[j][0])) {
                    int[] tmp = votospartido[i];
                    votospartido[i] = votospartido[j];
                    votospartido[j] = tmp;
                    String aux = partidos[i];
                    partidos[i] = partidos[j];
                    partidos[j] = aux;
                } else if (votospartido[i][0] == votospartido[j][0]) {
                    if (partidos[i].compareTo(partidos[j]) > 0) {
                        int[] tmp = votospartido[i];
                        votospartido[i] = votospartido[j];
                        votospartido[j] = tmp;
                        String aux = partidos[i];
                        partidos[i] = partidos[j];
                        partidos[j] = aux;
                    }
                }
            }
        }
    }

    /**
     * Metodo responsável por preencher array de votos por partido
     * (votospartido)
     *
     * @param votacao - valor da votacao utilizada no switch para salvaguardar
     * no respectivo partido
     * @return a matriz de votospartido
     */
    private static int[][] votosPorPartido(String[][] deputados, int ndeputados, String[] partidos, int npartidos, char[] votacoes, int[][] votospartido) {
        char votacao;
        for (int i = 0; i < npartidos; i++) {
            for (int j = 0; j < ndeputados; j++) {
                if (partidos[i].equalsIgnoreCase(deputados[j][2])) {
                    votacao = votacoes[j];
                    switch (votacao) {
                        case 'S':
                            votospartido[i][0]++;
                            votospartido[i][1]++;
                            break;
                        case 'N':
                            votospartido[i][0]++;
                            votospartido[i][2]++;
                            break;
                        case 'A':
                            votospartido[i][0]++;
                            votospartido[i][3]++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return votospartido;

    }

    /**
     * Metodo partidos é responsável por preencher array de partidos existentes
     * nos deputados
     *
     * @param partidodeputado guardar o partido do deputado
     * @param partido partido lido do vetor partidos para validar se já existe
     * ou não
     * @param npartidos numero de partigos defirentes dos deputados
     * @param auxcont é uma flag para verificar se o partido já existe na vector
     */
    private static int partidos(String[][] deputados, int ndeputados, String[] partidos) {
        String partidodeputado, partido;
        int npartidos = 0, auxcont;
        for (int i = 0; i < ndeputados; i++) {
            auxcont = 0;
            partidodeputado = deputados[i][2];
            for (int j = 0; j < npartidos; j++) {
                partido = partidos[j];
                if (partidodeputado.equalsIgnoreCase(partido)) {
                    auxcont++;
                }
            }
            if (auxcont == 0) {
                partidos[npartidos] = partidodeputado;
                npartidos++;
            }
        }
        return npartidos;
    }

    /**
     * O metodo listaDeputadosvotacoes vai listar os deputados com a paginacao
     * de 10 linhas que quem votação registada
     *
     * @param contPaginas acumula todas as paginas listadas
     */
    private static void listaDeputadosvotacoes(String[][] deputadosvotacoes, int auxnvotacoes) {
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
            System.out.printf("%-6s# %-30s# %-10s# %-6s%n", deputadosvotacoes[i][0],
                    deputadosvotacoes[i][1], deputadosvotacoes[i][2], deputadosvotacoes[i][3]);
        }
    }

    /**
     * O metodo listaDeputados vai listar os deputados com a paginacao de 10
     * linhas
     *
     * @param contPaginas acumula todas as paginas listadas
     */
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

    /**
     * O metodo deputadosVotacoes vai ser responsável por preencher a matriz
     * deputadosvotacoes com as respetivas votacões anteriormente lidas
     *
     */
    public static int deputadosVotacoes(char[] votacoes, String[][] deputados, String[][] deputadosvotacoes, int ndeputados) {
        int i = 0, votacoesencontradas = 0;
        String nomeabreviado;
        for (i = 0; i < votacoes.length; i++) {
            if (votacoes[i] != 'F') {
                deputadosvotacoes[votacoesencontradas][0] = deputados[i][0];
                nomeabreviado = Utilitarios.nomePrimeiroUltimo(deputados[i][1]);
                deputadosvotacoes[votacoesencontradas][1] = nomeabreviado;
                deputadosvotacoes[votacoesencontradas][2] = deputados[i][2];
                deputadosvotacoes[votacoesencontradas][3] = String.valueOf(votacoes[i]);
                votacoesencontradas++;
            }
        }
        ordenarDeputadosVotacoes(deputadosvotacoes, votacoesencontradas);
        return votacoesencontradas;
    }

    /**
     * O metodo ordenarDeputadosVotacoes é responsável por ordenar a matriz
     * deputadosvotacoes pelo ID do deputado
     *
     */
    public static void ordenarDeputadosVotacoes(String[][] deputadosvotacoes, int votacoesencontradas) {
        for (int i = 0; i < votacoesencontradas - 1; i++) {
            for (int j = i + 1; j < votacoesencontradas; j++) {
                if (deputadosvotacoes[i][0].compareTo(deputadosvotacoes[j][0]) > 0) {
                    String[] tmp = deputadosvotacoes[i];
                    deputadosvotacoes[i] = deputadosvotacoes[j];
                    deputadosvotacoes[j] = tmp;

                }
            }
        }
    }

    /**
     * O metodo carregarVotacoes vai receber como parametro vetor das votacoes o
     * vector dos deputados e a string do assunto votado
     *
     * @param nvotacoes acumula todas as votações validas guardadas
     * @param pos é a posição correspondente ao deputado com votação
     * @param votacao guarda a votação lida
     * @param filevotacoes é o nome do ficheiro que corresponde ao assunto
     * votado + ".txt"
     * @return o numero de votações lidas com sucesso
     */
    public static int carregarVotacoes(String[][] deputados, String assuntovotado, char[] votacoes, Formatter escreverlog) throws FileNotFoundException {
        int nvotacoes = 0, pos;
        String id, filevotacoes;
        char votacao;
        filevotacoes = assuntovotado + ".txt";
        Utilitarios.limpaVotacoes(votacoes);
        Scanner lerfic = new Scanner(new File(filevotacoes));
        while (lerfic.hasNext() && nvotacoes < MAX_DEPUTADOS) {
            String linha = lerfic.nextLine();
            linha = linha.trim();
            if (linha.length() > 0) {
                id = linha.substring(0, 5);
                pos = Utilitarios.procurarDeputados(deputados, id);
                if (pos != -1) {
                    votacao = linha.charAt(5);
                    if (votacao != 'S' && votacao != 'N' && votacao != 'A') {
                        String erro = "Ao ler o ficheiro votações " + filevotacoes + " o deputado " + id + " apresenta votacao incorrecta ";
                        LogErros.guardarFicheiroErros(erro, escreverlog);

                    } else {
                        votacoes[pos] = votacao;
                        nvotacoes++;
                    }
                } else {
                    String erro = "Ao ler o ficheiro votações " + filevotacoes + " o deputado " + id + " não existe";
                    LogErros.guardarFicheiroErros(erro, escreverlog);
                }
            }
        }
        return nvotacoes;
    }

    /**
     * O metodo alteraDadosDeputado recebe a opção escolhida no metodo
     * menuAlterarDadosDeputado e permite ao utilizador efectuar a devida
     * alteração
     *
     * @param posição procura por id o deputado com a ajuda do metodo
     * @param alterado valida se a alteração e efetuada com sucesso
     * Utilitarios.procurarDeputados
     * @return true ou false mediande a alteração efetuada com sucesso ou não
     */
    private static boolean alteraDadosDeputado(String[][] deputados, String id) throws ParseException {
        int posicao = Utilitarios.procurarDeputados(deputados, id), opcao;
        boolean alterado = false;
        boolean nomevalido = false;
        boolean partidovalido = false;
        boolean datanascimentovalida = false;
        if (posicao != -1) {
            do {
                opcao = menuAlterarDadosDeputado(deputados[posicao]);
                switch (opcao) {
                    case 1:
                        out.format("Novo Nome: ");
                        String auxnome=in.nextLine();
                        if(Utilitarios.validaNome(auxnome)){
                            deputados[posicao][1] = auxnome;
                            alterado = true;
                        }else{
                            out.format("%n%s%n","Nome NÃO foi alterado, pois foi introduzido um nome inválido");
                        }
                        Utilitarios.continuar();
                        break;
                    case 2:
                        out.format("Novo partido: ");
                        String auxpartido=in.nextLine();
                        if(Utilitarios.validaPartido(auxpartido)){
                            deputados[posicao][2] = auxpartido;
                            alterado = true;
                        }else{
                            out.format("%n%s%n","Partido NÃO foi alterado, pois foi introduzido um nome de Partido inválido");
                        }
                        Utilitarios.continuar();
                        break;
                    case 3:
                        out.format("Nova Data de nascimento: ");
                        String auxdatanascimento=in.nextLine();
                        if(Utilitarios.validadeDataDeNascimento(auxdatanascimento)){
                            deputados[posicao][3] = auxdatanascimento;
                            alterado = true;
                        }else{
                            out.format("%n%s%n","Data de nascimento inválida ou formato inválido yyyyMMdd. Deputado não alterado");
                        }
                        Utilitarios.continuar();
                        break;
                    default:
                        if (opcao != 0) {
                            out.format("%n%s%n", "Opção Incorrecta.");
                            alterado = false;
                        } else {
                            out.format("%n%s%n", "Saiu");
                        }
                        break;
                }
            } while (opcao != 0);
        } else {
            System.out.printf("O deputado %s não foi encontrado!", id);
            alterado = false;
        }
        if (alterado == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * O metodo menuAlterarDadosDeputado apresenta diversas opções de alteração
     * de dados dos deputados
     *
     * @return opção escolhida
     */
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

    /**
     * O metodo guardarDeputados é o responsável por guardar os dados recebido
     * pore parametro do metodo lerFicheiro
     *
     * @param dadostemporarios acumula dados temporários da string dividida por
     * ";"
     * @param linhaslidas corresponde a todas as linhas lidas válidas e não
     * válidas
     * @return numero de linhas lidas com sucesso
     */
    private static int guardarDeputados(String linha, String[][] deputados, int nDeputados, Formatter escreverlog) throws FileNotFoundException, ParseException {
        String[] dadostemporarios = linha.split(";");
        boolean idvalido = false;
        boolean nomevalido = false;
        boolean partidovalido = false;
        boolean datanascimentovalida = false;
        if (dadostemporarios.length == 4) {
            String auxid = dadostemporarios[0].trim();
            String auxnome = dadostemporarios[1].trim();
            String auxpartido = dadostemporarios[2].trim();
            String auxdatanascimento = dadostemporarios[3].trim();
            int t=auxid.length();
            if (t == 5) {
                idvalido = true;
                //deputados[nDeputados][0] = id;
                //nDeputados++;
            } else {
                idvalido = false;
                String erro = "O ID: " + auxid + " presente no ficheiro deputados.txt não apresenta um formato válido!";
                LogErros.guardarFicheiroErros(erro, escreverlog);
            }
            if (Utilitarios.validaNome(auxnome)) {
                nomevalido = true;
            } else {
                nomevalido = false;
                String erro = "O nome " + auxnome + " presente no ficheiro deputados.txt não apresenta um formato válido!";
                LogErros.guardarFicheiroErros(erro, escreverlog);
            }
            if (Utilitarios.validaNome(auxpartido)) {
                partidovalido = true;
            } else {
                partidovalido = false;
                String erro = "O partido " + auxpartido + " presente no ficheiro deputados.txt não apresenta um formato válido!";
                LogErros.guardarFicheiroErros(erro, escreverlog);
            }
            if (Utilitarios.validadeDataDeNascimento(auxdatanascimento)) {
                datanascimentovalida = true;
            } else {
                datanascimentovalida = false;
                String erro = "A data de nascimento " + auxdatanascimento + " presente no ficheiro deputados.txt não é válida ou formato invalido yyyyMMdd!";
                LogErros.guardarFicheiroErros(erro, escreverlog);
            }
            if (idvalido == true && nomevalido == true && partidovalido == true && datanascimentovalida == true) {
                deputados[nDeputados][0] = auxid;
                deputados[nDeputados][1] = auxnome;
                deputados[nDeputados][2] = auxpartido;
                deputados[nDeputados][3] = auxdatanascimento;
                nDeputados++;
            }

        } else {
            String erro = "A linha " + linha + " presente no ficheiro deputados.txt é uma linha inválida!";
            LogErros.guardarFicheiroErros(erro, escreverlog);
        }
        return nDeputados;
    }

    /**
     * O metodo lerFicheiro vai receber como parametro vetor vazio ler todos os
     * dados do ficheiro deputados.txt Com a utilização do metodo
     * guardarDadosDeputado guarda os dados no vetor vazio recebido
     *
     * @param nDeputados indica o numero de deputados válidos lido
     */
    private static int lerFicheiro(String[][] deputados, Formatter escreverlog) throws FileNotFoundException, ParseException {

        int nDeputados = 0, linhaslidas = 0;
        Scanner lerfic = new Scanner(new File(FILE_DEPUTADOS));
        while (lerfic.hasNext() && nDeputados < MAX_DEPUTADOS) {
            String linha = lerfic.nextLine();
            linhaslidas++;
            // teste para verificar a linha ignorando as linhas vazias
            if (linha.length() > 0) {
                nDeputados = guardarDeputados(linha, deputados, nDeputados, escreverlog);
            } else {
                String erro = "A linha " + linhaslidas + " no ficheiro " + FILE_DEPUTADOS + " é uma linha vazia";
                LogErros.guardarFicheiroErros(erro, escreverlog);
            }
        }
        lerfic.close();
        return nDeputados;
    }
}
