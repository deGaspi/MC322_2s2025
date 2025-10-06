package com.rpg.acoes;

import com.rpg.armas.Arma;
import com.rpg.armas.SemArma;
import com.rpg.interfaces.AcaoDeCombate;
import com.rpg.interfaces.Combatente;

// Ataque padrão, usado por todods
public class Ataque implements AcaoDeCombate{
    public Ataque(){}

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
