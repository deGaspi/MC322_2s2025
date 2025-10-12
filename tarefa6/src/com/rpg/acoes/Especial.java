package com.rpg.acoes;

import com.rpg.heroi.Heroi;
import com.rpg.interfaces.AcaoDeCombate;
import com.rpg.interfaces.Combatente;

/**
 * Ataque Especial dos heróis
 */
public class Especial implements AcaoDeCombate{
    public Especial(){}

    /**
     * Verifica se o combatente é um herói e chama seu método de especial
     */
    public void executar(Combatente usuario, Combatente alvo){
        if (usuario instanceof Heroi hero) {
            hero.usarHabilidadeEspecial(alvo);
        }
    }
}
