public abstract class Personagem {
    private String nome;
    public int pontosDeVida;
    public int forca;

    public Personagem(String name, int LP, int strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
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

    public abstract String getClassName();
    public abstract int atacar(Personagem alvo);

    //Toda função de ação retorna 0 ou 1 para contabilizar a passagem, ou não, do turno
}