package br.ufjf.dcc.ciberduelo.modelo;

import java.util.List;

public class Jogada {

    private final int turno;
    private final String nomeHacker;
    private final String idHacker;
    private final int vida;
    private final int energia;
    private final List<String> cartasJogadas;
    private final boolean passou;
    private final boolean desistiu;

    public Jogada(int turno, String nomeHacker, String idHacker, int vida, int energia, List<String> cartasJogadas, boolean passou, boolean desistiu) {
        this.turno = turno;
        this.nomeHacker = nomeHacker;
        this.idHacker = idHacker;
        this.vida = vida;
        this.energia = energia;
        this.cartasJogadas = cartasJogadas;
        this.passou = passou;
        this.desistiu = desistiu;
    }

    public int getTurno() {
        return turno;
    }

    public String getNomeHacker() {
        return nomeHacker;
    }

    public String getIdHacker() {
        return idHacker;
    }

    public int getVida() {
        return vida;
    }

    public int getEnergia() {
        return energia;
    }

    public List<String> getCartasJogadas() {
        return cartasJogadas;
    }

    public boolean passou() {
        return passou;
    }

    public boolean desistiu() {
        return desistiu;
    }
}