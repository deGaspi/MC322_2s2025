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
        System.out.println(this.getClasse().name() + " recebeu " + x + " pontos de experiência");
        experiencia += x;
        if (experiencia >= 20) {
            nivel += experiencia / 20;
            experiencia %= 20;
            System.out.println(this.getClasse().name() + "subiu para nível " + nivel);
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + nivel);
        System.out.println("Nível: " + experiencia);
    }

    public int atacar(Personagem alvo) {
        float dano = this.getForca() * (random.nextInt(3) + (random.nextInt(101) / 100));
        int danoAplicado = alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
        }
        return danoAplicado;
    }

    public abstract int usarHabilidadeEspecial(Personagem alvo);

}
