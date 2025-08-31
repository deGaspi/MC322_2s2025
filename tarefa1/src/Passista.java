import java.util.Random;

public class Passista extends Herói{
    private int requebrado;

     Random random = new Random();

    public Passista(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp, Classe.Passista);
        this.requebrado = shakeness;
    }


    public int atacar(Personagem alvo){
        System.out.println("Conselho dado");
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        this.requebrado++;
        return 1;
    }
    
    public int usarHabilidadeEspecial(Personagem alvo){
        if (this.requebrado < 7){
            System.out.println("Requebrdo insuficiente");
            return 0;
        }
        this.requebrado -= 7;

        switch (alvo.getClasse()) {
            case Classe.FalsoPatriota:
                alvo.receberDano(alvo.getPontosDeVida());
                System.out.println("Explodiu o coração do falso patriota");
                break;
            case Classe.Entreguista:
                alvo.receberDano(20);
                break;
            case Classe.Imperialista:
                alvo.receberDano(30);
                break;
            default:
                break;
        }
        return 1;
    }
}