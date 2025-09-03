import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Herói heróiEscolhido = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        heróiEscolhido = escolherPersonagem();
        Monstro monstro = new FalsoPatriota("TODO", 10, 1, 40);
        while (monstro.getPontosDeVida() > 0) {
            System.out.println("---------------------------------------");
            boolean acabarRound = false;
            while (!acabarRound) {
                Escolha escolha = escolha();
                switch (escolha) {
                    case Escolha.ataqueSimples:
                        heróiEscolhido.atacar(monstro);
                        acabarRound = true;
                        break;
                    case Escolha.ataqueEspecial:
                        acabarRound = heróiEscolhido.usarHabilidadeEspecial(monstro);
                        break;
                    case Escolha.statusMonstro:
                        monstro.exibirStatus();
                        break;
                    case Escolha.statusHeroi:
                        heróiEscolhido.exibirStatus();
                        break;
                    default:
                        break;
                }
            }
            monstro.atacar(heróiEscolhido);
        }
    }

    private enum Escolha {
        ataqueSimples,
        ataqueEspecial,
        statusHeroi,
        statusMonstro,
    }

    private static Escolha escolha() {
        int input = 0;
        System.out.println("Escolha uma ação:");
        System.out.println("1-) Ataque Simples");
        System.out.println("2-) Ataque Especial");
        System.out.println("3-) Mostrar status do Herói");
        System.out.println("4-) Mostrar status do Monstro");
        while (true) {
            try {
                input = scanner.nextInt();
                switch (input) {
                    case 1:
                        return Escolha.ataqueSimples;
                    case 2:
                        return Escolha.ataqueEspecial;
                    case 3:
                        return Escolha.statusHeroi;
                    case 4:;
                        return Escolha.statusMonstro;
                    default:
                        System.out.println("Digite o número de 1 a 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite o número de 1 a 4");
            }
        }
        
    }

    private static Herói escolherPersonagem() {
        int input = 0;
        System.out.println("Escolha um herói: (digite o número)");
        System.out.println("1-) Passista");
        System.out.println("2-) Puxador");
        while (true) {
            try {
                input = scanner.nextInt();
                switch (input) {
                    case 1:
                        return new Passista("TODO", 10, 1, 0, 0, 0);
                    case 2:
                        return new Puxador("TODO", 10, 1, 0, 0, 0);
                    default:
                        System.out.println("Digite o número 1 ou 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite o número 1 ou 2");
                scanner.next();
            }
        }
    }
}
