import java.util.Random;

public class Puxador extends Herói{
    private int swing;

     Random random = new Random();

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp);
        this.swing = shakeness;
    }

    public String getClassName(){
        return "Puxador";
    }

    public int atacar(Personagem alvo){
        float dano = this.forca * (random.nextInt(3) + (random.nextInt(101) / 100));
        int n = alvo.receberDano(Math.round(dano));
        this.swing++;
        System.out.println("Bumbum, Paticumbum, Prugurundum " + n + " de dano");

        return 1;
    }

    public int usarHabilidadeEspecial(Personagem alvo){
        if(this.swing <= 0){
            System.out.println("Swing zerado");
            return 0;
        }
        float resultado = Math.round((float) (Math.pow(1.2, this.swing) - Math.pow(1.15, this.swing) + 1));

        this.forca += resultado / 2;
        this.pontosDeVida += resultado;

        System.out.println("Esperança reconquistada: " + this.forca + this.pontosDeVida);

        return 1;
    }

    
}
