package com.rpglab.cenario;

import com.rpglab.combate.Evento;
import com.rpglab.personagens.heroi.Herói;

public interface Fase {
    public boolean iniciar(Herói heroi);
    public boolean isConcluida();
    public TipoCenario getTipoCenario();
    public void adicionarEvento(Evento evento);
}