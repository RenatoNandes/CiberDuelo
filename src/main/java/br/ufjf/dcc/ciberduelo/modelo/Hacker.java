package br.ufjf.dcc.ciberduelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Hacker {
    private final String nome;
    private final String identificador;
    private int vida;
    private int energia;
    private List<Carta> deckInicial;
    private List<Carta> deckAtual;

    public Hacker(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
        this.vida = 100; // vida inicial
        this.energia = 10; // energia inicial

        // guarda uma cópia do deck inicial e cria o deck atual
        this.deckInicial = new ArrayList<>();
        this.deckAtual = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getVida() {
        return vida;
    }

    public int getEnergia() {
        return energia;
    }

    public List<Carta> getDeckInicial() {
        return deckInicial;
    }

    public List<Carta> getDeckAtual() {
        return deckAtual;
    }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, 100));
    }

    public void setEnergia(int energia) {
        this.energia = Math.max(0, Math.min(energia, 10));
    }

    public void setDeckInicial(List<Carta> deck) {
        this.deckInicial = new ArrayList<>(deck);
        this.deckAtual = new ArrayList<>(deck);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int dano) {
        if (dano <= 0) {
            return; // se o dano for negativo ou zero, não muda nada
        }
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    public void ganharVida(int quantidade) {
        if (quantidade <= 0) {
            return;
        }
        vida += quantidade;
    }

    public void ajustarEnergiaDepoisDoTurno(int energiaUsada) {
        int energiaFinal = 1 + (energia - energiaUsada);

        if (energiaFinal > 10) {
            energiaFinal = 10;
        }
        if (energiaFinal < 0) {
            energiaFinal = 0;
        }

        this.energia = energiaFinal;
    }

    public void aplicarArredondamentoVida() {
        // limita a 100 se passou
        if (vida > 100) {
            vida = 100;
        }

        int resto = vida % 10; // pega o resto da vida, ex: se 30, resto é 3

        // arredondamento, se < 5 vai arredondar pra baixo e se maior ou igual arredonda pra cima
        if (resto < 5) {
            vida = vida - resto;          // arredonda pra baixo
        } else {
            vida = vida + (10 - resto);   // arredonda pra cima
        }

        if (vida < 0) {
            vida = 0;
        }
    }

    public void removerCartasDoDeck(List<Carta> usadas) {
        deckAtual.removeAll(usadas);

        if (deckAtual.isEmpty()) {
            recarregarDeck();
        }
    }

    private void recarregarDeck() {
        deckAtual = new ArrayList<>(deckInicial);
    }
}