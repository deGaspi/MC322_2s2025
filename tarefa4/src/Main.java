import classes.InputManager;
import classes.heroi.HeroEnum;
import classes.monstro.MonstroEnum;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        final String menu = """
                TERRAS SOMBRIAS - RPG
                ==================================================
                [1] Iniciar Novo Jogo
                [2] Ver Informações das Classes de Heróis
                [3] Ver Informações das Classes de Monstros
                [4] Sair do Jogo
                ==================================================
                Digite sua opção >
                """;
        switch (InputManager.lerInteiro(menu, 1, 4)) {
            case 1:
                Jogo.main();
                break;
            case 2:
                heroInfo();
                break;
            case 3:
                monsterInfo();
                break;
            case 4:
                System.out.println("Tchau");
                break;
            default:
                break;
        };
    }

    private static void heroInfo() {
        for (var hero: HeroEnum.values()) {
            System.out.println(hero.getTypeName()+":");
            hero.printHabilityInfo();
            System.out.println("\n");
        }
    }

    private static void monsterInfo() {
        for (var monster: MonstroEnum.values()) {
            System.out.println(monster.getTypeName()+":");
            System.out.println("\n");
        }
    }
}
