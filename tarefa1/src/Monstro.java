public class Monstro extends Personagem {
    private Integer xpConcedido;

    public Monstro(
        String nome,
        Integer pontosDeVida,
        Integer forca,
        Integer xpConcedido
    ) {
        super(nome, pontosDeVida, forca);
        this.xpConcedido = xpConcedido;
    }

    public Integer getXpConcedido() {
        return this.xpConcedido;
    }

    public void exibirStatus() {
        System.out.println(
            "Nome: " + this.getNome() +
            ", Vida: " + this.getPontosDeVida() +
            ", Ataque: " + this.getForca() +
            ", XP Concedido: " + this.xpConcedido
        );
    }
}
