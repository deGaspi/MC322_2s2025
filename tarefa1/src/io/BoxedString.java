package io;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BoxedString {
    private int stringLength;
    private Deque<StringBuilder> deque = new ArrayDeque<>();

    public BoxedString(int stringLength) {
        this.stringLength = stringLength;
    }

    private List<StringBuilder> fixSize(String inputStr) {
        var strArray = inputStr.split("\\R");
        List<StringBuilder> strbList = new ArrayList<>();
        for (var str: strArray) {
            StringBuilder strb = new StringBuilder(str);
            if (strb.length() >= stringLength)
                strb.setLength(stringLength);
            else
                strb.append(" ".repeat(stringLength - strb.length()));
            strbList.add(strb);
        }
        return strbList;
    }

    public void appendLine(String str) {
        for (var strb: fixSize(str)) {
            deque.addLast(strb);
        }
    }

    public void appendLines(Iterable<String> list) {
        for (String str: list)
            appendLine(str);
    }

    public void insertLine(String str) {
        for (var strb: fixSize(str)) {
            deque.addFirst(strb);
        }
    }

    public void addStrLeft(String str) {
        int nOfLines = deque.size();
        for (int i = 1; i <= nOfLines; i++) {
            StringBuilder line = deque.removeFirst();
            line.insert(0, str);
            deque.addLast(line);
        }
        stringLength += str.length();
    }

    public void addStrRight(String str) {
        int nOfLines = deque.size();
        for (int i = 1; i <= nOfLines; i++) {
            StringBuilder line = deque.removeFirst();
            line.append(str);
            deque.addLast(line);
        }
        stringLength += str.length();
    }

    public void addBox() {
        addStrLeft("│");
        addStrRight("│");
        appendLine("╰" + "─".repeat(stringLength - 2) + "╯");
        insertLine("╭" + "─".repeat(stringLength - 2) + "╮");
    }

    public void addPadding(int top, int bottom, int left, int right) {
        addStrLeft(" ".repeat(left));
        addStrRight(" ".repeat(right));
        for (int i = 1; i <= bottom; i++)
            appendLine(" ".repeat(stringLength));
        for (int i = 1; i <= top; i++)
            insertLine(" ".repeat(stringLength));
    }

    public void addLineSpacing(int n) {
        int nOfLines = deque.size();
        String emptyLine = " ".repeat(stringLength);
        for (int i = 1; i < nOfLines; i++) {
            StringBuilder line = deque.removeFirst();
            deque.addLast(line);
            for (int j = 1; j <= n; j++)
                deque.addLast(new StringBuilder(emptyLine));
        }
        deque.addLast(deque.removeFirst());
    }

    public void tabulate(int mergeAmount, String spacer) {
        int nOfSmallLines = deque.size();
        for (int i = 1; i <= nOfSmallLines / mergeAmount; i++) {
            StringBuilder bigLine = new StringBuilder();
            for (int j = 1; j < mergeAmount; j++) {
                bigLine.append(deque.removeFirst());
                bigLine.append(spacer);
            }
            bigLine.append(deque.removeFirst());
            deque.add(bigLine);
        }
        int nOfLinesLeft = nOfSmallLines % mergeAmount;
        if (nOfLinesLeft != 0) {
            StringBuilder bigLine = new StringBuilder();
            for (int j = 1; j <= nOfLinesLeft; j++) {
                bigLine.append(deque.removeFirst());
                bigLine.append(spacer);
            }
            String emptyLine = " ".repeat(stringLength);
            for (int j = nOfLinesLeft + 1; j < mergeAmount; j++) {
                bigLine.append(emptyLine);
                bigLine.append(spacer);
            }
            bigLine.append(emptyLine);
            deque.add(bigLine);
        }
        stringLength = (stringLength + spacer.length()) * mergeAmount - spacer.length();
    }

    public static enum VerticalAlign {
        top,
        bottom,
        centerTop,
        centerBottom;
    }

    public void addLeft(BoxedString other, VerticalAlign align) {
        other = other.clone();
        BoxedString smaller = other;
        BoxedString bigger = this;

        if (other.deque.size() > deque.size()) {
            smaller = this;
            bigger = other;
        }

        int nOfLinesToAdd = bigger.deque.size() - smaller.deque.size();
        int addTop = 0;
        switch (align) {
            case VerticalAlign.bottom:
                addTop = nOfLinesToAdd;
                break;
            case VerticalAlign.centerTop:
                addTop = nOfLinesToAdd / 2;
                break;
            case VerticalAlign.centerBottom:
                addTop = (nOfLinesToAdd + 1) / 2;
                break;
            case VerticalAlign.top:
                break;
            default:
                break;
        }
        int addBottom = nOfLinesToAdd - addTop;
        String emptyLine = " ".repeat(smaller.stringLength);
        for (int i = 1; i <= addTop; i++) {
            smaller.deque.addFirst(new StringBuilder(emptyLine));
        }
        for (int i = 1; i <= addBottom; i++) {
            smaller.deque.addLast(new StringBuilder(emptyLine));
        }

        int nOfLines = this.deque.size();
        for (int i = 1; i <= nOfLines; i++) {
            StringBuilder str = deque.removeFirst();
            str.append(other.deque.removeFirst());
            deque.addLast(str);
        }
        stringLength += other.stringLength;
    }

    public static enum HorizontalAlign {
        left,
        right,
        centerLeft,
        centerRight;
    }

    public void concatBottom(BoxedString other, HorizontalAlign align) {
        other = other.clone();
        BoxedString smaller = other;
        BoxedString bigger = this;

        if (other.stringLength > stringLength) {
            smaller = this;
            bigger = other;
        }

        int nOfLinesToAdd = bigger.stringLength - smaller.stringLength;
        int addLeft = 0;
        switch (align) {
            case HorizontalAlign.right:
                addLeft = nOfLinesToAdd;
                break;
            case HorizontalAlign.centerLeft:
                addLeft = nOfLinesToAdd / 2;
                break;
            case HorizontalAlign.centerRight:
                addLeft = (nOfLinesToAdd + 1) / 2;
                break;
            case HorizontalAlign.left:
                break;
            default:
                break;
        }
        smaller.addStrLeft(" ".repeat(addLeft));
        smaller.addStrRight(" ".repeat(nOfLinesToAdd - addLeft));
        smaller.stringLength = bigger.stringLength;

        

        int nOfLines = other.deque.size();
        for (int i = 1; i <= nOfLines; i++)
            deque.addLast(other.deque.removeFirst());
    }

    public BoxedString clone() {
        BoxedString out = new BoxedString(stringLength);
        int nOfLines = deque.size();
        for (int i = 1; i <= nOfLines; i++) {
            StringBuilder line = deque.removeFirst();
            out.deque.addLast(new StringBuilder(line));
            deque.addLast(line);
        }
        return out;
    }

    public String getString() {
        int nOfLines = deque.size();
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < nOfLines; i++) {
            StringBuilder line = deque.removeFirst();
            out.append(line);
            out.append("\n");
            deque.addLast(line);
        }
        StringBuilder line = deque.removeFirst();
        out.append(line);
        deque.addLast(line);
        return out.toString();
    }

    public void clear() {
        this.deque = new ArrayDeque<>();
    }
    
    public void print() {
        System.out.print("\033[H\033[2J");
        System.out.print(this.getString());
        System.out.flush();
    }
}