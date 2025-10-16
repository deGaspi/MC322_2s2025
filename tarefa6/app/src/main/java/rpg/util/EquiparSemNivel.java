package rpg.util;

/**
 * Se o heroi tenta equipar uma arma de nivel muito alto
 */
public class EquiparSemNivel extends Exception{
    public EquiparSemNivel(String msg) {
        super(msg);
    }
}
