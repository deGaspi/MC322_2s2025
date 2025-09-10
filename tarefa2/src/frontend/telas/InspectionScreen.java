package frontend.telas;

import java.util.Scanner;

import backend.monstro.Monstro;
import frontend.BoxedString;

// ╭───────────────────────────────────────────────────────────────────────────────────────────────────────╮
// │                                                                                                       │
// │  Status do inimigo:                                                                                   │
// │                                                                                                       │
// │  Nome: Lacaio 1/6                                   Vida: 10                                          │
// │  Força: 1                                           XP: 50                                            │
// │                                                                                                       │
// ╰───────────────────────────────────────────────────────────────────────────────────────────────────────╯
// Status do inimigo:

// Produz uma tela como o exemplo acima e espera por confirmação.
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
        blockOfText.appendLine("Enter para continuar: ");
        blockOfText.print();

        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
