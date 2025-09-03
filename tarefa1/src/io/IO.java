package io;

import io.telas.ChooseAction;
import io.telas.ChooseHero;
import io.telas.MonsterInspection;
import io.telas.roundMsg;
import personagens.Personagem;
import personagens.heroi.Herói;
import personagens.monstro.Monstro;
import personagens.monstro.Monstro.MonsterTypes;

public class IO {
    private Herói heroi;
    private Monstro monstro;
    private static BoxedString roundBoxStr = new BoxedString(63);

    public IO() {
        this.heroi = ChooseHero.print();
        this.monstro = MonsterTypes.FALSO_PATRIOTA.getDefaultInstance();
    }

    public static enum Resultado {
        PERDEU,
        GANHOU;
    }

    public static void dmgCallback(int damage, Personagem target) {
        roundBoxStr.appendLine(target.getName() + " recebeu " + damage + " pontos de dano.");
    }

    public static void healCallback(int heal, Personagem target) {
        roundBoxStr.appendLine(target.getName() + " recebeu " + heal + " pontos de vida.");
    }

    public static void msgCallback(String msg) {
        roundBoxStr.appendLine(msg);
    }

    public static void xpCallback(int xp, Herói target) {
        roundBoxStr.appendLine(target.getName() + " recebeu " + xp + " pontos de experiência.");
    }

    public Resultado start() {
        while (true) {
            boolean nextRound = false;
            if (monstro.getPontosDeVida() == 0)
                return Resultado.GANHOU;
            if (heroi.getPontosDeVida() == 0)
                return Resultado.PERDEU;
            while (!nextRound) {
                var action = ChooseAction.print(heroi.getStatusList());
                switch (action) {
                    case ATAQUE_ESPECIAL:
                        msgCallback("Você usa ataque especial no " + monstro.getName() + ".");
                        nextRound = heroi.usarHabilidadeEspecial(monstro);
                        roundMsg.print(roundBoxStr);
                        break;
                    case ATAQUE_SIMPLES:
                        msgCallback("Você ataca o " + monstro.getName() + ".");
                        nextRound = heroi.atacar(monstro);
                        roundMsg.print(roundBoxStr);
                        break;
                    case INSPECIONAR:
                        MonsterInspection.print(monstro);
                        break;
                    default:
                        break;
                }
            }
            if (monstro.getPontosDeVida() == 0)
                return Resultado.GANHOU;
            if (heroi.getPontosDeVida() == 0)
                return Resultado.PERDEU;
            msgCallback("O " + monstro.getName() + " te ataca");
            monstro.atacar(heroi);
            roundMsg.print(roundBoxStr);
        }
    }

}