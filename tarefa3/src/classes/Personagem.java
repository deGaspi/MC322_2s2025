package classes;

import classes.armas.Arma;
import classes.armas.SemArma;
import classes.interfaces.Combatente;

public abstract class Personagem implements Combatente{
    private String nome;
    private int pontosDeVida;
    private int forca;
    private Arma arma = new SemArma();

    public Personagem(String name, int LP, int strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
    }

    public Arma getArma() {
        return this.arma;
    }

    public int getPontosDeVida() {
        return this.pontosDeVida;
    }

    public int getForca() {
        return this.forca;
    }

    public String getNome(){
        return this.nome;
    }

    // Está aqui para satisfazer as exigências da atividade. Não é utilizado.
    public void exibirStatus() {
        System.out.println("    Nome: " + this.nome);
        System.out.println("    Vida: " + this.pontosDeVida);
        System.out.println("    Força: " + this.forca);
        System.out.println("    Arma: " + arma.getNome() + " (Força: +"+ arma.getDano() + " | NívelMin: "+ arma.getMinNivel() + ")");
    }

    public boolean estaVivo(){
        return this.pontosDeVida > 0;
    }

    public void zerarVida() {
        this.pontosDeVida = 0;
    }

    public int receberDano(int dano) {
        dano = Math.min(dano, this.pontosDeVida);
        this.pontosDeVida -= dano;
        System.out.println(this.nome + " recebeu " + dano + " pontos de dano, ficando com " + this.pontosDeVida + " pontos de vida.");
        return dano;
    }

    public void receberCura(int cura) {
        this.pontosDeVida += cura;
        System.out.println(this.nome + " recebeu " + cura + " pontos de vida.");
    }

    public void receberForca(int forca) {
        this.forca += forca;
        System.out.println(this.nome + " recebeu " + forca + " pontos de força.");
    }

    public void receberArma(Arma arma) {
        this.arma = arma;
        System.out.println(this.getNome() + " equipou " + arma.getNome());
    }

    public abstract boolean atacar(Combatente alvo); // retorna true se o ataque faz o turno acabar.
}