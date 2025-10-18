package rpg.heroi;

import rpg.interfaces.Combatente;

public class Puxador extends Heroi {

    public Puxador(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength, level, xp);
    }

    @Override
    public HeroEnum getHeroType() {
        return HeroEnum.PUXADOR;
    }

    @Override
    public int usarHabilidadeEspecial(Combatente alvo) {
        if (getPontosEspecial() <= 0) {
            System.out.println("Swing zerado");
            return 0;
        }
        System.out.println("Esperança Conquistada!!");
        int resultado = Math.round((float) (Math.pow(1.4, getPontosEspecial())));
        setPontosEspecial(0);
        receberCura(resultado);
        return 0;
    }
}
