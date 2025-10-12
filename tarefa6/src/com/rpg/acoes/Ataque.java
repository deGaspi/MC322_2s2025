package com.rpg.acoes;

import com.rpg.armas.Arma;
import com.rpg.armas.SemArma;
import com.rpg.interfaces.AcaoDeCombate;
import com.rpg.interfaces.Combatente;

/**
 * É o ataque padrão usado por qualquer combatente
 */
public class Ataque implements AcaoDeCombate{
    public Ataque(){}

    /**
     * Método implementado de AcaoDeCombate, verifica se o usuario tem vida,
     * se sim, faz o cálculo do dano e printa a mensagem de ataque de acordo
     * com a arma, o usuario e o alvo
     */
    public void executar(Combatente usuario, Combatente alvo){
        if(usuario.getPontosDeVida() > 0){
            Arma arma = usuario.getArma();
            float dano = usuario.getForca() * usuario.getSorte() * 2 + usuario.getNivel() * 0.5f + arma.getDano();
            if (arma instanceof SemArma)
                System.out.println(usuario.getNome() + " ataca desarmado.");
            else 
                System.out.println(usuario.getNome() + " ataca com " + arma.getNome() + ".");
            usuario.darDano(alvo, Math.round(dano));
        }
    }
}
