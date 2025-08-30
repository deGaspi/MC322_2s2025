public abstract class Personagem {
    private String nome;
    private Integer pontosDeVida;
    private Integer forca;

    public Personagem(String nome, Integer pontosDeVida, Integer forca) {
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
        this.forca = forca;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getPontosDeVida() {
        return this.pontosDeVida;
    }

    public Integer getForca() {
        return this.forca;
    }

    public void receberDano(Integer dano) {
        this.pontosDeVida = Math.max(0, this.pontosDeVida - dano);
    }

    public void exibirStatus() {
        System.out.println(
            "Nome: " + this.nome +
            ", Vida: " + this.pontosDeVida +
            ", Ataque: " + this.forca
        );
    }

    public void atacar(Personagem alvo) {
        alvo.receberDano(this.forca);
    }
}