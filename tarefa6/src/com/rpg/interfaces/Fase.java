package com.rpg.interfaces;

import com.rpg.cenarios.TipoCenario;
import com.rpg.heroi.Heroi;
import com.rpg.util.Desistencia;

public interface Fase {
    public boolean iniciar(Heroi heroi) throws Desistencia;
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public int getMonsterLife();
    public void adicionarEvento(Evento evento);
}