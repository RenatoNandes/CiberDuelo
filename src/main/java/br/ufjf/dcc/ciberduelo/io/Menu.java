package br.ufjf.dcc.ciberduelo.io;

import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;
import br.ufjf.dcc.ciberduelo.mecanica.ConfiguraPartida;
import br.ufjf.dcc.ciberduelo.mecanica.Duelo;

import java.util.List;

public class Menu {

    public void iniciar() {
        System.out.println("====================================");
        System.out.println("   Cyber Duel: Guerra de Hackers   ");
        System.out.println("====================================");
        System.out.println("Em um futuro próximo, as guerras deixaram os campos físicos e passaram a ser\n" +
                "travadas no ciberespaço. Megacorporações disputam poder por meio de ataques\n" +
                "silenciosos, enquanto os hackers mais habilidosos do planeta se enfrentam em\n" +
                "duelos estratégicos, usando cartas digitais baseadas em NFTs como armas de\n" +
                "combate.\n");
        System.out.println("Carregando cartas para o jogo...");
        CarregarCartas carrega = new CarregarCartas();
        List<CartaAtaque> atks = carrega.carregarAtaques("ataque.csv");
        List<CartaDefesa> defs = carrega.carregarDefesas("defesa.csv");
        List<CartaSuporte> sups = carrega.carregarSuportes("suporte.csv");

        System.out.println("Cartas carregadas: " + atks.size() + " / " + defs.size() + " / " + sups.size() + "\n");

        ConfiguraPartida config = new ConfiguraPartida(atks, defs, sups);
        ConfiguraPartida.DueloConfig dc = config.configurar();

        Duelo duelo = new Duelo(dc.jogador, dc.oponente);
        duelo.iniciar();
    }
}