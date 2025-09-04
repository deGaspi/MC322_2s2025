import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static Herói heróiEscolhido = null;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int nLacaios = 6;

    public static void main(String[] args) {

        System.out.println("Você nasceu e cresceu no samba, mas agora estão querendo acabar com a cultura do seu povo,\nsó resta uma coisa a fazer: resistir e derrotar a força imperialista que quer privatizar o carnaval.");
        System.out.println();
        
        heróiEscolhido = escolherPersonagem();
        Monstro lacaio;
        Imperialista boss = new Imperialista("Imperialista", 1, 3, 0);
        Entreguista covarde = new Entreguista("b", 1, 3, 0, boss);

        int falsoPatriotaCounter = 0;                                                              //Quando chegar em nLacaios, invoca o imperialista e o entreguista foge para os EUA
        int turno = 0;


        while(heróiEscolhido.getPontosDeVida() != 0 && boss.getPontosDeVida() != 0) {              //Loop do jogo inteiro
            System.out.println("Você encontra a caverna do acúmulo, onde o terrível imperialista reside,\nvocê hesita, mas a alegria de seu povo depende de você, derrote os lacaios\npara alcançar o imperialista e por um fim à sua ganância");
            System.out.println();

            while(falsoPatriotaCounter <= nLacaios){                                               //Loop até chegar no boss final
                System.out.println("Um Falso Patriota se aproxima para defender os interesses extrangeiros");
                System.out.println();                                              
                falsoPatriotaCounter++;
                lacaio = new FalsoPatriota("Lacaio " + falsoPatriotaCounter + "/" + nLacaios, 10, 1, 50);

                while(lacaio.getPontosDeVida() != 0){                                              //Loop até derrotar o lacaio atual
                    turno++;
                    Entregar(covarde, lacaio, heróiEscolhido, turno);

                    lacaio.atacar(heróiEscolhido);

                    Escolha escolha = Escolha.statusHeroi;
                    while(escolha != Escolha.ataqueSimples && escolha != Escolha.ataqueEspecial){ //Loop porque escolher mostrar o status não conta como turno
                        escolha = escolha();
                        if(escolha == Escolha.statusHeroi){
                            heróiEscolhido.exibirStatus();
                        }else if(escolha == Escolha.statusMonstro){
                            lacaio.exibirStatus();
                        }
                    }
                    if(escolha == Escolha.ataqueSimples){
                        heróiEscolhido.atacar(lacaio);
                    }else{
                        heróiEscolhido.usarHabilidadeEspecial(lacaio);
                    }
                    System.out.println();
                }
                if((nLacaios - falsoPatriotaCounter) >= 0){
                    System.out.println("Você derrotou um lacaio do imperialismo, pelos seus sentidos aguçados, você sente que há mais " + (nLacaios - falsoPatriotaCounter));
                }
            }
            System.out.println("O entreguista fugiu para os Estados Unidos, deixando o imperialista vulnerável");
            System.out.println("Agora é a sua chance, você: " + heróiEscolhido.getNome() + " enfrentará o imperialista");
            System.out.println();
                
            while(true){
                Escolha escolha = Escolha.statusHeroi;
                while(escolha != Escolha.ataqueSimples && escolha != Escolha.ataqueEspecial){ //Loop porque escolher mostrar o status não conta como turno
                    escolha = escolha();
                    if(escolha == Escolha.statusHeroi){
                        heróiEscolhido.exibirStatus();
                    }else if(escolha == Escolha.statusMonstro){
                        boss.exibirStatus();
                    }
                }
                if(escolha == Escolha.ataqueSimples){
                    heróiEscolhido.atacar(boss);
                }else{
                    heróiEscolhido.usarHabilidadeEspecial(boss);
                }
                turno++;

                boss.atacar(heróiEscolhido);
        }
    }


        if(boss.getPontosDeVida() == 0){
            System.out.println("Você eternizou o samba nos corações dos brasileiros");
        }else{
            System.out.println("O imperialismo conseguiu privatizar o carnaval");
        }
    }

    private enum Escolha {
        ataqueSimples,
        ataqueEspecial,
        statusHeroi,
        statusMonstro,
    }

    private static Escolha escolha() {
        int input = 0;
        System.out.println("Escolha uma ação:");
        System.out.println("1-) Ataque Simples");
        System.out.println("2-) Ataque Especial");
        System.out.println("3-) Mostrar status do Herói");
        System.out.println("4-) Mostrar status do Monstro");
        while (true) {
            try {
                input = scanner.nextInt();
                System.out.println();
                switch (input) {
                    case 1:
                        return Escolha.ataqueSimples;
                    case 2:
                        return Escolha.ataqueEspecial;
                    case 3:
                        return Escolha.statusHeroi;
                    case 4:;
                        return Escolha.statusMonstro;
                    default:
                        System.out.println("Digite o número de 1 a 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite o número de 1 a 4");
            }
        }
        
    }

    private static Herói escolherPersonagem() {
        int input = 0;
        System.out.println("Escolha um herói: (digite o número)");
        System.out.println("1-) Passista");
        System.out.println("2-) Puxador");
        while (true) {
            try {
                input = scanner.nextInt();
                System.out.println();
                switch (input) {
                    case 1:
                        return new Passista("Valéria Valenssa", 25, 4, 0, 0, 0);
                    case 2:
                        return new Puxador("Jamelão", 20, 4, 0, 0, 0);
                    default:
                        System.out.println("Digite o número 1 ou 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite o número 1 ou 2");
                scanner.next();
            }
        }
    }

    private static void Entregar(Entreguista e, Monstro lacaio, Herói h, int turno){
        if(turno % 4 == 0){
            System.out.println("Entreguista vai privatizar a vida do...");
            boolean chance = random.nextBoolean();

            if(chance){
                System.out.println("Herói");
                e.atacar(h);
            }else{
                System.out.println("Falso Patriota");
                e.atacar(lacaio);
            }
            System.out.println();
        }
    }
}
