package io.telas;

import java.util.Scanner;

import io.BoxedString;
import io.Utils;
import personagens.heroi.Herói.heroEnum;

public final class ChooseHero {
    private ChooseHero() {
    }

    public static heroEnum print() {
        var heroArray = heroEnum.values();
        var blockOfText = new BoxedString(48);
        blockOfText.appendLines(Utils.enumToStrings(heroArray));
        blockOfText.tabulate(2, "   ");
        blockOfText.addPadding(1, 1, 2, 2);
        blockOfText.addBox();
        blockOfText.appendLine("Escolha um herói (Digite o número): ");
        int input = 0;
        var scanner = new Scanner(System.in);
        while (true) {
            blockOfText.print();
            if (scanner.hasNextInt()){ 
                input = scanner.nextInt();
                scanner.nextLine(); 
                if (input >= 0 && input < heroArray.length)
                    break;
            } else {
                scanner.nextLine();
            }
        }
        return heroArray[input];
    }
}
