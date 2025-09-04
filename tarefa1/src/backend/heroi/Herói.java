package backend.heroi;

import java.util.List;
import java.util.Random;

import backend.Batalha;
import backend.Personagem;
import backend.monstro.Monstro;
import frontend.Utils.enumDescription;

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
        statusList.add("Classe: " + this.getTipo().getDescription());
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

        public String getHabilityInfo() {
            return habilityInfo;
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
