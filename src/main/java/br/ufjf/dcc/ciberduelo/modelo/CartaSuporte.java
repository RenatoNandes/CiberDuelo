package br.ufjf.dcc.ciberduelo.modelo;

public class CartaSuporte implements Carta {

    private final String nome;
    private final String tipo;
    private final double efeito; // porcentagem, exemplo: 0.20 (20%)
    private final int custoEnergia;
    private final String tipoEfeito; // aumenta ataque, diminui ataque e aumenta vida
    private final String descricao;

    public CartaSuporte(String nome, double efeito, int custoEnergia, String tipoEfeito, String descricao) {
        this.nome = nome;
        this.tipo = "SUPORTE";
        this.efeito = efeito;
        this.custoEnergia = custoEnergia;
        this.tipoEfeito = tipoEfeito;
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

    public double getEfeito() {
        return efeito;
    }

    @Override
    public int getCustoEnergia() {
        return custoEnergia;
    }

    public String getTipoEfeito() {
        return tipoEfeito;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}

