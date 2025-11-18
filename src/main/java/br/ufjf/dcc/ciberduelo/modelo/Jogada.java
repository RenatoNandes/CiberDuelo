package br.ufjf.dcc.ciberduelo.modelo;

import java.util.List;

public class Jogada {

    private final int turno;
    private final String jogadorId;
    private final String adversarioId;
    private final List<Carta> cartasJogadas;
    private final boolean passou;
    private final boolean desistiu;
    private final int vidaAntesJogador;
    private final int vidaDepoisJogador;
    private final int energiaAntesJogador;
    private final int energiaDepoisJogador;
    private final int vidaAntesAdversario;
    private final int vidaDepoisAdversario;
    private final int energiaAntesAdversario;
    private final int energiaDepoisAdversario;

    private Jogada(Builder builder) { // Construtor privado para encapsular e somente o Builder conseguir chamar e alterar

        this.turno = builder.turno;
        this.jogadorId = builder.jogadorId;
        this.adversarioId = builder.adversarioId;
        this.cartasJogadas = builder.cartasJogadas;
        this.passou = builder.passou;
        this.desistiu = builder.desistiu;
        this.vidaAntesJogador = builder.vidaAntesJogador;
        this.vidaDepoisJogador = builder.vidaDepoisJogador;
        this.energiaAntesJogador = builder.energiaAntesJogador;
        this.energiaDepoisJogador = builder.energiaDepoisJogador;
        this.vidaAntesAdversario = builder.vidaAntesAdversario;
        this.vidaDepoisAdversario = builder.vidaDepoisAdversario;
        this.energiaAntesAdversario = builder.energiaAntesAdversario;
        this.energiaDepoisAdversario = builder.energiaDepoisAdversario;
    }

    public static class Builder { // Classe Builder para montar a jogada passo a passo

        private int turno;
        private String jogadorId;
        private String adversarioId;
        private List<Carta> cartasJogadas;
        private boolean passou;
        private boolean desistiu;
        private int vidaAntesJogador;
        private int vidaDepoisJogador;
        private int energiaAntesJogador;
        private int energiaDepoisJogador;
        private int vidaAntesAdversario;
        private int vidaDepoisAdversario;
        private int energiaAntesAdversario;
        private int energiaDepoisAdversario;

        // Métodos da classe Builder

        public Builder() {
        }

        public Builder turno(int turno) {
            this.turno = turno;
            return this;
        }

        public Builder jogadorId(String jogadorId) {
            this.jogadorId = jogadorId;
            return this;
        }

        public Builder adversarioId(String adversarioId) {
            this.adversarioId = adversarioId;
            return this;
        }

        public Builder cartasJogadas(List<Carta> cartasJogadas) {
            this.cartasJogadas = cartasJogadas;
            return this;
        }

        public Builder passou(boolean passou) {
            this.passou = passou;
            return this;
        }

        public Builder desistiu(boolean desistiu) {
            this.desistiu = desistiu;
            return this;
        }

        public Builder vidaAntesJogador(int vida) {
            this.vidaAntesJogador = vida;
            return this;
        }

        public Builder vidaDepoisJogador(int vida) {
            this.vidaDepoisJogador = vida;
            return this;
        }

        public Builder energiaAntesJogador(int energia) {
            this.energiaAntesJogador = energia;
            return this;
        }

        public Builder energiaDepoisJogador(int energia) {
            this.energiaDepoisJogador = energia;
            return this;
        }

        public Builder vidaAntesAdversario(int vida) {
            this.vidaAntesAdversario = vida;
            return this;
        }

        public Builder vidaDepoisAdversario(int vida) {
            this.vidaDepoisAdversario = vida;
            return this;
        }

        public Builder energiaAntesAdversario(int energia) {
            this.energiaAntesAdversario = energia;
            return this;
        }

        public Builder energiaDepoisAdversario(int energia) {
            this.energiaDepoisAdversario = energia;
            return this;
        }

        public Jogada build() {
            return new Jogada(this);
        }
    }

}
