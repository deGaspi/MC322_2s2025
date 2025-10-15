package rpg.game;
import java.util.Random;
import java.util.Scanner;

import rpg.cenarios.ConstrutorDeCenárioFixo;
import rpg.cenarios.Dificuldade;
import rpg.heroi.HeroEnum;
import rpg.interfaces.Fase;
import rpg.util.Desistencia;
import rpg.util.InputManager;

import java.util.ArrayList;

/**
 * Jogo em si, escolhe-se a dificuldade, o herói é escolhido
 * aliatoriamente e depois se chama o início de cada fase
 * dentro de um try catch para capturar a exceção de desistência
 */
public class Jogo {
    public static void main() {
        //Escolha de Dificuldade
        final Dificuldade dificuldade;
        System.out.println();
        final String escDificuldade = """
                Escolha a dificuldade
                ==================================================
                [1] Fácil
                [2] Médio
                [3] Difícil
                ==================================================
                Digite sua opção >
                """;
        var input = new InputManager(new Scanner(System.in));
        switch (input.lerInteiro(escDificuldade, 1, 3)) {
                case 1:
                    dificuldade = Dificuldade.FACIL;
                    break;
                case 2:
                    dificuldade = Dificuldade.MEDIO;
                    break;
                case 3:
                    dificuldade = Dificuldade.DIFICIL;
                    break;
                default:
                    throw new AssertionError("Input inesperado.");
            };



        final int N_DE_FASES = 4;
        
        final ConstrutorDeCenárioFixo construtor = new ConstrutorDeCenárioFixo();
        final ArrayList<Fase> fases = construtor.gerar(N_DE_FASES, dificuldade); // 4 fases

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
        heroiEscolhido.getHabilityInfo();
        System.out.println();

        // Introdução do objetivo do jogo.
        System.out.println(
                "Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria \nde seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua \nganância.\n");

        // Loop de fases
        for (int i = 0; i < N_DE_FASES; i++) {
            var fase = fases.removeFirst();
            System.out.println("\n############################# Fase " + (i + 1) + "/" + N_DE_FASES // Divisor de fases
                    + " #############################\n");
            System.out.println(fase.getTipoCenario().getDescription());
            boolean resultado;
            try {
                resultado = fase.iniciar(heroi); // Aqui acontece a chamada da fase criada
            } catch (Desistencia e) {
                System.out.println(e.getMessage());
                return;
            }

            //Resultado
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
