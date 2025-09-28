package classes.armas;

import classes.cenarios.Dificuldade;

public class SemArma extends Arma {
    public SemArma(){
        super(0, 0, "Desarmado", Dificuldade.FACIL);
    }   
}
