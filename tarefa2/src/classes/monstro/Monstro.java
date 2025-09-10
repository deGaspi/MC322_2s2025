package classes.monstro;

import java.util.List;
import java.util.Random;

import classes.Personagem;
import classes.armas.Arma;
import classes.armas.Chinelo;
import classes.armas.Lança;
import classes.armas.Repique;

public abstract class Monstro extends Personagem {
    private Random random = new Random();

    private int xpConcedido;

    private Arma repique = new Repique();
    private Arma lanca = new Lança();
    private Arma chinelo = new Chinelo();
    private Arma[] listaDeArmasParaLargar = {repique, lanca, chinelo};

    public Monstro(String name, int LP, int strength, int xp) {
        super(name, LP, strength);
        this.xpConcedido = xp;
    }

    public int getXpConcedido() {
        return this.xpConcedido;
    }

    public static enum monstroEnum {
        // Enum para facilitar implementação futura de novos monstros. Basta alterar
        // aqui, e não vários arquivos.
        ENTREGUISTA("Entreguista"),
        IMPERIALISTA("Imperialista"),
        FALSO_PATRIOTA("Falso Patriota");
        
        private final String description;

        monstroEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public abstract monstroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("XP: " + xpConcedido);
        statusList.add("Classe: " + this.getTipo().getDescription());
        return statusList;
    }

    @Override
    public void exibirStatus() {
        System.out.println("Nome: " + super.getNome());
        System.out.println("Vida: " + super.getPontosDeVida());
        System.out.println("Força: " + super.getForca());
        System.out.println("XP concedido: " + this.xpConcedido);
    }

    public Arma largaArma(){
        int index = random.nextInt(3);
        return listaDeArmasParaLargar[index];
    }
}
