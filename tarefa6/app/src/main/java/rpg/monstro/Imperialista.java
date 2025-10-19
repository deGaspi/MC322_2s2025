package rpg.monstro;

import rpg.armas.Arma;
import rpg.cenarios.Dificuldade;
import rpg.interfaces.Combatente;

public class Imperialista extends Monstro{
    public Imperialista(String name, int LP, int strength, int xp, Arma arma, Dificuldade dif){
        super(name, LP, strength, xp, arma, dif);
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

    @Override
    public void usarHabilidadeEspecial(Combatente alvo) {
    }
}