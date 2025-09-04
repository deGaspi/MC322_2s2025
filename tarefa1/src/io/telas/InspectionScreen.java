package io.telas;

import java.util.Scanner;

import io.BoxedString;
import personagens.monstro.Monstro;

public final class InspectionScreen {
    private InspectionScreen() {
    }

    public static void print(Monstro monstro) {
        var blockOfText = new BoxedString(48);
        blockOfText.appendLines(monstro.getStatusList());
        blockOfText.tabulate(2, "   ");
        blockOfText.insertLine("");
        blockOfText.insertLine("Status do inimigo:");
        blockOfText.addPadding(1, 1, 2, 2);
        blockOfText.addBox();
        blockOfText.appendLine("Status do inimigo:");
        blockOfText.print();

        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
