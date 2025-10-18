package rpg.acoes;

import rpg.interfaces.AcaoDeCombate;
import rpg.interfaces.Combatente;

/**
 * Ataque Especial dos heróis
 */
public class Especial implements AcaoDeCombate{
    public Especial(){}

    public void executar(Combatente usuario, Combatente alvo){
        usuario.usarHabilidadeEspecial(alvo);
    }
}
