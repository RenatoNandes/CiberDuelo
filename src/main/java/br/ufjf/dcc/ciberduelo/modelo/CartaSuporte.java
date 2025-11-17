package br.ufjf.dcc.ciberduelo.modelo;

public class CartaSuporte implements Carta {
        private String nome;
        private String tipo;
        private double efeito;
        private int custoEnergia;
        private String tipoEfeito;
        private String descricao;

        public CartaSuporte(String nome, double efeito, int custoEnergia, String tipoEfeito, String descricao) {
            this.nome = nome;
            this.tipo = "SUPORTE";
            this.efeito = efeito;
            this.custoEnergia = custoEnergia;
            this.tipoEfeito = tipoEfeito;
            this.descricao = descricao;
        }

        public double getEfeito() { return efeito; }

        public String getTipoEfeito() { return tipoEfeito; }

        @Override
        public String getNome() { return nome; }

        @Override
        public String getTipo() { return tipo; }

        @Override
        public int getCustoEnergia() { return custoEnergia; }

        @Override
        public String getDescricao() { return descricao; }
    }

