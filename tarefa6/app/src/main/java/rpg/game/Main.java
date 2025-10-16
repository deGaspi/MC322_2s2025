package rpg.game;
//Refatorar em pacotes
//Balancear

import rpg.heroi.HeroEnum;
import rpg.monstro.MonstroEnum;
import rpg.util.InputManager;
import rpg.cenarios.GerenciadorDePersistencia;

import java.util.Scanner;


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
        var input = new InputManager(new Scanner(System.in));
        while(loop){
            switch (input.lerInteiro(menu, 1, 5)) {
                case 1:
                    Jogo.novoJogo();                
                    loop = false;
                    break;
                case 2:
                    GerenciadorDePersistencia persistir = new GerenciadorDePersistencia();
                    try{
                        persistir.carregarJogo(input.lerString("Qual jogo deve ser carregado?"));
                        Jogo.jogoCarregado(persistir.getListaDeFases(), persistir.getFaseInicial(), persistir.getHeroi(), persistir.getDificuldade());
                        loop = false;
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
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
