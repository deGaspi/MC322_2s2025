package backend.monstro;
import java.util.Random;

import backend.Batalha;
import backend.Personagem;

public class Imperialista extends Monstro{
    private Random random = new Random();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
    }

    public boolean atacar(Personagem alvo){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        float dano = (this.getForca() * random.nextFloat() * 3);
        alvo.receberDano(Math.round(dano));
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.IMPERIALISTA;
    }
}