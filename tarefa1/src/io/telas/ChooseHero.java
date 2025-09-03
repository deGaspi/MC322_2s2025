package io.telas;

import java.util.Scanner;

import io.BoxedString;
import io.Utils;
import personagens.heroi.Herói;

public final class ChooseHero {
    private ChooseHero() {
    }

    public static Herói print() {
        var heroArray = Herói.heroEnum.values();

        // Construção da string a ser impressa.
        var box = new BoxedString(30);
        box.appendLines(Utils.enumToStrings(heroArray));
        box.mergeLines(2, "   ");
        box.addPadding(1, 1, 2, 2);
        box.addBox();
        var boxString = box.getString();

        // Loop para imprimir a pergunta e receber resposta válida.
        int input = 0;
        var scanner = new Scanner(System.in);
        while (true) {
            Utils.clear();
            System.out.println(boxString);
            System.out.print("Escolha um herói (Digite o número): ");
            if (scanner.hasNextInt()){ 
                input = scanner.nextInt();
                scanner.nextLine(); 
                if (input >= 0 && input < heroArray.length)
                    break;
            } else {
                scanner.nextLine();
            }
        }
        return heroArray[input].getDefaultInstance();
    }
}
