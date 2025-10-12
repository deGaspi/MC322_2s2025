package com.rpg.monstro;

import com.rpg.armas.Arma;
import com.rpg.interfaces.Combatente;
import com.rpg.cenarios.Dificuldade;

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
}
