package classes.interfaces;

import classes.cenarios.TipoCenario;
import classes.heroi.Herói;

public interface Fase {
    public boolean iniciar(Herói h);
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
}