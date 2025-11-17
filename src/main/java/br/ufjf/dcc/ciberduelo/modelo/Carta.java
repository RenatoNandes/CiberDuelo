package br.ufjf.dcc.ciberduelo.modelo;

public interface Carta {

    String getNome();

    String getTipo(); // "ATAQUE", "DEFESA" ou "SUPORTE"

    int getCustoEnergia();

    String getDescricao();
}
