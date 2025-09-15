package classes.interfaces;

import classes.armas.Arma;

public interface Combatente {
    public String getNome();
    public boolean estaVivo();
    public int receberDano(int dano);
    public void receberCura(int cura);
    public boolean escolherAcao(Combatente Alvo);
    public int getPontosDeVida();
    public int getForca();
    public float getSorte();
    public Arma getArma();
    public int getNivel();
    public int usarHabilidadeEspecial(Combatente alvo);
}
