package rpg.game;
import java.util.Random;

import rpg.cenarios.ConstrutorDeCenárioFixo;
import rpg.cenarios.Dificuldade;
import rpg.cenarios.FaseDeCombate;
import rpg.heroi.HeroEnum;
import rpg.util.paradaJogador;
import rpg.util.InputManager;
import rpg.util.Util;
import rpg.cenarios.GerenciadorDePersistencia;
import rpg.heroi.Heroi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Jogo em si, escolhe-se a dificuldade, o herói é escolhido
 * aliatoriamente e depois se chama o início de cada fase
 * dentro de um try catch para capturar a exceção de desistência
 * ou salvamento
 */
public class Jogo {
    private final static int N_DE_FASES = 4;

    public static void novoJogo() {
        final String saveName = escolherSaveName();
        final Dificuldade dif = escolherDificuldade();
        
        final ConstrutorDeCenárioFixo construtor = new ConstrutorDeCenárioFixo(dif);
        final ArrayList<FaseDeCombate> fases = construtor.gerar(N_DE_FASES, dif);

        var random = new Random();
        var heroValues = HeroEnum.values();
        var heroiEscolhido = heroValues[random.nextInt(heroValues.length)];
        var heroi = heroiEscolhido.getDefaultInstance();

        // Formatação e prints de informações para o jogador.
        Util.printBoxed("Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo. Só resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o carnaval.");
        InputManager.esperarEnter();
        Util.printBoxed("Informações do herói:\n\n" + heroiEscolhido.getHabilityInfo());
        InputManager.esperarEnter();
        Util.printBoxed("Você encontra a caverna do acúmulo, onde o terrível imperialista reside, você hesita, mas a alegria de seu povo depende de você, derrote os lacaios pra alcançar o imperialista e por um fim à sua ganância.");
        InputManager.esperarEnter();

        jogoCarregado(saveName, fases, 0, N_DE_FASES, heroi, dif);
    }

    public static void jogoCarregado(String saveName, List<FaseDeCombate> fases, int faseAtual, int nDeFases, Heroi heroi, Dificuldade dif){
        for (int i = faseAtual; i < nDeFases; i++) {
            FaseDeCombate fase = fases.get(i);

            // Formatação e print da apresentação da fase.
            Util.printBoxed("Fase " + (i+1) + "/" + nDeFases+ "\n \n", fase.getTipoCenario().getDescription());
            InputManager.esperarEnter();

            // Execução da fase e salvamento.
            boolean resultado;
            try {
                resultado = fase.iniciar(heroi);
            } catch (paradaJogador e) {
                System.out.println(e.getMessage());
                if(e.toSave()){
                    GerenciadorDePersistencia persistir = new GerenciadorDePersistencia();
                    persistir.salvarJogo(N_DE_FASES, saveName, i, dif, heroi, fase.getState());  
                }
                return;
            }

            //Resultado
            if (!resultado) {
                Util.printBoxedCentered("O imperialismo conseguiu privatizar o carnaval.\n \nO   S A M B A   M O R R E U   ! ! !");
                return;
            }
        }
        Util.printBoxedCentered("Você eternizou o samba nos corações dos brasileiros.\n \nO SAMBA VENCEU !!!");
    }

    private static Dificuldade escolherDificuldade(){
        Util.printBoxed("""
            Escolha a dificuldade:

            [1] Fácil
            [2] Médio
            [3] Difícil
            """);
        switch (InputManager.lerInteiro(1, 3)) {
            case 1:
                return Dificuldade.FACIL;
            case 2:
                return Dificuldade.MEDIO;
            case 3:
                return Dificuldade.DIFICIL;
            default:
                throw new AssertionError("Input inesperado");
        }
    }

    private static String escolherSaveName() {
        while (true) {
            var saveName = InputManager.lerString("Escolha um nome para salvar o jogo > ");
            var file = new File("savedGames/"+saveName+".xml");
            if (!file.exists()) {
                return saveName;
            }
            System.out.println("Erro: Já existe um jogo salvo com esse nome.");
        }
    }
}
