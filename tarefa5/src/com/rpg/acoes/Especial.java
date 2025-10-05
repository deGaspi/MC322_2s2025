package com.rpg.acoes;

import com.rpg.heroi.Herói;
import com.rpg.interfaces.AcaoDeCombate;
import com.rpg.interfaces.Combatente;

// Ataque Especial usado pelos heróis
public class Especial implements AcaoDeCombate{
    public Especial(){}

    public void executar(Combatente usuario, Combatente alvo){
        if (usuario instanceof Herói hero) {
            hero.usarHabilidadeEspecial(alvo);
        }
    }
}
