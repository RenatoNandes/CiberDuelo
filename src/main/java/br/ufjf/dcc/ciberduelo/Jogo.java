package br.ufjf.dcc.ciberduelo;

import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jogo {
public class Jogo{
    private static void exibirBoasVindas() {
        System.out.println("===========================================");
        System.out.println("        CIBERDUELO: BATALHA DE HACKERS     ");
        System.out.println("===========================================");
        System.out.println();
    }

    private static void exibirResumoCartas(List<CartaAtaque> ataques,
                                           List<CartaDefesa> defesas,
                                           List<CartaSuporte> suportes) {
        System.out.println("Cartas carregadas:"); //mostra a quantidade de ataques/defesas/suportes
        System.out.println("- Ataque: " + ataques.size());
        System.out.println("- Defesa: " + defesas.size());
        System.out.println("- Suporte: " + suportes.size());
        System.out.println();

        // mostrar um exemplo de cada tipo
        if (!ataques.isEmpty()) {
            System.out.println("Exemplo ATAQUE: " + ataques.get(0).getNome());
        }
        if (!defesas.isEmpty()) {
            System.out.println("Exemplo DEFESA: " + defesas.get(0).getNome());
        }
        if (!suportes.isEmpty()) {
            System.out.println("Exemplo SUPORTE: " + suportes.get(0).getNome());
        }

        System.out.println();
    }

    public static void main(String[] args) {
            exibirBoasVindas();
            exibirResumoCartas(cartasAtaque, cartasDefesa, cartasSuporte);

        }
}
