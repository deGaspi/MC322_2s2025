package com.rpg.util;

import com.rpg.armas.Arma;
import com.rpg.interfaces.Combatente;

public class EquiparSemNivel extends Exception{
    private Combatente combatente;
    private Arma arma;

    public EquiparSemNivel(Combatente c, Arma a){
        this.combatente = c;
        this.arma = a;
    }

    public String getMessage(){
        return (combatente.getNome() + " não tem experiência suficiente para lidar com " + arma.getNome());
    }
}
