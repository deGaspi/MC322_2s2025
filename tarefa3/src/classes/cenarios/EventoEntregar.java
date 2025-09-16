package classes.cenarios;

import classes.interfaces.Evento;
import classes.monstro.Monstro;
import classes.interfaces.Combatente;


public class EventoEntregar implements Evento{
    private int turno;
    private Monstro entreguista;
    private TipoCenario tipo;
    private Combatente alvo;

    public EventoEntregar(Monstro ent, TipoCenario T, int round){
        turno = round;
        entreguista = ent;
        tipo = T;
    }

    public boolean verificarGatilho(){
        if(tipo == TipoCenario.CAVERNA){
            if(turno == 0){
                System.out.println("Cuidado, há um entreguista à espreita");
            }
            if(turno % 4 == 0 ){
                return true;
            }
        }
        return false;
    }

    public void incrementarTurno(){
        turno++;
    }

    public void setCombatente(Combatente c){
        alvo = c;
    }

    public void executar(){
        this.incrementarTurno();
        if(this.verificarGatilho()){
            entreguista.escolherAcao(alvo);
        }
    }
}
