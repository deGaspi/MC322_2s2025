package rpg.util;
/**
 * Classe para capiturar a exceção do 
 * jogador desistir do jogo ou pedir 
 * para salvar, no último caso, 
 * salvamento será true
 */
public class paradaJogador extends Exception {
    private boolean salvamento;

    public paradaJogador(String msg, boolean s){
        super(msg);
        salvamento = s;
    }

    public boolean toSave(){
        return salvamento;
    }
}
