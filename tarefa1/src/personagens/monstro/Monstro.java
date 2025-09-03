package personagens.monstro;

import java.util.List;

import personagens.Personagem;

public abstract class Monstro extends Personagem {
    private int xpConcedido;

    public Monstro(String name, int LP, int strength, int xp) {
        super(name, LP, strength);
        this.xpConcedido = xp;
    }

    public int getXpConcedido() {
        return this.xpConcedido;
    }

    @Override
    public List<String> getStatusList() {
         // Usado no lugar de exibirStatus(). Como não printa direto, pode ser usado pela classe IO.
        var statusList = super.getStatusList();
        statusList.add("Xp: " + xpConcedido);
        return statusList;
    }
   


    @Override
    public void exibirStatus() {
        // Está aqui para satisfazer as exigências da atividade. Não é utilizado.
        super.exibirStatus();
        System.out.println("Xp: " + xpConcedido);
    }

    public abstract MonsterTypes getMonsterType();

    // Enum com todos os monstros.
    public static enum MonsterTypes {
        IMPERIALISTA,
        FALSO_PATRIOTA,
        ENTREGUISTA;

        public Monstro getDefaultInstance() {
            switch (this) {
                case IMPERIALISTA:
                    return new Imperialista("Imperialista", 20, 5, 0);
                case FALSO_PATRIOTA:
                    return new FalsoPatriota("Falso Patriota", 20, 5, 10);
                case ENTREGUISTA:
                    return new Entreguista("Entreguista", 20, 5, 10, null);
                default:
                    throw new RuntimeException();
            }
        }
    }

}
