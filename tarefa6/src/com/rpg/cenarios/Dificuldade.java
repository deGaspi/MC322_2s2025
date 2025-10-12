package com.rpg.cenarios;

/**
 * Enum que as classes usam para reconhecer a dificuldade
 * valor é usado como multiplicador da vida de monstros e 
 * outros atributos
 */

public enum Dificuldade {
    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    public final int valor;

    Dificuldade(int valor){
        this.valor = valor;
    }
}
