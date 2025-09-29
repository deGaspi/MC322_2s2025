package com.rpg.heroi;

import com.rpg.armas.Arma;
import com.rpg.armas.SemArma;
import com.rpg.interfaces.Combatente;

public abstract class Personagem implements Combatente {
    private String nome;
    private int pontosDeVida;
    private int forca;
    private float sorte;
    private int lvl;
    private Arma arma = new SemArma();

    public Personagem(String name, int LP, int strength, float sorte, int lvl) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
        this.sorte = sorte;
        this.lvl = lvl;
    }

    // ------------- getters -------------
    public Arma getArma() {
        return arma;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public int getForca() {
        return forca;
    }

    public float getSorte() {
        return sorte;
    }

    public int getNivel() {
        return lvl;
    }

    public String getNome(){
        return nome;
    }

    public void exibirStatus() {
        System.out.println("    Nome: " + nome);
        System.out.println("    Vida: " + pontosDeVida);
        System.out.println("    Nível: " + lvl);
        System.out.println("    Força: " + forca);
        System.out.println("    Arma: " + arma.getNome() + " (Força: +" + arma.getDano() + " | NívelMin: "
                + arma.getMinLvl() + ")");
    }

    @Override
    public void darDano(Combatente alvo, int dano) { // é sobrescrita na classe heroi
        alvo.receberDano(dano);
    }

    public int receberDano(int dano) {
        dano = Math.min(dano, pontosDeVida);
        pontosDeVida -= dano;
        System.out.println(
                nome + " recebeu " + dano + " pontos de dano, ficando com " + pontosDeVida + " pontos de vida.");
        return dano;
    }

    public void receberCura(int cura) {
        pontosDeVida += cura;
        System.out.println(nome + " recebeu " + cura + " pontos de vida.");
    }

    public void receberForca(int forca) {
        this.forca += forca;
        System.out.println(nome + " recebeu " + forca + " pontos de força.");
    }

    public void receberLvl(int lvl) {
        this.lvl += lvl;
        System.out.println(this.getNome() + " subiu para nível " + lvl + ".");
    }



    // ------------- setters -------------

    public void setSorte(float sorte) {
        this.sorte = sorte;
    }

    public void setArma(Arma a){
        this.arma = a;
    }

    public void receberArma(Arma arma) throws Exception{
        this.arma = arma;
    }
}