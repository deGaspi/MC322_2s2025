package com.rpglab.personagens.heroi;

// Enum para facilitar implementação futura de novos heróis. Basta alterar aqui, e não vários arquivos.
public enum HeroEnum {
    PASSISTA("Passista",
            "Após requebrar muito, o passita manda seu passinho mais brasileiro, efetivo contra imperialistas e \nmortal para falsos patriotas."),
    PUXADOR("Puxador de Samba",
            "O puxador de samba reconquista a esperança da nação, convertendo seu swing para curar-se.");

    private final String TypeName;
    private final String habilityInfo;

    private HeroEnum(String typeName, String habilityInfo) {
        this.TypeName = typeName;
        this.habilityInfo = habilityInfo;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void printHabilityInfo() {
        System.out.println(habilityInfo);
    }

    public Herói getDefaultInstance() {
        switch (this) {
            case PASSISTA:
                return new Passista("Valéria Valenssa", 23, 6, 0, 0);
            case PUXADOR:
                return new Puxador("Cartola", 23, 6, 0, 0);
            default:
                throw new RuntimeException();
        }
    }
}
