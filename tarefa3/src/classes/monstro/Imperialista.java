package classes.monstro;

import java.util.Random;
import java.util.ArrayList;

import classes.interfaces.AcaoDeCombate;
import classes.interfaces.Combatente;
import classes.acoes.Ataque;

public class Imperialista extends Monstro{
    private Random random = new Random();
    private ArrayList<AcaoDeCombate> acoes = new ArrayList<AcaoDeCombate>();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
        acoes.add(new Ataque());
    }

     public boolean escolherAcao(Combatente alvo){
        acoes.get(0).executar(this, alvo);
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.IMPERIALISTA;
    }




    public boolean atacar(Combatente alvo){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        float dano = (this.getForca() * random.nextFloat() * 3);
        alvo.receberDano(Math.round(dano));
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