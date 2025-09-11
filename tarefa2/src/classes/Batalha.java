package classes;

import classes.heroi.Herói;
import classes.monstro.Entreguista;
import classes.monstro.Monstro;

public class Batalha {
    private Herói heroi;
    private Monstro monstro;
    private int round = 0;

    public Batalha(Herói heroi, Monstro monstro, Entreguista entreguista) {
        this.heroi = heroi;
        this.monstro = monstro;
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
            heroi.atacar(monstro);

            // Verifica se a batalha acabou
            if (monstro.getPontosDeVida() == 0)
                return true;
            if (heroi.getPontosDeVida() == 0)
                return false;
             monstro.atacar(heroi);
        }
    }
    /* TODO: adicionar a lógica abaixo na classe do entreguista.
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
    */
}