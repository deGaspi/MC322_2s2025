package com.rpg.heroi;

import com.rpg.interfaces.Combatente;
import com.rpg.monstro.Monstro;

public class Passista extends Herói {

    public Passista(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength, level, xp);
    }

    @Override
    public HeroEnum getHeroType() {
        return HeroEnum.PASSISTA;
    }

    @Override
    public int usarHabilidadeEspecial(Combatente alvo) {
        if (alvo instanceof Monstro monstro) {
            if (super.getPontosEspecial() < 4) {
                System.out.println("Requebrdo insuficiente. Precisa ser maior que 4.");
                return 0;
            }
            setPontosEspecial(getPontosEspecial()-4);

            switch (monstro.getTipo()) {
                case FALSO_PATRIOTA:
                    System.out.println("Explodiu o coração do falso patriota");
                    int dano = alvo.getPontosDeVida();
                    alvo.receberDano(dano);
                    return dano;
                case IMPERIALISTA:
                    alvo.receberDano(10);
                    return 10;
                default:
                    return 0;
            }
            
        }
        throw new RuntimeException(); // Classe abstrata permite usar habilidade em Heroi. Mas isso não faz sentido.
    }

    
}