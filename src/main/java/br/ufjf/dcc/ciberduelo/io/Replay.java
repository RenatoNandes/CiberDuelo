package br.ufjf.dcc.ciberduelo.io;

import br.ufjf.dcc.ciberduelo.modelo.Carta;
import br.ufjf.dcc.ciberduelo.modelo.Hacker;
import br.ufjf.dcc.ciberduelo.modelo.Jogada;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Replay {

    private final String caminhoTxt;

    public Replay(String caminhoTxt) {
        this.caminhoTxt = caminhoTxt;
    }

    public void salvarReplay(Map<Hacker, List<Carta>> decksIniciais, List<Jogada> jogadas, Hacker vencedor, Hacker perdedor) {
        try (FileWriter fw = new FileWriter(caminhoTxt)) {

            fw.write("==================== REPLAY DA PARTIDA ====================\n\n");

            fw.write("SELEÇÃO DE CARTAS INICIAIS\n\n");

            // Imprime as cartas iniciais
            for (Hacker h : decksIniciais.keySet()) {
                fw.write("Hacker: " + h.getNome() + " (ID: " + h.getIdentificador() + ")\n\n");
                fw.write("Deck inicial: \n");
                for (Carta c : decksIniciais.get(h)) {
                    fw.write("- " + c.getNome() + "\n");
                }
                fw.write("\n");
            }

            fw.write("==================== TURNOS ====================\n\n");

            int turnoAtual = -1;

            // Imprime as jogadas
            for (Jogada j : jogadas) {

                if (j.getTurno() != turnoAtual) {
                    turnoAtual = j.getTurno();
                    fw.write("--------------- TURNO " + turnoAtual + " ---------------\n\n");
                }

                fw.write("Hacker: " + j.getNomeHacker() + "\n");
                fw.write("ID: " + j.getIdHacker() + "\n");
                fw.write("Vida: " + j.getVida() + "\n");
                fw.write("Energia: " + j.getEnergia() + "\n");
                fw.write("Passou? " + (j.passou() ? "sim" : "não") + "\n");
                fw.write("Desistiu? " + (j.desistiu() ? "sim" : "não") + "\n");

                fw.write("Cartas jogadas:\n");

                if (j.getCartasJogadas().isEmpty()) {
                    fw.write("(Nenhuma)\n");
                } else {
                    for (String nomeCarta : j.getCartasJogadas()) {
                        fw.write("- " + nomeCarta + "\n");
                    }
                }

                fw.write("\n-----------------------------------------------\n\n");
            }

            fw.write("RESULTADO FINAL: \n");
            fw.write("Vencedor: " + vencedor.getNome() + "\n");
            fw.write("Perdedor: " + perdedor.getNome() + "\n");

            System.out.println("Replay salvo em: " + caminhoTxt);

        } catch (IOException e) {
            System.out.println("Erro ao salvar replay: " + e.getMessage());
        }
    }
}