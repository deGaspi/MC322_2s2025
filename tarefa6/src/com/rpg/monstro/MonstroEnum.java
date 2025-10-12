package com.rpg.monstro;

// Enum para facilitar implementação futura de novos monstros.
// Por exemplo, pode-se usar MonstroEnum.values() para ter todos os monstros implementados.
// Outras classes usam esse enum, assim basta alterar esse enum e não várias classes.
public enum MonstroEnum {
    IMPERIALISTA(
            "Imperialista",
            "Monstro mais abundande e fraco do jogo."),
    FALSO_PATRIOTA(
            "Falso Patriota",
            "Chefe final do jogo.");

    private final String typeName;
    private final String description;

    private MonstroEnum(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getDescripton() {
        return description;
    }
}
