package com.rpg.armas;

import com.rpg.cenarios.Dificuldade;
import com.rpg.interfaces.Item;

public abstract class Arma implements Item{
    private final int dano;
    private final int minLvl;
    private final String nome;
    private final Dificuldade dificuldade;

    public Arma(int damage, int minLvl, String nome, Dificuldade dificuldade){
        this.dano = damage;
        this.minLvl = minLvl;
        this.nome = nome;
        this.dificuldade = dificuldade;
    }

    public int getDano(){
        float multiplicador;
        switch (dificuldade) {
            case FACIL:
                multiplicador = 1;
                break;
            case MEDIO:
                multiplicador = 1.25f;
                break;
            case DIFICIL:
                multiplicador = 1.5f;
                break;
            default:
                throw new AssertionError("Enum inesperado: " + dificuldade);
        }
        return Math.round(dano * multiplicador);
    }

    public int getMinLvl(){
        return minLvl;
    }

    @Override
    public String getNome() {
        return nome;
    }
}
