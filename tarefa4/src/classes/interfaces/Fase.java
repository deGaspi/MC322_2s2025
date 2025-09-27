package classes.interfaces;

import classes.cenarios.Desistencia;
import classes.cenarios.TipoCenario;
import classes.heroi.Herói;

public interface Fase {
    public boolean iniciar(Herói heroi) throws Desistencia;
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public void adicionarEvento(Evento evento);
}