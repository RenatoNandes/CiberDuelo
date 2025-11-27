package br.ufjf.dcc.ciberduelo.modelo;

public interface Carta {

    String getNome();

    String getTipo(); // ataque, defesa ou suporte

    int getCustoEnergia();

    String getDescricao();
}
