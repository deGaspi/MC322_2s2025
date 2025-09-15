package classes.cenarios;

import classes.interfaces.Evento;
import classes.monstro.Monstro;
import classes.interfaces.Combatente;


public class EventoEntregar implements Evento{
    private int turno;
    private Monstro entreguista;
    private Combatente alvo;
    private TipoCenario tipo;

    public EventoEntregar(Monstro ent, TipoCenario T){
        turno = 0;
        entreguista = ent;
        tipo = T;
    }

    public boolean verificarGatilho(){
        this.incrementarTurno();
        if(turno % 4 == 0 && tipo == TipoCenario.CAVERNA){
            return true;
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
        if(this.verificarGatilho()){
            entreguista.escolherAcao(alvo);
        }
    }
}
