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

    public static enum monstroEnum {
        // Enum para facilitar implementação futura de novos monstros. Basta alterar
        // aqui, e não vários arquivos.
        ENTREGUISTA,
        IMPERIALISTA,
        FALSO_PATRIOTA;
    }

    public abstract monstroEnum getTipo(); // Para que lembrem de alterar o enum quando adicionarem outro heroi.

    @Override
    public List<String> getStatusList() {
        var statusList = super.getStatusList();
        statusList.add("XP: " + xpConcedido);
        return statusList;
    }
}
