package personagens.monstro;
import java.util.Random;

import io.IO;
import personagens.Personagem;

public class Entreguista extends Monstro{  // Ele vai roubar vida do Herói ou do Falso Patriota para entregar para o Imperialista
    public Imperialista imperialista;
    private Random random = new Random();

    public Entreguista(String name, int LP, int strength, int xp, Imperialista imperialista){
        super(name, LP, strength, xp);
        this.imperialista = imperialista;
    }

    public boolean atacar(Personagem alvo){
        float dano = this.getForca() * (random.nextInt(2) + (random.nextInt(101) / 100)) + 0.2f;
        int n = alvo.receberDano(Math.round(dano));
        imperialista.receberCura(n);
        IO.msgCallback("Entreguista privatiza vida de " + alvo.getName());
        return true;
    }

    @Override
    public MonsterTypes getMonsterType() {
        return MonsterTypes.ENTREGUISTA;
    }
}
