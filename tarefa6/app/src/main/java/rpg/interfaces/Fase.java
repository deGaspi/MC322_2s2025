package rpg.interfaces;

import rpg.cenarios.TipoCenario;
import rpg.heroi.Heroi;
import rpg.util.paradaJogador;

public interface Fase {
    public boolean iniciar(Heroi heroi) throws paradaJogador;
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public void adicionarEvento(Evento evento);

    // as fases devem ser serializáveis, para que possam ser salvas.
    public String getState(); 
    public void setState(String serializedState) throws IllegalArgumentException;
}