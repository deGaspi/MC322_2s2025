package classes.heroi;

import classes.interfaces.Combatente;

public class Puxador extends Herói {

    public Puxador(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength, level, xp);
    }

    @Override
    public HeroEnum getHeroType() {
        return HeroEnum.PUXADOR;
    }

    @Override
    public int usarHabilidadeEspecial(Combatente alvo) {
        if (super.getPontosEspecial() <= 0) {
            System.out.println("Swing zerado");
            return 0;
        }
        System.out.println("Esperança Conquistada!!");
        int resultado = Math.round((float) (Math.pow(1.4, super.getPontosEspecial())));
        super.setPontosEspecial(0);
        this.receberCura(resultado);
        return 0;
    }
}
