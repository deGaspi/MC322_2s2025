package com.rpglab.personagens.monstro;

import com.rpglab.personagens.Combatente;

public class Imperialista extends Monstro{
    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp); 
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