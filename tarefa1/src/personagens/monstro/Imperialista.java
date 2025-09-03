package personagens.monstro;
import java.util.Random;

import io.IO;
import personagens.Personagem;

public class Imperialista extends Monstro{
    private Random random = new Random();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
    }

    public boolean atacar(Personagem alvo){
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        IO.msgCallback("Imperialista avança o lobby para privatizar o carnaval");
        return true;
    }

    @Override
    public MonsterTypes getMonsterType() {
        return MonsterTypes.IMPERIALISTA;
    }
}