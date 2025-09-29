package com.rpg.heroi;

import java.util.Random;

import com.rpg.acoes.Ataque;
import com.rpg.acoes.Especial;
import com.rpg.armas.Arma;
import com.rpg.interfaces.AcaoDeCombate;
import com.rpg.interfaces.Combatente;
import com.rpg.monstro.Monstro;
import com.rpg.util.EquiparSemNivel;

import java.util.ArrayList;
import java.util.List;

public abstract class Herói extends Personagem {
    private int xp;
    private int expProxNivel = 15;
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
            this.expProxNivel += 5;
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
            if (alvo instanceof Monstro monstro) {
                this.ganharExperiencia(monstro.getXpConcedido());
            }
        }
        pontosEspecial++;
    }

    @Override
    public void receberArma(Arma arma) throws EquiparSemNivel{
        if(arma.getMinLvl() > getNivel()){
            throw new EquiparSemNivel(this, arma);
        }else{ 
            super.setArma(arma);
            System.out.println(this.getNome() + " equipou " + arma.getNome());
        }
    }
}
