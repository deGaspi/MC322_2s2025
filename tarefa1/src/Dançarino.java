import java.util.Random;

public class Dançarino extends Herói{
    private int requebrado;

     Random random = new Random();

    public Dançarino(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp);
        this.requebrado = shakeness;
    }

      public String getClassName(){
        return "Dançarino";
    }

    public int atacar(Personagem alvo){
        float dano = this.forca * (random.nextInt(3) + (random.nextInt(101) / 100));
        int n = alvo.receberDano(Math.round(dano));
        this.requebrado++;
        System.out.println("Conselho dado " + n + " de dano");

        return 1;
    }
    
    public int usarHabilidadeEspecial(Personagem alvo){
        if (this.requebrado < 7){
            System.out.println("Requebrdo insuficiente");
            return 0;
        }
        this.requebrado -= 7;


        if(alvo.getClassName() == "FalsoPatriota"){
            alvo.pontosDeVida = 0;
            System.out.println("Explodiu o coração do falso patriota");
        }else if(alvo.getClassName() == "Entreguista"){
            alvo.receberDano(20);
            System.out.println("Entreguista tomou 20 de dano");
        }else{
            alvo.receberDano(30);
            System.out.println("Imperialista tomou 30 de dano");
        }

        return 1;

    }

}