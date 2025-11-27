package br.ufjf.dcc.ciberduelo.mecanica;

import br.ufjf.dcc.ciberduelo.modelo.Carta;
import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;

import java.util.List;

public class AplicarEfeitos {

    public static class Resultado {
        public int ataque;
        public int defesa;
        public int aumentoVida;
        public double aumentoAtaque;
        public double diminuicaoAtaque;
    }

    // processa listas de cartas e retorna valores agregados
    public Resultado processar(List<Carta> cartas) {
        Resultado r = new Resultado();
        r.ataque = 0;
        r.defesa = 0;
        r.aumentoVida = 0;
        r.aumentoAtaque = 0;
        r.diminuicaoAtaque = 0;

        for (Carta c : cartas) {
            if (c instanceof CartaAtaque) {
                r.ataque += ((CartaAtaque) c).getPoderAtaque();
            } else if (c instanceof CartaDefesa) {
                r.defesa += ((CartaDefesa) c).getPoderDefesa();
            } else if (c instanceof CartaSuporte) {
                CartaSuporte cs = ((CartaSuporte) c);
                String tipo = cs.getTipoEfeito().toUpperCase();
                if (tipo.equals("AUMENTA_ATAQUE")) r.aumentoAtaque += cs.getEfeito();
                else if (tipo.equals("DIMINUI_ATAQUE")) r.diminuicaoAtaque += cs.getEfeito();
                else if (tipo.equals("AUMENTA_VIDA")) r.aumentoVida += (int) cs.getEfeito();
            }
        }
        return r;
    }
}