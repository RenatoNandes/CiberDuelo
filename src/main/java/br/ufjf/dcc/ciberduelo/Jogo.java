package br.ufjf.dcc.ciberduelo;

import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jogo{
    private static void exibirBoasVindas() {
        System.out.println("===========================================");
        System.out.println("       CIBERDUELO: BATALHA DE HACKERS     ");
        System.out.println("===========================================");
        System.out.println();
    }

    public void menuInicial(){
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;  // valor inválido inicial para entrar no loop

        while (true) {
            System.out.println("===== MENU INICIAL =====");
            System.out.println("1 - Iniciar partida");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // verifica se o usuário realmente digitou um número
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // limpa entrada
                continue; // volta para o início do while
            }

            opcao = scanner.nextInt();

            if (opcao == 1) {
                configuraJogadorEDeck();
                loopPartida();
                break; // sai do menu após iniciar o jogo
            }
            else if (opcao == 0) {
                System.out.println("Saindo...");
                System.exit(0);
            }
            else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

    }

    public void configuraJogadorEDeck(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        scanner.nextLine();
        String nomeJogador = scanner.nextLine();

        this.jogador = new Hacker(nomeJogador, "P1"); // cria o jogador

        this.bot = new Hacker("BOT", "CPU"); // cria o bot

        // Monta deck do jogador (aleatório por enquanto)
        List<Carta> deckJogador = new ArrayList<>();
        deckJogador.addAll(selecionarAleatorio(cartasAtaque, 4));
        deckJogador.addAll(selecionarAleatorio(cartasDefesa, 4));
        deckJogador.addAll(selecionarAleatorio(cartasSuporte, 2));
        jogador.setDeckInicial(deckJogador);

        // Monta deck do bot
        List<Carta> deckBot = new ArrayList<>();
        deckBot.addAll(selecionarAleatorio(cartasAtaque, 4));
        deckBot.addAll(selecionarAleatorio(cartasDefesa, 4));
        deckBot.addAll(selecionarAleatorio(cartasSuporte, 2));
        bot.setDeckInicial(deckBot);


    }

    private void loopPartida(){

    }

    public void encerrarPartida(){

    }

    private static void exibirResumoCartas(List<CartaAtaque> ataques,
                                           List<CartaDefesa> defesas,
                                           List<CartaSuporte> suportes) {
        System.out.println("Cartas carregadas:"); //mostra a quantidade de ataques/defesas/suportes
        System.out.println("- Ataque: " + ataques.size());
        System.out.println("- Defesa: " + defesas.size());
        System.out.println("- Suporte: " + suportes.size());
        System.out.println();

        // mostrar um exemplo de cada tipo
        if (!ataques.isEmpty()) {
            System.out.println("Exemplo ATAQUE: " + ataques.get(0).getNome());
        }
        if (!defesas.isEmpty()) {
            System.out.println("Exemplo DEFESA: " + defesas.get(0).getNome());
        }
        if (!suportes.isEmpty()) {
            System.out.println("Exemplo SUPORTE: " + suportes.get(0).getNome());
        }

        System.out.println();
    }

    // Disclaimer de algumas sintaxes:
    // FileReader: abre o arquivo físico (ex: ataque.csv)
    // BufferedReader: lê o arquivo linha por linha (rápido e eficiente)

    // String[] partes = linha.split(",") -> divide a linha em partes, corta a linha no separador ","
    // Exemplo:
    // partes[0] = "Exploit Zero-Day"
    // partes[1] = "ATAQUE"
    // partes[2] = "50"
    // partes[3] = "3"
    // partes[4] = "Ataque poderoso"

    // após isso, lê cada informação e converte pro tipo certo
    // Exemplo:
    // nome = partes[0].trim();
    // int poder = Integer.parseInt(partes[2].trim());
    // int custo = Integer.parseInt(partes[3].trim());
    // String descricao = partes[4].trim();

    // por fim, cria o objeto Carta do tipo determinado e adiciona a carta na lista

    private static List<CartaAtaque> carregarCartasAtaque(String caminhoArquivo) {
        List<CartaAtaque> cartas = new ArrayList<>(); // pega o csv e transforma em lista

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) { //abre o arquivo para leitura
            String linha = br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) { // lê as demais linhas em um loop
                String[] partes = linha.split(",");

                String nome = partes[0].trim();
                int poder = Integer.parseInt(partes[2].trim());
                int custo = Integer.parseInt(partes[3].trim());
                String descricao = partes[4].trim();

                CartaAtaque carta = new CartaAtaque(nome, poder, custo, descricao);
                cartas.add(carta);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de ataque: " + e.getMessage());
        }

        return cartas;
    }

    private static List<CartaDefesa> carregarCartasDefesa(String caminhoArquivo) {
        List<CartaDefesa> cartas = new ArrayList<>(); // pega o csv e transforma em lista

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) { //abre o arquivo para leitura
            String linha = br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {  // lê as demais linhas em um loop
                String[] partes = linha.split(",");

                String nome = partes[0].trim();
                int poder = Integer.parseInt(partes[2].trim());
                int custo = Integer.parseInt(partes[3].trim());
                String descricao = partes[4].trim();

                CartaDefesa carta = new CartaDefesa(nome, poder, custo, descricao);
                cartas.add(carta);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de defesa: " + e.getMessage());
        }

        return cartas;
    }

    private static List<CartaSuporte> carregarCartasSuporte(String caminhoArquivo) {
        List<CartaSuporte> cartas = new ArrayList<>(); // pega o csv e transforma em lista

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) { //abre o arquivo para leitura
            String linha = br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {  // lê as demais linhas em um loop
                String[] partes = linha.split(",");

                String nome = partes[0].trim();
                double efeito = Double.parseDouble(partes[2].trim());
                int custo = Integer.parseInt(partes[3].trim());
                String tipoEfeito = partes[4].trim();
                String descricao = partes.length > 5 ? partes[5].trim() : "";

                CartaSuporte carta = new CartaSuporte(nome, efeito, custo, tipoEfeito, descricao);
                cartas.add(carta);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de suporte: " + e.getMessage()); // msg de erro
        }

        return cartas;
    }



    public static void main(String[] args) {
            exibirBoasVindas();

            // 1) Carregar cartas
            List<CartaAtaque> cartasAtaque = carregarCartasAtaque("ataque.csv");
            List<CartaDefesa> cartasDefesa = carregarCartasDefesa("defesa.csv");
            List<CartaSuporte> cartasSuporte = carregarCartasSuporte("suporte.csv");

            // 2) Mostrar um resumo
            exibirResumoCartas(cartasAtaque, cartasDefesa, cartasSuporte);

        }
}
