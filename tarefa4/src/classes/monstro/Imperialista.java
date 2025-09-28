package classes.monstro;

import classes.armas.Arma;
import classes.interfaces.Combatente;

public class Imperialista extends Monstro{
    public Imperialista(String name, int LP, int strength, int xp, Arma arma){
        super(name, LP, strength, xp, arma);
    }

    @Override
    public MonstroEnum getTipo() {
        return MonstroEnum.IMPERIALISTA;
    }

    @Override
    public void darDano(Combatente alvo, int dano){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        super.darDano(alvo, dano);
    }
}