package personagens.monstro;
import java.util.Random;

import personagens.Personagem;

public class Imperialista extends Monstro{
    Random random = new Random();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp, Classe.Imperialista);
    }

    public int atacar(Personagem alvo){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        float dano = (this.getForca() * random.nextFloat() * 3);
        alvo.receberDano(Math.round(dano));
        return 1;
    }
}