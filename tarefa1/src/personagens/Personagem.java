package personagens;
public abstract class Personagem {
    public static enum Classe {
        Passista,
        Entreguista,
        FalsoPatriota,
        Puxador,
        Imperialista,
    }

    private String nome;
    private int pontosDeVida;
    private int forca;
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
        System.out.println(this.classe.name() + " tomou " + dano + " de dano");
        return dano;
    }

    public void receberCura(int cura) {
        this.pontosDeVida += cura;
        System.out.println(this.classe.name() + " recebeu " + cura + " de cura");
    }

    public void receberForca(int forca) {
        this.forca += forca;
        System.out.println(this.classe.name() + " ganha " + forca + " pontos de força");
    }

    public void exibirStatus() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
    }

    public Classe getClasse() {
        return this.classe;
    }

    public int getPontosDeVida() {
        return this.pontosDeVida;
    }

    public int getForca() {
        return this.forca;
    }

    public String getNome(){
        return this.nome;
    }


    public abstract int atacar(Personagem alvo);
}