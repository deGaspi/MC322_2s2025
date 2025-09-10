package frontend.telas;

import java.util.Scanner;

import backend.heroi.Herói;
import frontend.BoxedString;
import frontend.Utils;
import frontend.BoxedString.HorizontalAlign;

// ╭───────────────────────────────────────────────────────────────────────────────────────────────────────╮
// │                                                                                                       │
// │  Seus status:                                                                                         │
// │                   Nome: Jamelão         Vida: 20              Força: 4                                │
// │                   Nível: 0              Nível: 0              Swing: 0                                │
// │                                                                                                       │
// │  (0) Ataque Simples                                 (1) Ataque Especial                               │
// │  (2) Inspecionar inimigo                                                                              │
// │                                                                                                       │
// ╰───────────────────────────────────────────────────────────────────────────────────────────────────────╯
// Escolha uma ação (digite o número):

// Produz uma tela como o exemplo acima, e scaneia resposta.
public final class RoundScreen {
    private RoundScreen() {
    }

    public static Action print(Herói hero) {
        var actionArray = Action.values();

        var bottomBlockOfText = new BoxedString(48);
        bottomBlockOfText.appendLines(Utils.enumToStrings(actionArray));
        bottomBlockOfText.tabulate(2, "   ");

        var blockOfText = new BoxedString(25);
        blockOfText.appendLines(hero.getStatusList());
        blockOfText.tabulate(3, "  ");
        blockOfText.appendLine("");
        blockOfText.concatBottom(bottomBlockOfText, HorizontalAlign.centerLeft);
        blockOfText.insertLine("Seus status:");
        blockOfText.addPadding(1, 1, 2, 2);
        blockOfText.addBox();
        blockOfText.appendLine("Escolha uma ação (digite o número): ");

        // Loop para imprimir a pergunta e receber resposta válida.
        int input = 0;
        var scanner = new Scanner(System.in);
        while (true) {
            blockOfText.print();
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                if (input >= 0 && input < actionArray.length) // verifica se o int digitado corresponde a uma action
                                                              // existente.
                    break;
            } else {
                scanner.nextLine();
            }
        }
        return actionArray[input];
    }

    public static enum Action implements Utils.enumDescription {
        ATAQUE_SIMPLES("Ataque Simples"),
        ATAQUE_ESPECIAL("Ataque Especial"),
        INSPECIONAR("Inspecionar inimigo");

        private final String description;

        Action(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
