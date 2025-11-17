package br.ufjf.dcc.ciberduelo.modelo;

public class CartaAtaque implements Carta {

    private String nome;
    private String tipo;
    private int poderAtaque;
    private int custoEnergia;
    private String descricao;

    public CartaAtaque(String nome, int poderAtaque, int custoEnergia, String descricao) {
        this.nome = nome;
        this.tipo = "ATAQUE";
        this.poderAtaque = poderAtaque;
        this.custoEnergia = custoEnergia;
        this.descricao = descricao;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return tipo;
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