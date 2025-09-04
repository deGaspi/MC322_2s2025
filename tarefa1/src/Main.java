import backend.Batalha;
import backend.monstro.Entreguista;
import backend.monstro.FalsoPatriota;
import backend.monstro.Imperialista;
import backend.monstro.Monstro;
import frontend.telas.ChooseHero;
import frontend.telas.MsgScreen;



public class Main {
    private static int nLacaios = 6;

    public static void main(String[] args) {
        // Historia inicial.
        var initialScreen = new MsgScreen();
        initialScreen.addMsg("Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo. \nSó resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o \ncarnaval.");
        initialScreen.print();

        // Escolha do heroi.
        var heroiEscolhido = ChooseHero.print();
        var heroi = heroiEscolhido.getDefaultInstance();

        // Explicar habilidade do heroi escolhido.
        var habilityInfoScreen = new MsgScreen();
        habilityInfoScreen.addMsg("Informações do heró: ");
        habilityInfoScreen.addMsg(heroiEscolhido.getHabilityInfo());
        habilityInfoScreen.print();

        // Introdução do objetivo do jogo.
        var objectiveScreen = new MsgScreen();
        objectiveScreen.addMsg("Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria \nde seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua \nganância");
        objectiveScreen.print();

        // Inicialização dos inimigos
        Monstro lacaio;
        var boss = new Imperialista("Imperialista", 1, 5, 0);
        var covarde = new Entreguista("b", 1, 5, 0, boss);

        for (int lacaiosDerrotados=0; lacaiosDerrotados<nLacaios; lacaiosDerrotados++) { // Loop de lacaios
            // Tela de introdução de um inimigo.
            var newEnemyScreen = new MsgScreen();
            if (lacaiosDerrotados!=0)
                newEnemyScreen.addMsg("Você derrotou um lacaio do imperialismo, pelos seus sentidos aguçados, você sente que há mais " + (nLacaios - lacaiosDerrotados) + ".");
            newEnemyScreen.addMsg("Um Falso Patriota se aproxima para defender os interesses extrangeiros.");
            newEnemyScreen.print();

            // Criação do inimigo e batalha.
            lacaio = new FalsoPatriota("Lacaio " + (lacaiosDerrotados+1) + "/" + nLacaios, 10, 2, 50);
            var ganhou = Batalha.iniciar(heroi, lacaio, covarde);

            // Tela de derrota.
            if (!ganhou) {
                var defeatScreen = new MsgScreen();
                defeatScreen.addMsg("O imperialismo conseguiu privatizar o carnaval");
                defeatScreen.addMsg("O   S A M B A   M O R R E U");
                defeatScreen.print();
                return;
            }
        }

        // Introdução do boss.
        var bossScreen = new MsgScreen();
        bossScreen.addMsg("O entreguista fugiu para os Estados Unidos, deixando o imperialista vulnerável");
        bossScreen.addMsg("Agora é a sua chance, você: " + heroi.getNome() + " enfrentará o imperialista");
        bossScreen.print();

        var ganhou = Batalha.iniciar(heroi, boss, null);

        // Tela final.
        var endScreen = new MsgScreen();
        if (ganhou) {
            endScreen.addMsg("Você eternizou o samba nos corações dos brasileiros");
        } else {
            endScreen.addMsg("O imperialismo conseguiu privatizar o carnaval");
            endScreen.addMsg("O   S A M B A   M O R R E U");
        }
        endScreen.print();
    }
}
