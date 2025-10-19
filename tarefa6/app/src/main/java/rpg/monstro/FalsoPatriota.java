package rpg.monstro;

import rpg.armas.Arma;
import rpg.interfaces.Combatente;
import rpg.cenarios.Dificuldade;

public class FalsoPatriota extends Monstro {
    public FalsoPatriota(String name, int LP, int strength, int xp, Arma arma, Dificuldade dif) {
        super(name, LP, strength, xp, arma, dif);
    }

    @Override
    public MonstroEnum getTipo() {
        return MonstroEnum.FALSO_PATRIOTA;
    }

    @Override
    public void darDano(Combatente alvo, int dano) {
        System.out.println("Falso Patriota atacou com música gringa");
        super.darDano(alvo, dano);
    }

    @Override
    public void usarHabilidadeEspecial(Combatente alvo) {
    }

}
