package backend.heroi;

import java.util.List;

import backend.Batalha;
import backend.Personagem;

public class Puxador extends Herói {
    private int swing;

    public Puxador(String name, int LP, int strength, int level, int xp, int shakeness) {
        super(name, LP, strength, level, xp);
        this.swing = shakeness;
    }

    @Override
    public boolean atacar(Personagem alvo) {
        Batalha.addPostRoundMessage("Bumbum, Paticumbum, Prugurundum.");
        this.swing++;
        super.atacar(alvo);
        return true;
    }

    @Override
    public boolean usarHabilidadeEspecial(Personagem alvo) {
        if (this.swing <= 0) {
            Batalha.addPostRoundMessage("Swing zerado");
            return false;
        }
        Batalha.addPostRoundMessage("Esperança Conquistada!!");
        int resultado = Math.round((float) (Math.pow(1.4, this.swing)));
        this.receberCura(resultado);
        return true;
    }

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("Swing: " + swing);
        return statusList;
    }

    @Override
    public heroEnum getTipo() {
        return heroEnum.PUXADOR;
    }

}
