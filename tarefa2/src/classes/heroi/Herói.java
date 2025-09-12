package classes.heroi;

import java.util.Random;

import classes.Personagem;
import classes.armas.Arma;
import classes.armas.SemArma;
import classes.monstro.Monstro;

public abstract class Herói extends Personagem {
    private int nivel;
    private int experiencia;
    private int expProxNivel = 20;
    private float sorte;
    
    private Random random = new Random();

    public Herói(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength);
        this.nivel = level;
        this.experiencia = xp;
        this.sorte = random.nextFloat();
    }

    public abstract boolean usarHabilidadeEspecial(Personagem alvo);

    private void subirDeNivel() {
        if (this.experiencia >= expProxNivel) {
            this.nivel += this.experiencia / expProxNivel;
            this.experiencia %= expProxNivel;
            this.expProxNivel += 2;
            System.out.println(this.getNome() + " subiu para nível " + this.nivel + ".");
        }
    }

    public void ganharExperiencia(int x) {
        float y = x * sorte * 2;
        int z = Math.round(y);
        System.out.println(this.getNome() + " recebeu " + z + " pontos de experiência.");
        this.experiencia += z;
        subirDeNivel();
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("    Nível: " + nivel);
        System.out.println("    Experiência: " + experiencia);
        System.out.println("    Tipo: " + getTipo().getDescription());
    }

    @Override
    public boolean atacar(Personagem alvo) {
        var arma = getArma();
        float dano = (this.getForca() * sorte * 2) + (this.nivel * 0.5f) + (arma.getDano());
        if (arma instanceof SemArma)
            System.out.println(this.getNome() + " ataca desarmado.");
        else 
            System.out.println(this.getNome() + " ataca com " + arma.getNome() + ".");
        alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
            var armaMonstro = monstro.getArma();
            if (armaMonstro.getDano() > this.getArma().getDano()) {
                this.receberArma(armaMonstro);
            }
        }
        return true;
    }

    public static enum heroEnum {
        // Enum para facilitar implementação futura de novos heróis. Basta alterar
        // aqui, e não vários arquivos.
        PASSISTA(
                "Passista",
                "Após requebrar muito, o passita manda seu passinho mais brasileiro, efetivo contra imperialistas e \nmortal para falsos patriotas."),
        PUXADOR(
                "Puxador de Samba",
                "O puxador de samba reconquista a esperança da nação, convertendo seu swing para curar-se.");

        private final String description;
        private final String habilityInfo;

        heroEnum(String description, String habilityInfo) {
            this.description = description;
            this.habilityInfo = habilityInfo;
        }

        public String getDescription() {
            return description;
        }

        public void printHabilityInfo() {
            System.out.println(habilityInfo);
        }

        public Herói getDefaultInstance() {
            switch (this) {
                case PASSISTA:
                    return new Passista("Valéria Valenssa", 23, 4, 0, 0, 0);
                case PUXADOR:
                    return new Puxador("Jamelão", 23, 4, 0, 0, 0);
                default:
                    throw new RuntimeException();
            }
        }
    }

    @Override
    public void receberArma(Arma a) {
        if(a.getMinNivel() < this.nivel){
            System.out.println(this.getNome() + " não possui experiência suficiente para lidar com " + a.getNome() + ".");
        }else{
            super.receberArma(a);
        }
    }

    public abstract heroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.

}
