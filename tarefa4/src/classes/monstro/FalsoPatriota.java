package classes.monstro;

import classes.interfaces.Combatente;;

public class FalsoPatriota extends Monstro {
    public FalsoPatriota(String name, int LP, int strength, int xp) {
        super(name, LP, strength, xp);
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
}
