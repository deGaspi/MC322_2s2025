package com.rpg.cenarios;

public enum Dificuldade {
    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    public final int valor;

    Dificuldade(int valor){
        this.valor = valor;
    }
}
