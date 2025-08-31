public abstract class Personagem {
    public static enum Classe {
        Passista,
        Entreguista,
        FalsoPatriota,
        Puxador,
        Imperialista,
    }

    private String nome;
    public int pontosDeVida;
    public int forca;
    private Classe classe;

    public Personagem(String name, int LP, int strength, Classe classe) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
        this.classe = classe;
    }

    public int receberDano(int dano) {
        dano = Math.min(dano, this.pontosDeVida);
        this.pontosDeVida -= dano;
        return dano;
    }

    public void exibirStatus() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
    }

    public Classe getClasse() {
        return this.classe;
    }

    public abstract int atacar(Personagem alvo);
}