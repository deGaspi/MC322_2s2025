package classes.monstro;

// Enum para facilitar implementação futura de novos monstros.
// Por exemplo, pode-se usar MonstroEnum.values() para ter todos os monstros implementados.
// Outras classes usam esse enum, assim basta alterar esse enum e não várias classes.
public enum MonstroEnum {
    ENTREGUISTA("Entreguista"),
    IMPERIALISTA("Imperialista"),
    FALSO_PATRIOTA("Falso Patriota");

    private final String typeName;

    private MonstroEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
