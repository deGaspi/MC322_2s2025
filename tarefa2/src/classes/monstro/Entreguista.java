package classes.monstro;
import java.util.Random;

import classes.Personagem;

public class Entreguista extends Monstro{  // Ele vai roubar vida do Herói ou do Falso Patriota para entregar para o Imperialista
    private Imperialista imperialista;
    private Random random = new Random();

    public Entreguista(String name, int LP, int strength, int xp, Imperialista imperialista){
        super(name, LP, strength, xp);
        this.imperialista = imperialista;
    }

    public boolean atacar(Personagem alvo){
        float dano = (this.getForca() * random.nextFloat() * 2) + 2;
        System.out.println("\nEntreguista vai privatizar a vida de " + alvo.getNome() + "!!!");
        int n = alvo.receberDano(Math.round(dano));
        imperialista.receberCura(n);
        return true;
    }

    @Override
    public monstroEnum getTipo() {
        return monstroEnum.ENTREGUISTA;
    }
} 
