package com.rpg.game;
//Refatorar em pacotes
//Balancear

import com.rpg.util.InputManager;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        final String menu = """
                NÃO DEIXE O SAMBA MORRER - RPG
                ==================================================
                [1] Iniciar Novo Jogo
                [2] Ver Informações das Classes de Heróis
                [3] Ver Informações das Classes de Monstros
                [4] Sair do Jogo
                ==================================================
                Digite sua opção >
                """;

        //Loop principal
        boolean loop = true;
        while(loop){
            switch (InputManager.lerInteiro(menu, 1, 4)) {
                case 1:
                    Jogo.main();                 //Inicia o jogo e fecha o Scanner
                    InputManager.fecharScanner();
                    loop = false;
                    break;
                case 2:
                    heroInfo();
                    break;
                case 3:
                    monsterInfo();
                    break;
                case 4:
                    System.out.println("Tchau");
                    loop = false;
                    break;
                default:
                    break;
            };
        }
        
    }

    private static void heroInfo() {
        System.out.println("\nPassista:");
        System.out.println("Sua habilidade especial é mortal contra Falsos Patriotas e causa dano direto sem estar sujeito à chances nos demais inimigos\n");
        System.out.println("Puxador:");
        System.out.println("Sua habilidade especial o cura exponencialmente em relação ao seus pontos\n");
    }

    private static void monsterInfo() {
        System.out.println("\nFalso Patriota");
        System.out.println("Monstro mais abundande e fraco do jogo\n");
        System.out.println("Entreguista");
        System.out.println("Rouba a vida do herói ou de falsos patriotas e a entrega para o Imperialista, foge quando perde proteção\n");
        System.out.println("Imperialista");
        System.out.println("Chefe final do jogo\n");
    }
}
