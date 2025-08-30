public abstract class Monstro extends Personagem{
    private int xpConcedido;

    public Monstro(String name, int LP, int strength, int xp){
        super(name, LP, strength);
        this.xpConcedido = xp;
    }

    
}
