package classes.acoes;

import classes.heroi.Herói;
import classes.interfaces.AcaoDeCombate;
import classes.interfaces.Combatente;

// Ataque Especial usado pelos heróis
public class Especial implements AcaoDeCombate{
    public Especial(){}

    public void executar(Combatente usuario, Combatente alvo){
        if (usuario instanceof Herói hero) {
            hero.usarHabilidadeEspecial(alvo);
        }
    }
}
