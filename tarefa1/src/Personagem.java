public abstract class Personagem {
    private String nome;
    public int pontosDeVida;
    public int forca;

    public Personagem(String name, int LP, int strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
    }

    public int receberDano(int dano) {
        if(this.pontosDeVida - dano < 0){
            int temp = this.pontosDeVida;
            this.pontosDeVida = 0;
            return temp;
        }else{
            this.pontosDeVida -= dano;
            return dano;
        }
        
    }

    public void exibirStatus() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
    }

    public abstract String getClassName();
    public abstract int atacar(Personagem alvo);

    //Toda função de ação retorna 0 ou 1 para contabilizar a passagem, ou não, do turno
}