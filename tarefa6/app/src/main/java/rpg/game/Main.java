package rpg.game;
//Refatorar em pacotes
//Balancear

import rpg.heroi.HeroEnum;
import rpg.monstro.MonstroEnum;
import rpg.util.InputManager;
import rpg.cenarios.GerenciadorDePersistencia;

import java.io.File;


/**
 * Implementa o menu principal e chama as próximas classes de acordo
 * com a escolha.
 * [1] Iniciar Novo Jogo
 * [2] Carregar Jogo
 * [3] Ver Informações das Classes de Heróis
 * [4] Ver Informações das Classes de Monstros
 * [5] Sair do Jogo
 */
public class Main {
    public static void main(String[] args) {
        System.out.println();
        final String menu = """
                NÃO DEIXE O SAMBA MORRER - RPG
                ==================================================
                [1] Iniciar Novo Jogo
                [2] Carregar Jogo
                [3] Ver Informações das Classes de Heróis
                [4] Ver Informações das Classes de Monstros
                [5] Sair do Jogo
                ==================================================
                Digite sua opção >
                """;

        //Loop principal
        boolean loop = true;
        while(loop){
            switch (InputManager.lerInteiro(menu, 1, 5)) {
                case 1:
                    Jogo.novoJogo();                
                    loop = false;
                    break;
                case 2:
                    try {
                        loop = loadSaves();
                    } catch (Exception e) {
                        System.out.println("Erro ao carregar save: " + e);
                    }
                    break;
                case 3:
                    heroInfo();
                    break;
                case 4:
                    monsterInfo();
                    break;
                case 5:
                    System.out.println("Tchau");
                    loop = false;
                    break;
                default:
                    break;
            };
        }
        InputManager.fecharScanner();
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

    public static boolean loadSaves() throws Exception {
        File directory = new File("savedGames");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File[] files = directory.listFiles();
        if (files.length <1) {
            System.out.println("Não há saves.");
            return true;
        }

        String menu = "Escolha um save:\n";
        for (int i = 1; i <= files.length; i++) {
            String filename = files[i-1].getName();
            menu += "["+i+"] " + filename.substring(0, filename.length()-4) + "\n";
        }
        menu += "Digite sua opção > ";

        int i = InputManager.lerInteiro(menu.toString(), 1, files.length);
        String filename = files[i-1].getName();
        filename = filename.substring(0, filename.length()-4);

        GerenciadorDePersistencia persistir = new GerenciadorDePersistencia();
        persistir.carregarJogo(files[i-1]);
        Jogo.jogoCarregado(filename, persistir.getListaDeFases(), persistir.getFaseInicial(), persistir.getNDeFases(), persistir.getHeroi(), persistir.getDificuldade());
        return false;
    }
}
