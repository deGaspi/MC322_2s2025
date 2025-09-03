package io.telas;

import java.util.List;
import java.util.Scanner;

import io.Utils;
import io.BoxedString.HorizontalAlign;
import io.BoxedString;

public enum ChooseAction implements Utils.enumDescription {
    ATAQUE_SIMPLES("Ataque Simples"),
    ATAQUE_ESPECIAL("Ataque Especial"),
    INSPECIONAR("Inspecionar inimigo");

    private final String description;

    ChooseAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ChooseAction print(List<String> heroStatusList) {
        var actionArray = ChooseAction.values();
        var statusBox = new BoxedString(17);
        statusBox.appendLines(heroStatusList);
        statusBox.mergeLines(3, "  ");
        statusBox.appendLine("");

        // Construção da string a ser impressa.
        var box = new BoxedString(30);
        box.appendLines(Utils.enumToStrings(actionArray));
        box.mergeLines(2, "   ");
        statusBox.addBottom(box, HorizontalAlign.centerLeft);
        statusBox.insertLine("Seus status:");
        statusBox.addPadding(1, 1, 2, 2);
        statusBox.addBox();
        var boxString = statusBox.getString();

        // Loop para imprimir a pergunta e receber resposta válida.
        int input = 0;
        var scanner = new Scanner(System.in);
        while (true) {
            Utils.clear();
            System.out.println(boxString);
            System.out.print("Escolha uma ação (digite o número): ");
            if(scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); 
                if (input >= 0 && input < actionArray.length)
                    break;
            } else {
                scanner.nextLine();
            }
        }
        return actionArray[input];
    }
}
