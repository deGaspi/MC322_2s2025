package personagens.heroi;

import personagens.Personagem;

public class Puxador extends Herói{
    private int swing;

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp, Classe.Puxador);
        this.swing = shakeness;
    }

    public int atacar(Personagem alvo){
        System.out.println("Bumbum, Paticumbum, Prugurundum");
        this.swing++;
        super.atacar(alvo);
        return 1;
    }

    public boolean usarHabilidadeEspecial(Personagem alvo){
        if(this.swing <= 0){
            System.out.println("Swing zerado");
            return false;
        }
        int resultado = Math.round((float) (Math.pow(1.2, this.swing) - Math.pow(1.15, this.swing) + 1));
        System.out.println("Esperança reconquistada");
        this.receberCura(resultado);
        return true;
    }

    
}
