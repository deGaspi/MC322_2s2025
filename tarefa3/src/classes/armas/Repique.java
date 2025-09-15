package classes.armas;

public class Repique extends Arma{
    private String nome;

    public Repique(){
        super(15, 15);
        this.nome = "Repique-mor";
    }   

    public String getNome(){
        return this.nome;
    }
}
