import java.util.Random;

public class FalsoPatriota extends Monstro{
    
    Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp, Classe.FalsoPatriota);
    }

    public int atacar(Personagem alvo){
        float dano = this.forca * (random.nextInt(2) + (random.nextInt(101) / 100));
        System.out.println("Falso Patriota atacou com música gringa");
        alvo.receberDano(Math.round(dano));
        return 1;
    }
}
