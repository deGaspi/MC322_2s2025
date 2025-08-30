import java.util.Random;

public class FalsoPatriota extends Monstro{
    
    Random random = new Random();

    public FalsoPatriota(String name, int LP, int strength, int xp){
        super(name, LP, strength, xp);
    }

    public String getClassName(){
        return "FalsoPatriota";
    }

    public int atacar(Personagem alvo){
        float dano = this.forca * (random.nextInt(2) + (random.nextInt(101) / 100));
        int n = alvo.receberDano(Math.round(dano));
        System.out.println("Falso Patriota atacou com música gringa causando " + n + " de dano");

        return 1;
    }
}
