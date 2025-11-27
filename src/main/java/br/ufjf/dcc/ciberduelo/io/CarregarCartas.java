package br.ufjf.dcc.ciberduelo.io;

import br.ufjf.dcc.ciberduelo.modelo.CartaAtaque;
import br.ufjf.dcc.ciberduelo.modelo.CartaDefesa;
import br.ufjf.dcc.ciberduelo.modelo.CartaSuporte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarregarCartas {

    // ATAQUE/DEFESA: Nome, Tipo, Poder, Custo, Descrição
    // SUPORTE: Nome, Tipo, Poder, Custo, Efeito, Descrição (Tipo -> tipoEfeito string; Poder -> efeito double; Efeito -> descrição curta)

    public List<CartaAtaque> carregarAtaques(String caminho) {
        List<CartaAtaque> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] p = linha.split(",");

                String nome = p[0].trim();
                int poder = Integer.parseInt(p[2].trim());
                int custo = Integer.parseInt(p[3].trim());
                String desc = p.length > 4 ? p[4].trim() : "";
                res.add(new CartaAtaque(nome, poder, custo, desc));
            }
        } catch (IOException e) {
            System.out.println("Erro ao 'carregarAtaques': " + e.getMessage());
        }
        return res;
    }

    public List<CartaDefesa> carregarDefesas(String caminho) {
        List<CartaDefesa> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] p = linha.split(",");

                String nome = p[0].trim();
                int poder = Integer.parseInt(p[2].trim());
                int custo = Integer.parseInt(p[3].trim());
                String desc = p.length > 4 ? p[4].trim() : "";
                res.add(new CartaDefesa(nome, poder, custo, desc));
            }
        } catch (IOException e) {
            System.out.println("Erro 'carregarDefesas': " + e.getMessage());
        }
        return res;
    }

    public List<CartaSuporte> carregarSuportes(String caminho) {
        List<CartaSuporte> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] p = linha.split(",");

                String nome = p[0].trim();
                double efeito = Double.parseDouble(p[2].trim());
                int custo = Integer.parseInt(p[3].trim());
                String tipoEfeito = p[4].trim(); // coluna "Efeito" no CSV
                String descricao = p.length > 5 ? p[5].trim() : tipoEfeito;
                res.add(new CartaSuporte(nome, efeito, custo, tipoEfeito, descricao));
            }
        } catch (IOException e) {
            System.out.println("Erro 'carregarSuportes': " + e.getMessage());
        }
        return res;
    }
}