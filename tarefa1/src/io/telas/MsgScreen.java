package io.telas;

import java.util.Scanner;

import io.BoxedString;

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
