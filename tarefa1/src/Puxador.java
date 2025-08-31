import java.util.Random;

public class Puxador extends Herói{
    private int swing;

     Random random = new Random();

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp, Classe.Puxador);
        this.swing = shakeness;
    }

    public int atacar(Personagem alvo){
        System.out.println("Bumbum, Paticumbum, Prugurundum");
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        this.swing++;
        return 1;
    }

    public int usarHabilidadeEspecial(Personagem alvo){
        if(this.swing <= 0){
            System.out.println("Swing zerado");
            return 0;
        }
        int resultado = Math.round((float) (Math.pow(1.2, this.swing) - Math.pow(1.15, this.swing) + 1));
        System.out.println("Esperança reconquistada");
        this.receberForca(resultado / 2);
        this.receberCura(resultado);
        return 1;
    }

    
}
