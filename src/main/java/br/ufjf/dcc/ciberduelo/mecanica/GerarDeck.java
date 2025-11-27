package br.ufjf.dcc.ciberduelo.mecanica;

import br.ufjf.dcc.ciberduelo.modelo.Carta;
import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;
import br.ufjf.dcc.ciberduelo.io.LeituraEntradas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerarDeck {

    private final List<CartaAtaque> cartasAtaque;
    private final List<CartaDefesa> cartasDefesa;
    private final List<CartaSuporte> cartasSuporte;

    private final Scanner scanner = new Scanner(System.in);
    private final LeituraEntradas leitor = new LeituraEntradas(scanner);

    public GerarDeck(List<CartaAtaque> atk, List<CartaDefesa> def, List<CartaSuporte> sup) {
        this.cartasAtaque = atk;
        this.cartasDefesa = def;
        this.cartasSuporte = sup;
    }

    private <T extends Carta> List<T> selecionarAleatorio(List<T> lista, int qtd) {

        List<T> copia = new ArrayList<>(lista);
        List<T> selecionadas = new ArrayList<>();
        for (int i = 0; i < qtd && !copia.isEmpty(); i++) {
            int idx = (int) (Math.random() * copia.size());
            selecionadas.add(copia.remove(idx));
        }
        return selecionadas;
    }

    private List<Carta> montarDeckAleatorio() {

        List<Carta> deck = new ArrayList<>();
        deck.addAll(selecionarAleatorio(cartasAtaque, 4));
        deck.addAll(selecionarAleatorio(cartasDefesa, 4));
        deck.addAll(selecionarAleatorio(cartasSuporte, 2));
        return deck;
    }

    private List<Carta> montarDeckManual() {

        // usa cópias locais para permitir remover cartas escolhidas
        List<CartaAtaque> atk = new ArrayList<>(cartasAtaque);
        List<CartaDefesa> def = new ArrayList<>(cartasDefesa);
        List<CartaSuporte> sup = new ArrayList<>(cartasSuporte);

        List<Carta> deck = new ArrayList<>();
        System.out.println("\nSelecione 4 cartas de ATAQUE:");
        escolherCartasComRemocao(deck, atk, 4);
        System.out.println("\nSelecione 4 cartas de DEFESA:");
        escolherCartasComRemocao(deck, def, 4);
        System.out.println("\nSelecione 2 cartas de SUPORTE:");
        escolherCartasComRemocao(deck, sup, 2);

        return deck;
    }

    private <T extends Carta> void escolherCartasComRemocao(List<Carta> destino, List<T> origem, int qtd) {

        for (int adicionados = 0; adicionados < qtd; ) {
            System.out.println("\nCartas disponíveis: ");
            for (int i = 0; i < origem.size(); i++) {
                System.out.println((i + 1) + " - " + origem.get(i).getNome());
            }
            System.out.print("Escolha (1 a " + origem.size() + "): ");
            int escolha = leitor.lerOpcao(1, origem.size());
            T carta = origem.remove(escolha - 1);
            destino.add(carta);
            adicionados++;
            System.out.println("Adicionada: " + carta.getNome());
        }
    }

    public List<Carta> gerarDeckManual() {
        return montarDeckManual();
    }

    public List<Carta> gerarDeckAleatorio() {
        return montarDeckAleatorio();
    }
}