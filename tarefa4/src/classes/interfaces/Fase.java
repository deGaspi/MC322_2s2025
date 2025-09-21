package classes.interfaces;

import classes.cenarios.TipoCenario;
import classes.heroi.Herói;

public interface Fase {
    public boolean iniciar(Herói heroi);
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public void adicionarEvento(Evento evento);
}