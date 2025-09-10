package backend.armas;

public abstract class Arma {
    private int dano;
    private int minNivel;

    public Arma(int damage, int minLevel){
        this.dano = damage;
        this.dano = minLevel;
    }

    public int getDano(){
        return dano;
    }

    public int getMinNivel(){
        return minNivel;
    }

    public static String[] getArmas() {
        return new String[]{"chinelo", "lança", "repique"};
    }

    public abstract String getNome();
}
