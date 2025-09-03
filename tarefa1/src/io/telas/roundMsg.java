package io.telas;

import java.util.Scanner;

import io.BoxedString;
import io.Utils;

public final class roundMsg {
    private roundMsg() {
    }

    public static void print(BoxedString roundBoxStr) {
        roundBoxStr.addPadding(1, 1, 2, 2);
        roundBoxStr.addBox();

        Utils.clear();
        System.out.println(roundBoxStr.getString());
        System.out.print("Enter para continuar: ");
        roundBoxStr.clear();
        var scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
