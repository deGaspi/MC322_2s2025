import java.util.Random;

public class Imperialista extends Monstro{
    Random random = new Random();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp, Classe.Imperialista);
    }

    public int atacar(Personagem alvo){
        System.out.println("Imperialista avança o lobby para privatizar o carnaval");
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        return 1;
    }
}