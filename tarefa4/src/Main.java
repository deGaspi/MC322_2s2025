
import java.util.Random;
import java.util.ArrayList;

import classes.cenarios.ConstrutorDeCenárioFixo;
import classes.cenarios.Dificuldade;

import classes.InputManager;

import classes.heroi.HeroEnum;
import classes.monstro.MonstroEnum;

public class Main {
    public static void main(String[] args) {
        final int N_DE_FASES = 4;
        final ConstrutorDeCenárioFixo construtor = new ConstrutorDeCenárioFixo();
        final ArrayList<Fase> fases = construtor.gerar(N_DE_FASES, Dificuldade.FACIL); // TODO: dar prompt na dificuldade

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
