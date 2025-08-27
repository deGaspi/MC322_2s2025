public abstract class Personagem {
    private String nome;
    private Integer pontosDeVida;
    private Integer forca;

    public Personagem(String name, Integer LP, Integer strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
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