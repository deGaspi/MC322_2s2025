package rpg.interfaces;

import rpg.cenarios.TipoCenario;
import rpg.heroi.Heroi;
import rpg.util.Desistencia;

public interface Fase {
    public boolean iniciar(Heroi heroi) throws Desistencia;
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public int getMonsterLife();
    public void adicionarEvento(Evento evento);
}