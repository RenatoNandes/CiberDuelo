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

}
