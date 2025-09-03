import java.util.Random;

public abstract class Herói extends Personagem{
    private int nivel;
    private int experiencia;
    Random random = new Random();
    
    public Herói (String name, int LP, int strength, int level, int xp, Classe classe){
        super(name, LP, strength, classe);
        this.nivel = level;
        this.experiencia = xp;
    }

    public void ganharExperiencia(int x){
        float y = x * random.nextFloat() * 2;
        int z = Math.round(y);
        System.out.println(this.getClasse().name() + " recebeu " + z + " pontos de experiência");
        this.experiencia += z;
        if (this.experiencia >= 20) {
            this.nivel += this.experiencia / 20;
            this.experiencia %= 20;
            System.out.println(this.getClasse().name() + "subiu para nível " + this.nivel);
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + nivel);
        System.out.println("Nível: " + experiencia);
    }

    public int atacar(Personagem alvo) {
        float dano = (this.getForca() * random.nextFloat() * 2) + (this.nivel * 10);
        int danoAplicado = alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
        }
        return danoAplicado;
    }

    public abstract boolean usarHabilidadeEspecial(Personagem alvo);

}
