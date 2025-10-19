package rpg.game;
//Refatorar em pacotes
//Balancear

import rpg.heroi.HeroEnum;
import rpg.monstro.MonstroEnum;
import rpg.util.BoxedString;
import rpg.util.InputManager;
import rpg.util.Util;
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
        // Criação da pasta de saves.
        File directory = new File("savedGames");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Formatação e print do menu.
        var menu = new BoxedString()
            .appendCentered("NÃO DEIXE O SAMBA MORRER - RPG")
            .append("""
                [1] Iniciar Novo Jogo
                [2] Carregar Jogo
                [3] Ver Informações das Classes de Heróis
                [4] Ver Informações das Classes de Monstros
                [5] Sair do Jogo
                """)
            .addPadding(1)
            .addBox();

        boolean loop = true;
        while(loop){
            menu.print();
            switch (InputManager.lerInteiro(1, 5)) {
                case 1:
                    Jogo.novoJogo();                
                    loop = false;
                    break;
                case 2:
                    try {
                        loadSaves();
                    } catch (NenhumSaveException e) {
                        Util.printBoxed("Não existem jogos salvos.");
                        InputManager.esperarEnter();
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
        var menu = new BoxedString()
            .appendCentered("Informações dos Heróis");

        for (var hero : HeroEnum.values()) {
            menu.append("\n" + hero.getTypeName()+":");
            menu.append(hero.getHabilityInfo() );
        }
        menu.addPadding(1);
        menu.addBox();
        menu.print();
        InputManager.esperarEnter();
    }

    private static void monsterInfo() {
        var menu = new BoxedString()
            .appendCentered("Informações dos Monstros");

        for (var monster : MonstroEnum.values()) {
            menu.append("\n" + monster.getTypeName()+":");
            menu.append(monster.getDescripton());
        }
        menu.addPadding(1);
        menu.addBox();
        menu.print();
        InputManager.esperarEnter();
    }

    public static void loadSaves() throws NenhumSaveException, Exception {
        File directory = new File("savedGames");

        File[] files = directory.listFiles();
        if (files.length <1)
            throw new NenhumSaveException();

        String menu = "Escolha um save:\n\n";
        for (int i = 1; i <= files.length; i++) {
            String filename = files[i-1].getName();
            menu += "["+i+"] " + filename.substring(0, filename.length()-4) + "\n";
        }
        Util.printBoxed(menu);

        int i = InputManager.lerInteiro(1, files.length);
        String filename = files[i-1].getName();
        filename = filename.substring(0, filename.length()-4);

        GerenciadorDePersistencia persistir = new GerenciadorDePersistencia();
        persistir.carregarJogo(files[i-1]);
        Jogo.jogoCarregado(filename, persistir.getListaDeFases(), persistir.getFaseInicial(), persistir.getNDeFases(), persistir.getHeroi(), persistir.getDificuldade());
    }
}
