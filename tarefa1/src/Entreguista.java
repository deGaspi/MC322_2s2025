import java.util.Random;

public class Entreguista extends Monstro{  // Ele vai roubar vida do Herói ou do Falso Patriota para entregar para o Imperialista
    public Imperialista i;

    Random random = new Random();

    public Entreguista(String name, int LP, int strength, int xp, Imperialista imp){
        super(name, LP, strength, xp, Classe.Entreguista);
        this.i = imp;
    }

    public int atacar(Personagem alvo){
        System.out.println("Entreguista privatiza vida de " + alvo.getClasse().name());
        float dano = this.forca * (random.nextInt(2) + (random.nextInt(101) / 100)) + 0.2f;
        int n = alvo.receberDano(Math.round(dano));
        i.receberCura(n);
        return 1;
    }
}
