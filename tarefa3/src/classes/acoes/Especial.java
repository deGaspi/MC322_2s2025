package classes.acoes;

import classes.interfaces.AcaoDeCombate;
import classes.interfaces.Combatente;

// Ataque Especial usado pelos heróis
public class Especial implements AcaoDeCombate{
    public Especial(){}

    public int executar(Combatente usuario, Combatente alvo){
        return usuario.usarHabilidadeEspecial(alvo);
    }
}
