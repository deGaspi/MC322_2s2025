package personagens;

import java.util.ArrayList;
import java.util.List;

import io.IO;

public abstract class Personagem {
    private String nome;
    private int pontosDeVida;
    private int forca;

    public Personagem(String name, int LP, int strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
    }

    public int receberDano(int dano) {
        dano = Math.min(dano, this.pontosDeVida);
        this.pontosDeVida -= dano;
        IO.dmgCallback(dano, this);
        return dano;
    }

    public void receberCura(int cura) {
        this.pontosDeVida += cura;
        IO.healCallback(cura, this);
    }

    public void receberForca(int forca) {
        this.forca += forca;
    }

    // Usado no lugar de exibirStatus(). Como não printa direto, pode ser usado pela classe IO.
    public List<String> getStatusList() { 
        List<String> statusList = new ArrayList<>();
        statusList.add("Nome: " + this.nome);
        statusList.add("Vida: " + this.pontosDeVida);
        statusList.add("Força: " + this.forca);
        return statusList;
    }

    // Está aqui para satisfazer as exigências da atividade. Não é utilizado.
    public void exibirStatus() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Vida: " + this.pontosDeVida);
        System.out.println("Força: " + this.forca);
    }

    public int getPontosDeVida() {
        return this.pontosDeVida;
    }

    public int getForca() {
        return this.forca;
    }
    
    public String getName() {
        return this.nome;
    }
    
    public abstract boolean atacar(Personagem alvo); // true quando for para passar o round.
}