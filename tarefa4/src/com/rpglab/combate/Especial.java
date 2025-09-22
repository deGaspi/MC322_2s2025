package com.rpglab.combate;

import com.rpglab.personagens.Combatente;
import com.rpglab.personagens.heroi.Herói;

// Ataque Especial usado pelos heróis
public class Especial implements AcaoDeCombate{
    public Especial(){}

    public void executar(Combatente usuario, Combatente alvo){
        if (usuario instanceof Herói hero) {
            hero.usarHabilidadeEspecial(alvo);
        }
    }
}
