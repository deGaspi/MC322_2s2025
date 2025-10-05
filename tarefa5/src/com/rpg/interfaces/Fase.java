package com.rpg.interfaces;

import com.rpg.cenarios.TipoCenario;
import com.rpg.heroi.Herói;
import com.rpg.util.Desistencia;

public interface Fase {
    public boolean iniciar(Herói heroi) throws Desistencia;
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public void adicionarEvento(Evento evento);
}