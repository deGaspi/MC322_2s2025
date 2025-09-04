package personagens.heroi;

import java.util.List;
import java.util.Random;

import io.Batalha;
import io.Utils.enumDescription;
import personagens.Personagem;
import personagens.monstro.Monstro;

public abstract class Herói extends Personagem {
    private int nivel;
    private int experiencia;
    private Random random = new Random();

    public Herói(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength);
        this.nivel = level;
        this.experiencia = xp;
    }

    public abstract boolean usarHabilidadeEspecial(Personagem alvo); // retorna true se ataque faz turno acabar.

    public void ganharExperiencia(int x) {
        float y = x * random.nextFloat() * 2;
        int z = Math.round(y);
        Batalha.addPostRoundMessage(this.getNome() + " recebeu " + z + " pontos de experiência.");
        this.experiencia += z;
        if (this.experiencia >= 20) {
            this.nivel += this.experiencia / 20;
            this.experiencia %= 20;
            Batalha.addPostRoundMessage(this.getNome() + " subiu para nível " + this.nivel + ".");
        }
    }

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("Nível: " + nivel);
        statusList.add("XP: " + experiencia);
        return statusList;
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.println("Nível: " + nivel);
        System.out.println("Experiência: " + experiencia);
    }

    @Override
    public boolean atacar(Personagem alvo) {
        float dano = (this.getForca() * random.nextFloat() * 2) + (this.nivel * 0.5f);
        alvo.receberDano(Math.round(dano));
        if (alvo instanceof Monstro monstro && monstro.getPontosDeVida() == 0) {
            this.ganharExperiencia(monstro.getXpConcedido());
        }
        return true;
    }

    public static enum heroEnum implements enumDescription {
        // Enum para facilitar implementação futura de novos heróis. Basta alterar
        // aqui, e não vários arquivos.
        PASSISTA("Passista"),
        PUXADOR("Puxador");

        private final String description;

        heroEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
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

    public abstract heroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.


}
