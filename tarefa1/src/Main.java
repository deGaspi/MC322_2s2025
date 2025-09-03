import io.IO;

public class Main {
    public static void main(String[] args) {
        var batalha = new IO();
        var resultado = batalha.start();
        switch (resultado) {
            case IO.Resultado.PERDEU:
                System.out.println("Você morreu!");
                break;
            case IO.Resultado.GANHOU:
                System.out.println("Você ganhou!");
                break;
            default:
                break;
        }
    }

    
}
