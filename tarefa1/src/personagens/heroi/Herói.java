package personagens.heroi;

import java.util.List;

import io.IO;
import io.Utils.enumDescription;
import personagens.Personagem;

public abstract class Herói extends Personagem {
    private int nivel;
    private int experiencia;

    public Herói(String name, int LP, int strength, int level, int xp) {
        super(name, LP, strength);
        this.nivel = level;
        this.experiencia = xp;

    }

    public void ganharExperiencia(int x) {
        IO.xpCallback(x, this);
        experiencia += x;
        if (experiencia >= 20) {
            nivel += experiencia / 20;
            experiencia %= 20;
        }
    }

    @Override
    public List<String> getStatusList() {
         // Usado no lugar de exibirStatus(). Como não printa direto, pode ser usado pela classe IO.
        var statusList = super.getStatusList();
        statusList.add("Nível: " + nivel);
        statusList.add("Nível: " + experiencia);
        return statusList;
    }
   


    @Override
    public void exibirStatus() {
        // Está aqui para satisfazer as exigências da atividade. Não é utilizado.
        super.exibirStatus();
        System.out.println("Nível: " + nivel);
        System.out.println("Nível: " + experiencia);
    }

    public abstract boolean usarHabilidadeEspecial(Personagem alvo); // true quando for para passar round.

    public abstract heroEnum getHeroType();

    // Enum com todos os heróis.
    public static enum heroEnum implements enumDescription {
        PASSISTA("Passista"),
        PUXADOR("Puxador");

        private final String description; // Usado na seleção de personagem.

        heroEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public Herói getDefaultInstance() { // Usado na seleção de Personagem.
            switch (this) {
                case PASSISTA:
                    return new Passista("Passista", 20, 5, 0, 0, 0);
                case PUXADOR:
                    return new Puxador("Puxador", 20, 5, 0, 0, 0);
                default:
                    throw new RuntimeException();
            }
        }
    }
}
