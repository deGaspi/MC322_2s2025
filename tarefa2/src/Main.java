import java.util.Random;

import classes.Batalha;
import classes.heroi.Herói.heroEnum;
import classes.monstro.Entreguista;
import classes.monstro.FalsoPatriota;
import classes.monstro.Imperialista;
import classes.monstro.Monstro;

public class Main {
    private static int nLacaios = 6;

    public static void main(String[] args) {
        // Historia inicial.
        System.out.println(
                "Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo. \nSó resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o \ncarnaval.");

        // Escolha do heroi.
        var random = new Random();
        var heroValues = heroEnum.values();
        var heroiEscolhido = heroValues[random.nextInt(heroValues.length)];
        var heroi = heroiEscolhido.getDefaultInstance();

        // Explicar habilidade do heroi escolhido.
        System.out.println("Informações do herói: ");
        heroiEscolhido.printHabilityInfo();

        // Introdução do objetivo do jogo.
        System.out.println(
                "Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria \nde seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua \nganância");

        // Inicialização dos inimigos
        Monstro lacaio;
        var boss = new Imperialista("Imperialista", 1, 5, 0);
        var covarde = new Entreguista("b", 1, 5, 0, boss);

        for (int lacaiosDerrotados = 0; lacaiosDerrotados < nLacaios; lacaiosDerrotados++) { // Loop de lacaios
            // Tela de introdução de um inimigo.
            if (lacaiosDerrotados != 0)
                System.out.println(
                        "Você derrotou um lacaio do imperialismo, pelos seus sentidos aguçados, você sente que há mais "
                                + (nLacaios - lacaiosDerrotados) + ".");
            System.out.println("Um Falso Patriota se aproxima para defender os interesses extrangeiros.");

            // Criação do inimigo e batalha.
            lacaio = new FalsoPatriota("Lacaio " + (lacaiosDerrotados + 1) + "/" + nLacaios, 10, 2, 50);
            var batalha = new Batalha(heroi, lacaio, covarde);
            var ganhou = batalha.executarTurnos();

            // Tela de derrota.
            if (!ganhou) {
                System.out.println("O imperialismo conseguiu privatizar o carnaval");
                System.out.println("O   S A M B A   M O R R E U");
                return;
            }
        }

        // Introdução do boss.
        System.out.println("O entreguista fugiu para os Estados Unidos, deixando o imperialista vulnerável");
        System.out.println("Agora é a sua chance, você: " + heroi.getNome() + " enfrentará o imperialista");

        var batalha = new Batalha(heroi, boss, null);
        var ganhou = batalha.executarTurnos();

        // Tela final.
        if (ganhou) {
            System.out.println("Você eternizou o samba nos corações dos brasileiros");
        } else {
            System.out.println("O imperialismo conseguiu privatizar o carnaval");
            System.out.println("O   S A M B A   M O R R E U");
        }
    }
}
