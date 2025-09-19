package classes.monstro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import classes.Personagem;
import classes.acoes.Ataque;
import classes.armas.Arma;
import classes.interfaces.Lootavel;
import classes.interfaces.AcaoDeCombate;
import classes.interfaces.Item;

public abstract class Monstro extends Personagem implements Lootavel{
    private Random random = new Random();
    private List<AcaoDeCombate> acoes = new ArrayList<AcaoDeCombate>(); // TODO: essa variável é nonsense.

    private int xpConcedido;

    public Monstro(String name, int LP, int strength, int xp) {
        super(name, LP, strength, 0.5f, 0);
        this.xpConcedido = xp;
        this.receberArma(Arma.values()[random.nextInt(Arma.values().length)]); // TODO: dar um jeito de trocar as probabilidades.
        acoes.add(new Ataque());
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
