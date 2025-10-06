package com.rpg.monstro;

import com.rpg.armas.Arma;
import com.rpg.cenarios.Dificuldade;
import com.rpg.interfaces.Combatente;

public class Imperialista extends Monstro{
    public Imperialista(String name, int LP, int strength, int xp, Arma arma, Dificuldade dif){
        super(name, LP, strength, xp, arma, dif);
    }

    @Override
    public MonstroEnum getTipo() {
        return MonstroEnum.IMPERIALISTA;
    }

    @Override
    public void darDano(Combatente alvo, int dano){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        super.darDano(alvo, dano);
    }
}