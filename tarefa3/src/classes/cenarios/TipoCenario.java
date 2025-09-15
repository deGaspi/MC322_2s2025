package classes.cenarios;

public enum TipoCenario {
    ENTRADA("Você se aproxima da entrada da caverna, porém dois falsos patriotas te impedem. Prepare-se para a batalha!!"),
    CAVERNA("Você desce mais um andar da caverna"),
    CHEFE("Você desce até o fim da caverna. Agora é a sua chance de acabar com o imperialista!!");

    private String description;

    private TipoCenario(String d){
        this.description = d;
    }

    public String getDescription(){
        return description;
    }
}
