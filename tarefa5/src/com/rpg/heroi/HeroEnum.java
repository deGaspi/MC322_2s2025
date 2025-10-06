package com.rpg.heroi;

// Enum para facilitar implementação futura de novos heróis. Basta alterar aqui, e não vários arquivos.
public enum HeroEnum {
    PASSISTA("Passista",
            "Após requebrar muito, o passita manda seu passinho mais brasileiro, mortal contra Falsos Patriotas e causa dano direto sem estar sujeito à chances nos demais inimigos."),
    PUXADOR("Puxador de Samba",
            "O puxador de samba reconquista a esperança da nação, curando-se exponencialmente em relação ao seus pontos acumulados.");

    private final String TypeName;
    private final String habilityInfo;

    private HeroEnum(String typeName, String habilityInfo) {
        this.TypeName = typeName;
        this.habilityInfo = habilityInfo;
    }

    public String getTypeName() {
        return TypeName;
    }

    public String getHabilityInfo() {
        return habilityInfo;
    }

    public Heroi getDefaultInstance() {
        switch (this) {
            case PASSISTA:
                return new Passista("Valéria Valenssa", 45, 12, 0, 0);
            case PUXADOR:
                return new Puxador("Cartola", 45, 12, 0, 0);
            default:
                throw new AssertionError("Enum inesperado: " + this);
        }
    }
}
