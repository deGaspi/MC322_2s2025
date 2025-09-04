package io;

import java.util.Random;

import io.telas.InspectionScreen;
import io.telas.MsgScreen;
import io.telas.RoundScreen;
import personagens.heroi.Herói;
import personagens.monstro.Entreguista;
import personagens.monstro.Monstro;
import io.telas.RoundScreen.Action;

public class Batalha {
    private Herói heroi;
    private Monstro monstro;
    private Entreguista entreguista;
    private int round = 1;
    private MsgScreen postRoundScreen; // Tela mostrada após um round. Os personagens adicionam mensagens nessa tela por meio dos métodos estáticos dessa classe.
    private static Batalha batalhaAtual; // Para que os personagens possam adicionar mensagens sem precisar ter a instância da batalha.

    private Batalha(Herói heroi, Monstro monstro, Entreguista entreguista) { 
        this.heroi = heroi;
        this.monstro = monstro;
        this.entreguista = entreguista;
    }


    // Retorna true se o heroi venceu.
    // Se entreguista for null, ele não participará da batalha.
    public static boolean iniciar(Herói heroi, Monstro monstro, Entreguista entreguista) {
        batalhaAtual = new Batalha(heroi, monstro, entreguista);
        return batalhaAtual.executarTurnos();
    }

    // Retorna true se o heroi venceu.
    private boolean executarTurnos() {
        while (true) {
            // Verifica se a batalha acabou
            this.round++;
            if (monstro.getPontosDeVida() == 0)
                return true;
            if (heroi.getPontosDeVida() == 0)
                return false;
            heroRound();

            // Verifica se a batalha acabou
            if (monstro.getPontosDeVida() == 0)
                return true;
            if (heroi.getPontosDeVida() == 0)
                return false;
            monsterRound();
        }
    }

    private void heroRound() {
        boolean roundAcabou = false;
        while (!roundAcabou) { // Algumas ações não fazem o round acabar.
            var action = RoundScreen.print(heroi);  // Tela que mostra status do heroi e ações possíveis.
            switch (action) {
                case Action.ATAQUE_ESPECIAL:
                    postRoundScreen = new MsgScreen(); // Criação da tela pós turno.
                    postRoundScreen.addMsg("Você usa ataque especial no " + monstro.getNome() + "."); // Adição de mensagem na tela
                    roundAcabou = heroi.usarHabilidadeEspecial(monstro); // Os métodos de Personagem também adicionam mensagens na tela.
                    postRoundScreen.print();
                    break;
                case Action.ATAQUE_SIMPLES:
                    postRoundScreen = new MsgScreen(); // Criação da tela pós turno.
                    postRoundScreen.addMsg("Você ataca o " + monstro.getNome() + "."); // Adição de mensagem na tela
                    roundAcabou = heroi.atacar(monstro); // Os métodos de Personagem também adicionam mensagens na tela.
                    postRoundScreen.print();
                    break;
                case Action.INSPECIONAR:
                    InspectionScreen.print(monstro);
                    break;
                default:
                    break;
            }
        }
    }

    private void monsterRound() {
            postRoundScreen = new MsgScreen(); // Criação da tela pós turno.
            postRoundScreen.addMsg("O " + monstro.getNome() + " te ataca"); // Adição de mensagem na tela
            monstro.atacar(heroi); // Os métodos de Personagem também adicionam mensagens na tela.

            // Entreguista se metendo no meio da batalha.
            if (entreguista != null && round%2==0) {
                var random = new Random();
                boolean chance = random.nextBoolean();
                if (chance) {
                    postRoundScreen.addMsg("Entreguista vai privatizar a vida do... " + monstro.getNome() + "!!!");
                    entreguista.atacar(heroi);
                } else {
                    postRoundScreen.addMsg("Entreguista vai privatizar a vida do... " + heroi.getNome() + "!!!");
                    entreguista.atacar(monstro);
                }
            }
            postRoundScreen.print();
    }

    // Callback para os personagens poderem adicionar mensagens na tela pós round
    public static void addPostRoundMessage(String msg) {
        batalhaAtual.postRoundScreen.addMsg(msg);
    }
    
}