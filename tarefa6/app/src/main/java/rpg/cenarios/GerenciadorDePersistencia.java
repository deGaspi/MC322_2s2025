package rpg.cenarios;

import java.util.ArrayList;

import rpg.interfaces.Fase;
import rpg.cenarios.Dificuldade;
import rpg.heroi.Heroi;

/**
 * Do jeito que a tarefa pede eu só consigo pensar em jeitos que
 * reestruturaria boa parte do código, como eu não quero ficar maluco,
 * irei fazer assim:
 * carregar jogo construirá as fases restantes e as colocará em um array o qual será
 * percorrido pela classe jogo
 * o método salvar jogo irá salvar as informações necessárias em um arquivo xml
 */
public class GerenciadorDePersistencia {
    private Heroi h;
    private int faseInicial;
    private ArrayList<Fase> listaDeFases;
    private Dificuldade dif;


    public void salvarJogo(String nome, int faseAtual, Dificuldade dif, Heroi H){

    }

    public void carregarJogo(String nome) throws Exception{

    }

    public int getFaseInicial(){
        return faseInicial;
    }
    public ArrayList<Fase> getListaDeFases(){
        return listaDeFases;
    }
    public Heroi getHeroi(){
        return h;
    }
    public Dificuldade getDificuldade(){
        return dif;
    }

}
