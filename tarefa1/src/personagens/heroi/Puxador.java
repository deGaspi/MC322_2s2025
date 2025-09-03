package personagens.heroi;
import java.util.List;
import java.util.Random;

import io.IO;
import personagens.Personagem;
import personagens.monstro.Monstro;

public class Puxador extends Herói{
    private int swing;
    private Random random = new Random();

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness){
        super(name, LP, strength, level, xp);
        this.swing = shakeness;
    }

    

    @Override
    public boolean atacar(Personagem alvo){
        IO.msgCallback("Bumbum, Paticumbum, Prugurundum.");
        this.swing++;
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
        }
        return true;
    }

    @Override
    public boolean usarHabilidadeEspecial(Personagem alvo){
        if(this.swing <= 0){
            IO.msgCallback("Swing zerado");
            return false;
        }
        IO.msgCallback("Esperança Conquistada!!");
        int resultado = Math.round((float) (Math.pow(1.2, this.swing) - Math.pow(1.15, this.swing) + 1));
        this.receberForca(resultado / 2);
        this.receberCura(resultado);
        return true;
    }

    @Override
    public heroEnum getHeroType() {
        return heroEnum.PUXADOR;
    }

    @Override
    public List<String> getStatusList() {
         // Usado no lugar de exibirStatus(). Como não printa direto, pode ser usado pela classe IO.
        var statusList = super.getStatusList();
        statusList.add("Swing: " + swing);
        return statusList;
    }

    
}
