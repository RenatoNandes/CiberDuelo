package br.ufjf.dcc.ciberduelo.modelo;

public class CartaDefesa implements Carta{
    private String nome;
    private String tipo;
    private int poderDefesa;
    private int custoEnergia;
    private String descricao;

    public CartaDefesa(String nome, int poderDefesa, int custoEnergia, String descricao) {
        this.nome = nome;
        this.tipo = "DEFESA";
        this.poderDefesa = poderDefesa;
        this.custoEnergia = custoEnergia;
        this.descricao = descricao;
    }

    public int getPoderDefesa() {
        return poderDefesa;
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public String getTipo() { return tipo; }

    @Override
    public int getCustoEnergia() { return custoEnergia; }

    @Override
    public String getDescricao() { return descricao; }
}
