package rpg.monstro;

import java.util.ArrayList;
import java.util.List;

import rpg.acoes.Ataque;
import rpg.armas.Arma;
import rpg.heroi.Personagem;
import rpg.interfaces.AcaoDeCombate;
import rpg.interfaces.Item;
import rpg.interfaces.Lootavel;
import rpg.cenarios.Dificuldade;


public abstract class Monstro extends Personagem implements Lootavel{
    private List<AcaoDeCombate> acoes = new ArrayList<AcaoDeCombate>(); // TODO: essa variável é nonsense.

    private int xpConcedido;

    public Monstro(String name, int LP, int strength, int xp, Arma arma, Dificuldade dif) {
        super(name, LP*dif.valor, strength*dif.valor, 0.5f, 0);
        this.xpConcedido = xp*dif.valor;        
        acoes.add(new Ataque());// TODO: dar um jeito de trocar as probabilidades.
        super.setArma(arma);
        
    }

    public int getXpConcedido() {
        return this.xpConcedido;
    }

    @Override
    public AcaoDeCombate escolherAcao() {
        return acoes.get(0);
    }

    
    public Item droparLoot(){
        return this.getArma();
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("    XP concedido: " + this.xpConcedido);
    }


    public abstract MonstroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.
}
