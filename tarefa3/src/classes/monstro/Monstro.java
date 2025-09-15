package classes.monstro;

import java.util.Random;

import classes.Personagem;
import classes.armas.Arma;
import classes.armas.Chinelo;
import classes.armas.Lança;
import classes.armas.Repique;
import classes.interfaces.Lootavel;
import classes.interfaces.Item;

public abstract class Monstro extends Personagem implements Lootavel{
    private Random random = new Random();

    private int xpConcedido;

    private Arma[] listaDeArmas = {new Chinelo(), new Lança(), new Repique()};
    
    private static float sorte = 0.5f;

    public Monstro(String name, int LP, int strength, int xp) {
        super(name, LP, strength);
        this.xpConcedido = xp;
        switch(random.nextInt(6)){
            case 0:
                this.receberArma(new Repique()); // 1/6 de chance
            case 1:
                this.receberArma(new Lança());   // 2/6 de chance
            case 2:
                this.receberArma(new Lança());   
            default:
                this.receberArma(new Chinelo()); // 3/6 de chance
        }
    }

    public int getXpConcedido() {
        return this.xpConcedido;
    }

    public float getSorte(){
        return sorte;
    }

    public static enum monstroEnum {
        // Enum para facilitar implementação futura de novos monstros. Basta alterar
        // aqui, e não vários arquivos.
        ENTREGUISTA("Entreguista"),
        IMPERIALISTA("Imperialista"),
        FALSO_PATRIOTA("Falso Patriota");
        
        private final String description;

        monstroEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public Item droparLoot(){
        return this.getArma();
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("    XP concedido: " + this.xpConcedido);
    }


    public abstract monstroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.
}
