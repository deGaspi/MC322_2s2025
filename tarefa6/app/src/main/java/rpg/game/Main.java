package rpg.game;
//Refatorar em pacotes
//Balancear

import rpg.heroi.HeroEnum;
import rpg.monstro.MonstroEnum;
import rpg.util.InputManager;
import java.util.Scanner;


/**
 * Começo da execução, é reduzido a um laço com um switch para a escolha inicial
 * se o jogo é iniciado, chama a main de Jogo.java
 */
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
        var input = new InputManager(new Scanner(System.in));
        while(loop){
            switch (input.lerInteiro(menu, 1, 4)) {
                case 1:
                    Jogo.main();                
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
        input.fecharScanner();
        
    }

    private static void heroInfo() {
        for (var hero : HeroEnum.values()) {
            System.out.println(hero.getTypeName());
            System.out.println(hero.getHabilityInfo() + "\n");
        }
    }

    private static void monsterInfo() {
        for (var monster : MonstroEnum.values()) {
            System.out.println(monster.getTypeName());
            System.out.println(monster.getDescripton() + "\n");
        }
    }
}
