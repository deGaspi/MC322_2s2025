package io.telas;

import java.util.Scanner;

import io.BoxedString;

// ╭───────────────────────────────────────────────────────────────────────────────────────────────────────╮
// │                                                                                                       │
// │  Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria  │
// │  de seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua       │
// │  ganância                                                                                             │
// │                                                                                                       │
// ╰───────────────────────────────────────────────────────────────────────────────────────────────────────╯
// Enter para continuar:

// Produz uma tela generica com todas as strings acionadas e espera confirmação.
public class MsgScreen {
    private BoxedString blockOfText = new BoxedString(99);

    public void addMsg(String msg) {
        blockOfText.appendLine(msg);
    }

    public void print() {
        blockOfText.addPadding(1, 1, 2, 2);
        blockOfText.addBox();
        blockOfText.appendLine("Enter para continuar: ");
        blockOfText.print();

        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
