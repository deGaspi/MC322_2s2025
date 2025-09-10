package classes.monstro;

import java.util.Random;
import classes.Personagem;

public class FalsoPatriota extends Monstro {
    private Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp) {
        super(name, LP, strength, xp);
    }

    public boolean atacar(Personagem alvo) {
        float dano = (this.getForca() * random.nextFloat());
        alvo.receberDano(Math.round(dano));
        System.out.println("Falso Patriota atacou com música gringa");
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.FALSO_PATRIOTA;
    }
}
