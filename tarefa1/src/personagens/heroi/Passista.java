package personagens.heroi;

import java.util.List;
import java.util.Random;

import io.IO;
import personagens.Personagem;
import personagens.monstro.Monstro;

public class Passista extends Herói {
    private int requebrado;
    private Random random = new Random();

    public Passista(String name, int LP, int strength, int level, int xp, int shakeness) {
        super(name, LP, strength, level, xp);
        this.requebrado = shakeness;
    }

    @Override
    public boolean atacar(Personagem alvo) {
        IO.msgCallback("Conselho Dado.");
        this.requebrado++;
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
        }
        return true;
    }

    @Override
    public boolean usarHabilidadeEspecial(Personagem alvo) {
        if (alvo instanceof Monstro monstro) {
            if (this.requebrado < 7) {
                IO.msgCallback("Requebrdo insuficiente");
                return false;
            }
            this.requebrado -= 7;
            switch (monstro.getMonsterType()) {
                case FALSO_PATRIOTA:
                    IO.msgCallback("Explodiu o coração do falso patriota");
                    alvo.receberDano(alvo.getPontosDeVida());    
                    break;
                case ENTREGUISTA:
                    alvo.receberDano(20);
                    break;
                case IMPERIALISTA:
                    alvo.receberDano(30);
                    break;
                default:
                    throw new RuntimeException();
            }
            return true;
        }
        IO.msgCallback("Alvo não é monstro.");
        return false;
    }

    @Override
    public heroEnum getHeroType() {
        return heroEnum.PASSISTA;
    }

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("Requebrado: " + requebrado);
        return statusList;
    }
}