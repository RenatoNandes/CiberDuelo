package br.ufjf.dcc.ciberduelo.mecanica;

import br.ufjf.dcc.ciberduelo.io.LeituraEntradas;
import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;
import br.ufjf.dcc.ciberduelo.modelo.Hacker;

import java.util.List;
import java.util.Scanner;

public class ConfiguraPartida {

    public static class DueloConfig {
        public Hacker jogador;
        public Hacker oponente;
    }

    private final List<CartaAtaque> cartasAtaque;
    private final List<CartaDefesa> cartasDefesa;
    private final List<CartaSuporte> cartasSuporte;
    private final Scanner scanner = new Scanner(System.in);
    private final LeituraEntradas leitor = new LeituraEntradas(scanner);

    public ConfiguraPartida(List<CartaAtaque> atk, List<CartaDefesa> def, List<CartaSuporte> sup) {
        this.cartasAtaque = atk;
        this.cartasDefesa = def;
        this.cartasSuporte = sup;
    }

    public DueloConfig configurar() {
        DueloConfig cfg = new DueloConfig();

        System.out.println("=== INICIALIZANDO PARTIDA ===");
        System.out.print("Nome do jogador(a): ");
        String nome1 = scanner.nextLine();
        System.out.print("ID do jogador(a): ");
        String id1 = scanner.nextLine();
        Hacker jogador1 = new Hacker(nome1, id1);

        GerarDeck gerador = new GerarDeck(cartasAtaque, cartasDefesa, cartasSuporte);

        System.out.println("Escolha o modo de jogo: ");
        System.out.println("0: Humano x Humano (PvP)");
        System.out.println("1: Humano x BOT (PvE)");
        System.out.print("Opção escolhida: ");
        int modo = leitor.lerOpcao(0, 1);

        if (modo == 0) {
            // PvP
            System.out.print("Nome do jogador 2: ");
            String nome2 = scanner.nextLine();
            System.out.print("ID do jogador 2: ");
            String id2 = scanner.nextLine();
            Hacker jogador2 = new Hacker(nome2, id2);

            String[] nomes = {nome1, nome2};
            Hacker[] jogadores = {jogador1, jogador2};

            // escolha dos decks
            for (int i = 0; i < 2; i++) {

                System.out.println(LeituraEntradas.converteMaiusculo(nomes[i]) + ", como gostaria de montar o seu Deck?");
                System.out.println("Escolha uma das opções: ");
                System.out.println("0: Montagem de deck aleatoriamente");
                System.out.println("1: Montagem de deck manualmente");
                System.out.print("Opção escolhida: ");

                if (leitor.lerOpcao(0, 1) == 1) {
                    jogadores[i].setDeckInicial(gerador.gerarDeckManual());
                } else {
                    jogadores[i].setDeckInicial(gerador.gerarDeckAleatorio());
                }
            }

            cfg.jogador = jogador1;
            cfg.oponente = jogador2;

        } else {
            // PvE
            System.out.println(LeituraEntradas.converteMaiusculo(nome1) + ", como gostaria de montar o seu Deck?");
            System.out.println("Escolha uma das opções: ");
            System.out.println("0: Montagem de deck aleatoriamente");
            System.out.println("1: Montagem de deck manualmente");
            System.out.print("Opção escolhida: ");

            if (leitor.lerOpcao(0, 1) == 1) {
                jogador1.setDeckInicial(gerador.gerarDeckManual());
            } else {
                jogador1.setDeckInicial(gerador.gerarDeckAleatorio());
            }

            Hacker bot = new Hacker("BOT", "202565001");
            bot.setDeckInicial(gerador.gerarDeckAleatorio());
            cfg.jogador = jogador1;
            cfg.oponente = bot;
        }
        return cfg;
    }
}