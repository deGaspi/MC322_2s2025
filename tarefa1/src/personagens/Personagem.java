package personagens;

import java.util.ArrayList;
import java.util.List;

import io.Batalha;

public abstract class Personagem {
    private String nome;
    private int pontosDeVida;
    private int forca;

    public Personagem(String name, int LP, int strength) {
        this.nome = name;
        this.pontosDeVida = LP;
        this.forca = strength;
    }

    public abstract boolean atacar(Personagem alvo); // retorna true se o ataque faz o turno acabar.

    public int receberDano(int dano) {
        dano = Math.min(dano, this.pontosDeVida);
        this.pontosDeVida -= dano;
        Batalha.addPostRoundMessage(this.nome + " recebeu " + dano + " pontos de dano, ficando com " + this.pontosDeVida + " pontos de vida.");
        return dano;
    }

    public void receberCura(int cura) {
        this.pontosDeVida += cura;
        Batalha.addPostRoundMessage(this.nome + " recebeu " + cura + " pontos de vida.");
    }

    public void receberForca(int forca) {
        this.forca += forca;
        Batalha.addPostRoundMessage(this.nome + " recebeu " + forca + " pontos de força.");
    }

    public int getPontosDeVida() {
        return this.pontosDeVida;
    }

    public int getForca() {
        return this.forca;
    }

    public String getNome(){
        return this.nome;
    }

    // Usado no lugar de exibirStatus().
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
}