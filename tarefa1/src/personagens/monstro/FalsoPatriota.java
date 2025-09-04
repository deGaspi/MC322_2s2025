package personagens.monstro;
import java.util.Random;

import personagens.Personagem;

public class FalsoPatriota extends Monstro{
    
    Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp, Classe.FalsoPatriota);
    }

    public int atacar(Personagem alvo){
        float dano = (this.getForca() * random.nextFloat());
        System.out.println("Falso Patriota atacou com música gringa");
        alvo.receberDano(Math.round(dano));
        return 1;
    }
}
