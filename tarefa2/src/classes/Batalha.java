package classes;

import java.util.Random;

import classes.heroi.Herói;
import classes.monstro.Entreguista;
import classes.monstro.Monstro;

public class Batalha {
    private Herói heroi;
    private Monstro monstro;
    private Entreguista entreguista;
    private int round = 0;
    private Random random;

    public Batalha(Herói heroi, Monstro monstro, Entreguista entreguista) {
        this.heroi = heroi;
        this.monstro = monstro;
        this.entreguista = entreguista;
    }

    // Retorna true se o heroi venceu.
    public boolean executarTurnos() {
        while (true) {
            // Verifica se a batalha acabou
            this.round++;
            System.out.println();
            System.out.println("---------- Turno " + this.round + " ----------");
            System.out.println();
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
            System.out.println("Você ataca o " + monstro.getTipo().getDescription() + ".");
            roundAcabou = heroi.atacar(monstro);
        }
    }

    private void monsterRound() {
        System.out.println("O " + monstro.getTipo().getDescription() + " te ataca");
        monstro.atacar(heroi);

        // Entreguista se metendo no meio da batalha.
        if (entreguista != null && round % 2 == 0) {
            var random = new Random();
            boolean chance = random.nextBoolean();
            if (chance) {
                System.out.println(
                        "\nEntreguista vai privatizar a vida do... " + monstro.getTipo().getDescription() + "!!!");
                entreguista.atacar(monstro);
            } else {
                System.out.println("\nEntreguista vai privatizar a vida do... " + heroi.getNome() + "!!!");
                entreguista.atacar(heroi);
            }
        }
    }
}