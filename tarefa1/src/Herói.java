public abstract class Herói extends Personagem{
    private int nivel;
    private int experiencia;
    
    public Herói (String name, int LP, int strength, int level, int xp, Classe classe){
        super(name, LP, strength, classe);
        this.nivel = level;
        this.experiencia = xp;
    }

    public void ganharExperiencia(int x){
        this.experiencia += x;
        while(this.experiencia >=20){
            this.nivel += 1;
            this.experiencia -= 20;
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + nivel);
        System.out.println("Nível: " + experiencia);
    }

    public abstract int usarHabilidadeEspecial(Personagem alvo);

}
