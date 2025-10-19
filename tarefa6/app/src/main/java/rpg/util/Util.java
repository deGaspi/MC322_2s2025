package rpg.util;

public class Util {
    private Util() {
    }

    public static void printBoxed(String str) {
        new BoxedString()
            .append(str)
            .addPadding(1)
            .addBox()
            .print();
    }

    public static void printBoxed(String centeredStr, String leftAlignedStr) {
        new BoxedString()
            .appendCentered(centeredStr)
            .append(leftAlignedStr)
            .addPadding(1)
            .addBox()
            .print();
    }

    public static void printBoxedCentered(String str) {
        new BoxedString()
            .appendCentered(str)
            .addPadding(1)
            .addBox()
            .print();
    }
}
