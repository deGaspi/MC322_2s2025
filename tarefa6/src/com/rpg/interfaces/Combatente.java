package com.rpg.interfaces;

import com.rpg.armas.Arma;

public interface Combatente {
    public String getNome();
    public int receberDano(int dano);
    public void receberCura(int cura);
    public AcaoDeCombate escolherAcao();
    public int getPontosDeVida();
    public int getForca();
    public float getSorte();
    public Arma getArma();
    public int getNivel();
    public void darDano(Combatente alvo, int dano);
}
