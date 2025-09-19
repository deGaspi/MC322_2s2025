package classes.cenarios;

import classes.interfaces.Evento;
import classes.monstro.Imperialista;

import java.util.Random;

import classes.interfaces.Combatente;


public class EventoEntregar implements Evento{
    private FaseDeCombate fase;
    private Random random = new Random();
    private Imperialista imperialista;

    public EventoEntregar(FaseDeCombate fase, Imperialista imperialista){
        this.fase = fase;
        this.imperialista = imperialista;
    }

    @Override
    public boolean verificarGatilho(){
        if(fase.getTipoCenario() == TipoCenario.CAVERNA)
            return true;
        return false;
    }

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