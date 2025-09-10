package classes.armas;

public class SemArma extends Arma {
    private String nome;

    public SemArma(){
        super(0, 0);
        this.nome = "Desarmado";
    }   

    public String getNome(){
        return this.nome;
    }
}
