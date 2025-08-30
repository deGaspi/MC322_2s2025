public abstract class Heroi extends Personagem {
    private Integer nivel;
    private Integer xp;
    
    public Heroi(
        String nome,
        Integer pontosDeVida,
        Integer forca,
        Integer nivel,
        Integer xp
    ) {
        super(nome, pontosDeVida, forca);
        this.nivel = nivel;
        this.xp = xp;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public Integer getXp() {
        return this.xp;
    }

    public void ganharXp(Integer xp) {
        this.xp += xp;
    }

    public void exibirStatus() {
        System.out.println(
            "Nome: " + this.getNome() +
            ", Vida: " + this.getPontosDeVida() +
            ", Ataque: " + this.getForca() +
            ", Nível: " + this.nivel +
            ", XP: " + this.xp
        );
    }

    public abstract void usarHabilidadeEspecial(Personagem alvo);
}