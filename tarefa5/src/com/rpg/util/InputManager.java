package com.rpg.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class InputManager {
    private final Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerInteiro(String mensagem, int min, int max) {
        int valor;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    break;
                } else {
                    System.err.println("Erro: O número deve estar entre " + min + " e " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
            } finally {
                scanner.nextLine();
            }
        }
        return valor;
    }

    public String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public boolean lerSimNao(String mensagem) {
        String resposta;
        while (true) {
            System.out.print(mensagem);
            resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                return true;
            } else if (resposta.equals("n")) {
                return false;
            } else {
                System.err.println("Erro: Entrada inválida. Por favor, digite 's' para sim ou 'n' para não.");
            }
        }
    }

    public void esperarEnter(String mensagem) {
        System.out.print(mensagem);
        scanner.nextLine();
    }

    public void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}