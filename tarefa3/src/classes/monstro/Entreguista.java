package classes.monstro;

import java.util.ArrayList;
import java.util.Random;

import classes.interfaces.AcaoDeCombate;
import classes.interfaces.Combatente;
import classes.acoes.Ataque;

public class Entreguista extends Monstro{  // Ele vai roubar vida do Herói ou do Falso Patriota para entregar para o Imperialista
    private Imperialista imperialista;
    private Random random = new Random();
    private ArrayList<AcaoDeCombate> acoes = new ArrayList<AcaoDeCombate>();

    public Entreguista(String name, int LP, int strength, int xp, Imperialista imperialista){
        super(name, LP, strength, xp);
        this.imperialista = imperialista;
        acoes.add(new Ataque());
    }

    public boolean escolherAcao(Combatente alvo){
        imperialista.receberCura(acoes.get(0).executar(this, alvo));
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.ENTREGUISTA;
    }








    public boolean atacar(Combatente alvo){
        if (random.nextFloat() < 0.2) { // 20% de chance de fugir.
            System.out.println("\nO entreguista fugiu para os Estados Unidos, deixando o imperialista vulnerável");
            this.zerarVida(); // Ele "morre" mas não dá xp para o heroi.
        } else {
            float dano = (this.getForca() * random.nextFloat() * 2) + 2;
            System.out.println("\nEntreguista vai privatizar a vida de " + alvo.getNome() + "!!!");
            int n = alvo.receberDano(Math.round(dano));
            imperialista.receberCura(n);
        }
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
