package br.ufjf.dcc.ciberduelo.mecanica;

import br.ufjf.dcc.ciberduelo.io.Replay;
import br.ufjf.dcc.ciberduelo.io.LeituraEntradas;
import br.ufjf.dcc.ciberduelo.modelo.Carta;
import br.ufjf.dcc.ciberduelo.modelo.Hacker;
import br.ufjf.dcc.ciberduelo.modelo.Jogada;
import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Duelo {

    private final Hacker jogador;
    private final Hacker oponente; // Pode ser tanto um jogador 2 quanto um BOT

    private final Scanner scanner = new Scanner(System.in);
    private final LeituraEntradas leitor = new LeituraEntradas(scanner);

    private final AplicarEfeitos aplicador = new AplicarEfeitos();

    private final List<Jogada> replay = new ArrayList<>();

    private final Map<Hacker, List<Carta>> decksIniciais = new HashMap<>();

    public Duelo(Hacker jogador, Hacker oponente) {
        this.jogador = jogador;
        this.oponente = oponente;

        decksIniciais.put(jogador, new ArrayList<>(jogador.getDeckInicial()));
        decksIniciais.put(oponente, new ArrayList<>(oponente.getDeckInicial()));
    }

    private List<String> nomeCartas(List<Carta> cartas) { // Converte lista de cartas em lista de nomes
        return cartas.stream().map(Carta::getNome).toList();
    }

    private void desistiuPartida(int turno, Hacker desistente, Hacker vencedor) {
        System.out.println("\n" + LeituraEntradas.converteMaiusculo(desistente.getNome()) + " desistiu da partida!");
        System.out.println("Vitória de " + LeituraEntradas.converteMaiusculo(vencedor.getNome()) + "!");

        replay.add(new Jogada(turno, vencedor.getNome(), vencedor.getIdentificador(), vencedor.getVida(), vencedor.getEnergia(), List.of(), true, false));
        replay.add(new Jogada(turno, desistente.getNome(), desistente.getIdentificador(), desistente.getVida(), desistente.getEnergia(), List.of(), true, true));
        salvarReplayOpcional(vencedor, desistente);
    }

    private List<Carta> turnoHumano(Hacker atual, Hacker oponente) {
        List<Carta> cartasUsadas = new ArrayList<>();
        int energiaDisponivel = atual.getEnergia();
        boolean terminou = false;

        while (!terminou) {
            System.out.println("Cartas no deck atual: \n");
            List<Carta> deck = atual.getDeckAtual();
            for (int i = 0; i < deck.size(); i++) {
                Carta c = deck.get(i);

                System.out.print((i + 1) + " - " + c.getNome() + " (" + c.getTipo() + ") - Custo: " + c.getCustoEnergia());

                switch (c) {
                    case CartaAtaque ca -> System.out.print(" - Ataque: " + ca.getPoderAtaque());
                    case CartaDefesa cd -> System.out.print(" - Defesa: " + cd.getPoderDefesa());
                    case CartaSuporte cs -> {
                        String tipo = cs.getTipoEfeito().toUpperCase();

                        if (tipo.contains("ATAQUE")) {
                            int porcentagem = (int) (cs.getEfeito() * 100);
                            System.out.print(" - Efeito: " + porcentagem + "% (" + cs.getTipoEfeito() + ")");
                        } else if (tipo.contains("VIDA")) {
                            int valor = (int) cs.getEfeito();
                            System.out.print(" - Efeito: +" + valor + " de Vida (" + cs.getTipoEfeito() + ")");
                        }
                    }
                    default -> {

                    }
                }
                System.out.println(" - Descrição: " + c.getDescricao());
            }

            System.out.println("\nOpções: ");
            System.out.println("1: Jogar carta");
            System.out.println("2: Passar sua vez");
            System.out.println("3: Desistir da partida");
            System.out.print("\nEscolha: ");
            int op = leitor.lerOpcao(1, 3);
            if (op == 1) {
                if (deck.isEmpty()) {
                    System.out.println("Sem cartas.");
                    continue;
                }
                System.out.print("Número da carta: ");
                int idx = leitor.lerOpcao(1, deck.size());
                Carta escolhida = deck.get(idx - 1);
                if (escolhida.getCustoEnergia() > energiaDisponivel) {
                    System.out.println("Energia insuficiente.");
                    continue;
                }
                cartasUsadas.add(escolhida);
                deck.remove(escolhida);
                energiaDisponivel -= escolhida.getCustoEnergia();
                System.out.println("\nEnergia restante: " + energiaDisponivel);
                if (energiaDisponivel == 0) terminou = true;
            } else if (op == 2) {
                terminou = true;
            } else {
                return null; // desistiu
            }
        }
        // atualiza energia gasta
        return cartasUsadas;
    }

    private List<Carta> turnoBot(Hacker bot) {
        List<Carta> usadas = new ArrayList<>();
        List<Carta> deck = new ArrayList<>(bot.getDeckAtual());
        Collections.shuffle(deck);

        int energia = bot.getEnergia();

        for (Carta c : deck) {
            if (c.getCustoEnergia() <= energia) {
                usadas.add(c);
                energia -= c.getCustoEnergia();
            }

            // Chance do bot pular a vez (Balanceamento)
            if (Math.random() < 0.30) {
                break;
            }
        }
        return usadas;
    }

    private void consolidarTurno(List<Carta> cartasJogador, List<Carta> cartasOponente) {
        AplicarEfeitos.Resultado rJ = aplicador.processar(cartasJogador);
        AplicarEfeitos.Resultado rO = aplicador.processar(cartasOponente);

        // aplica suportes de vida
        jogador.ganharVida(rJ.aumentoVida);
        oponente.ganharVida(rO.aumentoVida);

        // calcula ataque com bônus/redução
        int ataqueJ = (int) (rJ.ataque + rJ.ataque * rJ.aumentoAtaque);
        ataqueJ -= (int) (ataqueJ * rJ.diminuicaoAtaque);

        int ataqueO = (int) (rO.ataque + rO.ataque * rO.aumentoAtaque);
        ataqueO -= (int) (ataqueO * rO.diminuicaoAtaque);

        int danoAoOponente = 0;
        int danoAoJogador = 0;

        if (ataqueJ > 0 && ataqueO == 0) {
            danoAoOponente = ataqueJ - rO.defesa;
            if (danoAoOponente < 0) danoAoOponente = 0;
        } else if (ataqueO > 0 && ataqueJ == 0) {
            danoAoJogador = ataqueO - rJ.defesa;
            if (danoAoJogador < 0) danoAoJogador = 0;
        } else if (ataqueJ > 0 && ataqueO > 0) {
            danoAoJogador = ataqueO;
            danoAoOponente = ataqueJ;
        }

        jogador.receberDano(danoAoJogador);
        oponente.receberDano(danoAoOponente);

        jogador.aplicarArredondamentoVida();
        oponente.aplicarArredondamentoVida();

        // energia: usa energia baseada no custo total das cartas jogadas
        int energiaGastaJ = cartasJogador.stream().mapToInt(Carta::getCustoEnergia).sum();
        int energiaGastaO = cartasOponente.stream().mapToInt(Carta::getCustoEnergia).sum();
        jogador.ajustarEnergiaDepoisDoTurno(energiaGastaJ);
        oponente.ajustarEnergiaDepoisDoTurno(energiaGastaO);

        // remove cartas usadas
        jogador.removerCartasDoDeck(cartasJogador);
        oponente.removerCartasDoDeck(cartasOponente);

        // resumo
        System.out.println("Resumo do turno: ");
        System.out.println(LeituraEntradas.converteMaiusculo(jogador.getNome()) + " sofreu: " + danoAoJogador + " | Vida: " + jogador.getVida());
        System.out.println(LeituraEntradas.converteMaiusculo(oponente.getNome()) + " sofreu: " + danoAoOponente + " | Vida: " + oponente.getVida());
    }

    private void status() {
        System.out.println("Nome: " + LeituraEntradas.converteMaiusculo(jogador.getNome()) + " - ID: " + jogador.getIdentificador());
        System.out.println("Vida: " + jogador.getVida() + " \nEnergia: " + jogador.getEnergia());
        System.out.println("\nNome: " + LeituraEntradas.converteMaiusculo(oponente.getNome()) + " - ID: " + oponente.getIdentificador());
        System.out.println("Vida: " + oponente.getVida() + " \nEnergia: " + oponente.getEnergia() + "\n");
    }

    private void salvarReplayOpcional(Hacker vencedor, Hacker perdedor) {

        System.out.println("\nDeseja registrar o replay da partida?");
        System.out.println("\nOpções: ");
        System.out.println("0 - Não");
        System.out.println("1 - Sim");
        System.out.print("\nEscolha: ");

        int escolha = leitor.lerOpcao(0, 1);

        if (escolha == 1) {
            Replay r = new Replay("replay.txt");
            r.salvarReplay(decksIniciais, replay, vencedor, perdedor);
        } else {
            System.out.println("O replay não será salvo.");
        }
    }

    public void iniciar() {
        int turno = 1;

        while (jogador.estaVivo() && oponente.estaVivo()) {
            System.out.println("\n======= TURNO " + turno + " =======\n");
            status();
            System.out.print("======================");
            System.out.println("\n");

            // turno jogador (humano)
            System.out.println("--- Início do turno de " + LeituraEntradas.converteMaiusculo(jogador.getNome()) + " ---\n");

            List<Carta> cartasJogador = turnoHumano(jogador, oponente);
            if (cartasJogador == null) { // Jogador 1 desistiu
                desistiuPartida(turno, jogador, oponente);
                return;
            }

            System.out.println("--- Fim do turno de " + LeituraEntradas.converteMaiusculo(jogador.getNome()) + " ---\n");
            System.out.println("--- Início do turno de " + LeituraEntradas.converteMaiusculo(oponente.getNome()) + " ---\n");

            // turno bot/oponente
            List<Carta> cartasOponente;
            if (oponente.getNome().equals("BOT")) {
                cartasOponente = turnoBot(oponente);
            } else {
                cartasOponente = turnoHumano(oponente, jogador);
                if (cartasOponente == null) { // Jogador 2 desistiu
                    desistiuPartida(turno, oponente, jogador);
                    return;
                }
            }

            System.out.println("--- Fim do turno de " + LeituraEntradas.converteMaiusculo(oponente.getNome()) + " ---\n");

            // aplica efeitos e consolida
            consolidarTurno(cartasJogador, cartasOponente);

            // registra jogada no replay
            replay.add(new Jogada(turno, jogador.getNome(), jogador.getIdentificador(), jogador.getVida(), jogador.getEnergia(), nomeCartas(cartasJogador), cartasJogador.isEmpty(), false));
            replay.add(new Jogada(turno, oponente.getNome(), oponente.getIdentificador(), oponente.getVida(), oponente.getEnergia(), nomeCartas(cartasOponente), cartasOponente.isEmpty(), false));

            turno++;
            System.out.println();
        }

        // fim de jogo e salvar replay
        if (!jogador.estaVivo()) {
            System.out.println("Vitória de " + oponente.getNome() + "!");
            salvarReplayOpcional(jogador, oponente);
        } else if (!oponente.estaVivo()) {
            System.out.println("Vitória de " + jogador.getNome() + "!");
            salvarReplayOpcional(jogador, oponente);
        } else if (!jogador.estaVivo() && !oponente.estaVivo()) {
            System.out.println("Empate entre os hackers " + jogador.getNome() + " e " + oponente.getNome() + "!");
        }
    }
}