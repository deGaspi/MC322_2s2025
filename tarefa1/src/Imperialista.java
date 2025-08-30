import java.util.Random;

public class Imperialista extends Monstro{
    Random random = new Random();

    public Imperialista(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
    }

    public String getClassName(){
        return "Imperialista";
    }

    public int atacar(Personagem alvo){
        float dano = this.forca * (random.nextInt(3) + (random.nextInt(101) / 100));
        int n = alvo.receberDano(Math.round(dano));
        System.out.println("Imperialista avança o lobby para privatizar o carnaval " + n + " de dano");

        return 1;
    }
}