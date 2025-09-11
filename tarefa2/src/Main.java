import java.util.Random;

import classes.ConstrutorDeCenário;
import classes.heroi.Herói.heroEnum;

public class Main {
    public static void main(String[] args) {
        var fases = ConstrutorDeCenário.gerarFases(4); // 4 fases

        // Historia inicial.
        System.out.println(
                "Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo. \nSó resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o \ncarnaval.\n");

        // Escolha do heroi.
        var random = new Random();
        var heroValues = heroEnum.values();
        var heroiEscolhido = heroValues[random.nextInt(heroValues.length)];
        var heroi = heroiEscolhido.getDefaultInstance();

        // Explicar habilidade do heroi escolhido.
        System.out.println("Informações do herói: ");
        heroiEscolhido.printHabilityInfo();
        System.out.println();

        // Introdução do objetivo do jogo.
        System.out.println(
                "Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria \nde seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua \nganância.\n");

        // Inicialização dos inimigos
        for (var fase : fases) {
            for (var monstro : fase.monstros()) {
                int round = 0;
                boolean ganhou;
                System.out.println(monstro.getNome() + " se aproxima para defender os interesses extrangeiros.");
                while (true) {
                    round++;
                    System.out.println();
                    System.out.println("---------- Turno " + round + " ----------");
                    System.out.println();
                    if (monstro.getPontosDeVida() == 0) {
                        ganhou = true;
                        break;
                    }
                    if (heroi.getPontosDeVida() == 0) {
                        ganhou = false;
                        break;
                    }
                    heroi.atacar(monstro);

                    // Verifica se a batalha acabou
                    if (monstro.getPontosDeVida() == 0) {
                        ganhou = true;
                        break;
                    }
                    if (heroi.getPontosDeVida() == 0) {
                        ganhou = false;
                        break;
                    }
                    monstro.atacar(heroi);
                }
                if (!ganhou) {
                    System.out.println("O imperialismo conseguiu privatizar o carnaval");
                    System.out.println("O   S A M B A   M O R R E U");
                    return;
                }
            }
        }
        System.out.println("\nVocê eternizou o samba nos corações dos brasileiros");
    }
}
