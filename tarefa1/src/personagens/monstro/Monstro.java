package personagens.monstro;

import personagens.Personagem;


public abstract class Monstro extends Personagem{
    private int xpConcedido;

    public Monstro(String name, int LP, int strength, int xp, Classe classe){
        super(name, LP, strength, classe);
        this.xpConcedido = xp;
    }

    public int getXpConcedido() {
        return this.xpConcedido; 
    }
    
}
