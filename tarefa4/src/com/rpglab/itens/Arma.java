package com.rpglab.itens;

public enum Arma implements Item {
    DESARMADO(0, 0, "Desarmado"),
    CHINELO(3, 0, "Chinelo"),
    LANÇA(9, 8, "Lança de Porta Bandeira"),
    REPIQUE(15, 15, "Repique-mor");

    public final int dano;
    public final int minLvl;
    public final String nome;

    private Arma(int damage, int minLevel, String nome){
        this.dano = damage;
        this.minLvl = minLevel;
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public int getMinLvl() {
        return minLvl;
    }

    
}
