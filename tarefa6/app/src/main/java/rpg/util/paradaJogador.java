package rpg.util;
/**
 * Se o personagem desiste do jogo ou pede para salvar,
 * no último caso, salvamento será true
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
