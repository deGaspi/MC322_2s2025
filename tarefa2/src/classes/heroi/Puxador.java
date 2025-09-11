package classes.heroi;

import java.util.List;
import java.util.Random;

import classes.Personagem;

public class Puxador extends Herói {
    private int swing;
    private Random random;

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness) {
        super(name, LP, strength, level, xp);
        this.swing = shakeness;
    }

    @Override
    public boolean atacar(Personagem alvo) {
        int r = random.nextInt(3);
        if(r == 0){                            //1/3 de chance de usar a habilidade especial
            this.usarHabilidadeEspecial(alvo);
            return true;
        }
        System.out.println("Bumbum, Paticumbum, Prugurundum.");
        this.swing++;
        super.atacar(alvo);
        return true;
    }

    @Override
    public boolean usarHabilidadeEspecial(Personagem alvo) {
        if (this.swing <= 0) {
            System.out.println("Swing zerado");
            return false;
        }
        System.out.println("Esperança Conquistada!!");
        int resultado = Math.round((float) (Math.pow(1.4, this.swing)));
        this.receberCura(resultado);
        return true;
    }

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("Swing: " + swing);
        return statusList;
    }

    @Override
    public heroEnum getTipo() {
        return heroEnum.PUXADOR;
    }

}
