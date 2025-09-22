package com.rpglab.personagens.heroi;

import java.util.Random;

import com.rpglab.combate.*;
import com.rpglab.itens.Arma;
import com.rpglab.personagens.*;
import com.rpglab.personagens.monstro.Monstro;

import java.util.ArrayList;
import java.util.List;

public abstract class Herói extends Personagem {
    private int xp;
    private int expProxNivel = 20;
    private List<AcaoDeCombate> actionList = new ArrayList<AcaoDeCombate>(); // TODO: esse atributo é desnecessário.
    private int pontosEspecial;

    public Herói(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength, new Random().nextFloat(), level);
        this.xp = xp;
        pontosEspecial = 0;
        actionList.add(new Ataque());
        actionList.add(new Especial());
    }

    public int getPontosEspecial() {
        return pontosEspecial;
    }

    public void setPontosEspecial(int set) {
        pontosEspecial = set;
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("    Experiência: " + xp);
        System.out.println("    Tipo: " + getHeroType().getTypeName());
    }

    private void subirDeNivel() {
        if (xp >= expProxNivel) {
            var novoNivel = xp / expProxNivel;
            receberLvl(novoNivel + novoNivel);
            this.xp %= expProxNivel;
            this.expProxNivel += 2;
            receberCura(novoNivel * 8);
        }
    }

    public void ganharExperiencia(int xp) {
        xp = Math.round(xp * getSorte() * 2);
        System.out.println(this.getNome() + " recebeu " + xp + " pontos de experiência.");
        this.xp += xp;
        subirDeNivel();
    }

    @Override
    public AcaoDeCombate escolherAcao() {
        if (new Random().nextInt(3) == 0) {
            return actionList.get(1); // TODO: dar um jeito de não usar get().
        } else {
            return actionList.get(0); // TODO: dar um jeito de não usar get().
        }
    }

    public abstract HeroEnum getHeroType();
    public abstract int usarHabilidadeEspecial(Combatente alvo);

    @Override
    public void darDano(Combatente alvo, int dano) {
        super.darDano(alvo, dano);
        if (alvo.getPontosDeVida() == 0) {
            if (alvo instanceof Lootavel lootavel) {
                var loot = lootavel.droparLoot();
                if (loot instanceof Arma arma && arma.getDano() > getArma().getDano()) {
                    this.receberArma(arma);
                }
            }
            if (alvo instanceof Monstro monstro) {
                this.ganharExperiencia(monstro.getXpConcedido());
            }
        }
        pontosEspecial++;
    }
}
