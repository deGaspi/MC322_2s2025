package classes.armas;

public class Lança extends Arma{
    private String nome;

    public Lança(){
        super(9, 8);
        this.nome = "Lança de Porta Bandeira";
    }  

    public String getNome(){
        return this.nome;
    }
    
}
