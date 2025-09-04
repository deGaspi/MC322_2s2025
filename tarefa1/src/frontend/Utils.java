package frontend;
import java.util.List;
import java.util.ArrayList;

public class Utils {
    public interface enumDescription {
        String getDescription();
    }

    // Gera uma tabela com os valores de um Enum.
    public static <T extends Enum<T> & enumDescription> List<String> enumToStrings(T[] options) {
        List<String> lista = new ArrayList<>();
        for (T option : options) {
            StringBuilder str = new StringBuilder();
            str.append("(");
            str.append(option.ordinal());
            str.append(") ");
            str.append(option.getDescription());
            lista.add(str.toString());
        }
        return lista;
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}