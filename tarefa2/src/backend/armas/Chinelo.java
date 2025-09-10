package backend.armas;

public class Chinelo extends Arma{
    private String nome;

    public Chinelo(){
        super(3, 0);
        this.nome = "Chinelo";
    }   

    public String getNome(){
        return this.nome;
    }
}
