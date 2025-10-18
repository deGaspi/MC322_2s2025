package rpg.cenarios;

import java.util.Random;

import rpg.interfaces.Combatente;
import rpg.interfaces.Evento;
import rpg.monstro.Imperialista;

/**
 * Nas fases do meio, não a primeira nem a última, há um entreguista presente,
 * ele rouba a vida de Falsos Patriotas e Herois, dependendo de chance, e entrega
 * para o chefe imperialista.
 * É sempre executado na batalha, depende do gatilho para ser efetuado
 */

public class EventoEntregar implements Evento{
    private FaseDeCombate fase;
    private Random random = new Random();
    private Imperialista imperialista;

    public EventoEntregar(FaseDeCombate fase, Imperialista imperialista){
        this.fase = fase;
        this.imperialista = imperialista;
    }

    //verifica se estamos em uma fase do meio
    @Override
    public boolean verificarGatilho(){
        if(fase.getTipoCenario() == TipoCenario.CAVERNA)
            return true;
        return false;
    }

    /**
     * Verifica o gatilho e se está na fase certa, se a batalha acabou,
     * printa a mensagem de fuga do imperialista, a cada quatro turnos da
     * batalha, o evento é efetuado com chance de 50% de atingir o heroi
     * e 50% de atingir seu adversário
     * Recebe a informação do turno pela fase recebida no construtor
     */
    @Override
    public void executar(){
        fase.adicionarEvento(new EventoEntregar(fase, imperialista));

        if (fase.isConcluida() == true) {
            System.out.println("\nO entreguista fugiu para os Estados Unidos.");
            return;
        }
        
        var infoBatalha = fase.getInfoBatalha();

        if (infoBatalha.turno() == 1){
            System.out.println("Cuidado, há um entreguista à espreita");
            return;
        }

        if(infoBatalha.turno() % 4 == 0 ){
            Combatente alvo;
            if (random.nextInt(2) == 0)
                alvo = infoBatalha.heroi();
            else
                alvo = infoBatalha.monstro();
            System.out.println("\nEntreguista vai privatizar a vida de " + alvo.getNome() + "!!!");
            imperialista.receberCura(alvo.receberDano(10)); // TODO: colocar aleatoriedade no dano e balanceá-lo.
            return;
        }
    }
}