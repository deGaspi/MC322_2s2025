package com.rpglab.game;

import java.util.Random;

import com.rpglab.cenario.ConstrutorDeCenárioFixo;
import com.rpglab.cenario.Fase;
import com.rpglab.personagens.heroi.HeroEnum;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int N_DE_FASES = 4;
        final ConstrutorDeCenárioFixo construtor = new ConstrutorDeCenárioFixo();
        final ArrayList<Fase> fases = construtor.gerar(N_DE_FASES); // 4 fases

        // Historia inicial.
        System.out.println(
                "Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo. \nSó resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o \ncarnaval.\n");

        // Escolha do heroi.
        var random = new Random();
        var heroValues = HeroEnum.values();
        var heroiEscolhido = heroValues[random.nextInt(heroValues.length)];
        var heroi = heroiEscolhido.getDefaultInstance();

        // Explicar habilidade do heroi escolhido.
        System.out.println("Informações do herói: ");
        heroiEscolhido.printHabilityInfo();
        System.out.println();

        // Introdução do objetivo do jogo.
        System.out.println(
                "Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria \nde seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua \nganância.\n");

        // Inicialização dos inimigos
        for (int i = 0; i<N_DE_FASES; i++) {
            var fase = fases.removeFirst();
            System.out.println("\n############################# Fase " + (i+1) + "/" + N_DE_FASES + " #############################\n");
            System.out.println(fase.getTipoCenario().getDescription());
            boolean resultado = fase.iniciar(heroi);


                System.out.println("-------------------------------------------------\n");
                if (!resultado) {
                    System.out.println("O imperialismo conseguiu privatizar o carnaval.");
                    System.out.println("O   S A M B A   M O R R E U");
                    return;
                }
            }
        System.out.println("\nVocê eternizou o samba nos corações dos brasileiros.\n O SAMBA VENCEU!!!!");
    }
}
