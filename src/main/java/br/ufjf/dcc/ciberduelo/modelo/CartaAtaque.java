package br.ufjf.dcc.ciberduelo.modelo;

public class CartaAtaque implements Carta {

    private final String nome;
    private final String tipo;
    private final int poderAtaque;
    private final int custoEnergia;
    private final String descricao;

    public CartaAtaque(String nome, int poderAtaque, int custoEnergia, String descricao) {
        this.nome = nome;
        this.tipo = "ATAQUE";
        this.poderAtaque = poderAtaque;
        this.custoEnergia = custoEnergia;
        this.descricao = descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    @Override
    public int getCustoEnergia() {
        return custoEnergia;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}