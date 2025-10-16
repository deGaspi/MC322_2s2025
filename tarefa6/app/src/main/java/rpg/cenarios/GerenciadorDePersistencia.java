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

    public void salvarJogo(String nome, int faseAtual, Dificuldade dif, Heroi H){

    }

    public  ArrayList<Fase> carregarJogo(String nome){

    }
}
