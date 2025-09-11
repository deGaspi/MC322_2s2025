import java.util.Random;

import classes.Batalha;
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
                var batalha = new Batalha(heroi, monstro, null);
                var ganhou = batalha.executarTurnos();
                if (!ganhou) {
                    System.out.println("perdeu!"); // TODO: arrumar mensagem.
                    return;
                }
            }
        }
        System.out.println("Ganhou"); // TODO: arrumar mensagem

/* 
        for (int lacaiosDerrotados = 0; lacaiosDerrotados < nLacaios; lacaiosDerrotados++) { // Loop de lacaios
            // Tela de introdução de um inimigo.
            if (lacaiosDerrotados != 0)
                System.out.println(
                        "Você derrotou um lacaio do imperialismo, pelos seus sentidos aguçados, você sente que há mais "
                                + (nLacaios - lacaiosDerrotados) + ".\n\n");
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
        System.out.println("\nO entreguista fugiu para os Estados Unidos, deixando o imperialista vulnerável");
        System.out.println("Agora é a sua chance, você: " + heroi.getNome() + " enfrentará o imperialista");

        var batalha = new Batalha(heroi, boss, null);
        var ganhou = batalha.executarTurnos();

        // Tela final.
        if (ganhou) {
            System.out.println("\nVocê eternizou o samba nos corações dos brasileiros");
        } else {
            System.out.println("O imperialismo conseguiu privatizar o carnaval");
            System.out.println("O   S A M B A   M O R R E U");
        }
*/
    }
}
