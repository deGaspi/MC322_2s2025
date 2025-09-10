package backend.heroi;

import java.util.List;

import backend.Personagem;
import backend.monstro.Monstro;

public class Passista extends Herói {
    private int requebrado;

    public Passista(String name, int LP, int strength, int level, int xp, int shakeness) {
        super(name, LP, strength, level, xp);
        this.requebrado = shakeness;
    }

    @Override
    public boolean atacar(Personagem alvo) {
        System.out.println("Conselho dado.");
        this.requebrado++;
        super.atacar(alvo);
        return true;
    }

    @Override
    public boolean usarHabilidadeEspecial(Personagem alvo) {
        if (alvo instanceof Monstro monstro) {
            if (this.requebrado < 4) {
                System.out.println("Requebrdo insuficiente. Precisa ser maior que 7.");
                return false;
            }
            this.requebrado -= 4;

            switch (monstro.getTipo()) {
                case FALSO_PATRIOTA:
                    System.out.println("Explodiu o coração do falso patriota");
                    alvo.receberDano(alvo.getPontosDeVida());
                    break;
                case ENTREGUISTA:
                    alvo.receberDano(10);
                    break;
                case IMPERIALISTA:
                    alvo.receberDano(10);
                    break;
                default:
                    break;
            }
            return true;
        }
        throw new RuntimeException(); // Classe abstrata permite usar habilidade em Heroi. Mas isso não faz sentido.
    }

    @Override
    public heroEnum getTipo() {
        return heroEnum.PASSISTA;
    }

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("Requebrado: " + requebrado);
        return statusList;
    }

}