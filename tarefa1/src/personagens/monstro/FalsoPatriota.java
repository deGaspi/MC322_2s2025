package personagens.monstro;
import java.util.Random;

import io.IO;
import personagens.Personagem;

public class FalsoPatriota extends Monstro{
    private Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
    }

    public boolean atacar(Personagem alvo){
        float dano = this.getForca() * (random.nextInt(2) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        IO.msgCallback("Falso Patriota atacou com música gringa");
        return true;
    }

    @Override
    public MonsterTypes getMonsterType() {
        return MonsterTypes.FALSO_PATRIOTA;
    }
}
