package personagens.monstro;

import java.util.Random;

import io.Batalha;
import personagens.Personagem;

public class FalsoPatriota extends Monstro {
    private Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp) {
        super(name, LP, strength, xp);
    }

    public boolean atacar(Personagem alvo) {
        float dano = (this.getForca() * random.nextFloat());
        System.out.println("Falso Patriota atacou com música gringa");
        alvo.receberDano(Math.round(dano));
        Batalha.addPostRoundMessage("Falso Patriota atacou com música gringa");
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.FALSO_PATRIOTA;
    }
}
