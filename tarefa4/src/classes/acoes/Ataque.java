package classes.acoes;

import classes.interfaces.AcaoDeCombate;
import classes.armas.Arma;
import classes.armas.SemArma;
import classes.interfaces.Combatente;

// Ataque padrão, usado por todods
public class Ataque implements AcaoDeCombate{
    public Ataque(){}

    public void executar(Combatente usuario, Combatente alvo){
        Arma arma = usuario.getArma();
        float dano = usuario.getForca() * usuario.getSorte() * 2 + usuario.getNivel() * 0.5f + arma.getDano();
        if (arma instanceof SemArma)
            System.out.println(usuario.getNome() + " ataca desarmado.");
        else 
            System.out.println(usuario.getNome() + " ataca com " + arma.getNome() + ".");
        usuario.darDano(alvo, Math.round(dano));
        
    }
}
