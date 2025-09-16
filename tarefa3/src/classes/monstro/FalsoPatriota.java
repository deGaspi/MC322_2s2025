package classes.monstro;

import java.util.Random;
import java.util.ArrayList;

import classes.interfaces.Combatente;
import classes.interfaces.AcaoDeCombate;
import classes.acoes.Ataque;;

public class FalsoPatriota extends Monstro {
    private Random random = new Random();

    private ArrayList<AcaoDeCombate> acoes = new ArrayList<AcaoDeCombate>();

    public FalsoPatriota(String name, int LP, int strength, int xp) {
        super(name, LP, strength, xp);
        acoes.add(new Ataque());
    }

     public boolean escolherAcao(Combatente alvo){
        acoes.get(0).executar(this, alvo);
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.FALSO_PATRIOTA;
    }



    

    public boolean atacar(Combatente alvo) {
        float dano = (this.getForca() * random.nextFloat());
        alvo.receberDano(Math.round(dano));
        System.out.println("Falso Patriota atacou com música gringa");
        return true;
    }
    //Estão aqui só para completar a interface, não goste, mas tenho outras prioridades
    public int usarHabilidadeEspecial(Combatente alvo){
        return 0;
    }

    public int getNivel(){
        return 0;
    }

}
