package io.telas;

import java.util.Scanner;

import io.BoxedString;
import personagens.monstro.Monstro;
import io.Utils;

public class MonsterInspection {
    private MonsterInspection() {
    }

    public static void print(Monstro monstro) {
        var statusBox = new BoxedString(30);
        statusBox.appendLines(monstro.getStatusList());
        statusBox.mergeLines(2, "   ");

        // Construção da string a ser impressa.
        statusBox.insertLine("");
        statusBox.insertLine("Status do inimigo:");
        statusBox.addPadding(1, 1, 2, 2);
        statusBox.addBox();

        Utils.clear();
        System.out.println(statusBox.getString());
        System.out.print("Enter para continuar: ");
        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
