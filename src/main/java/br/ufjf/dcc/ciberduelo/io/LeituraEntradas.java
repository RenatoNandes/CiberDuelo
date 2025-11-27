package br.ufjf.dcc.ciberduelo.io;

import java.util.Scanner;

public class LeituraEntradas {

    private final Scanner scanner;

    public LeituraEntradas(Scanner scanner) {
        this.scanner = scanner;
    }

    // Le entrada do usuário para verificar a opção escolhida
    public int lerOpcao(int min, int max) {
        while (true) {
            try {
                String s = scanner.nextLine().trim();
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {
            }

            System.out.print("Entrada inválida. Escolha entre " + min + " e " + max + ": ");
        }
    }

    // Converte o nome para ficar com a inicial maiúscula
    public static String converteMaiusculo(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}