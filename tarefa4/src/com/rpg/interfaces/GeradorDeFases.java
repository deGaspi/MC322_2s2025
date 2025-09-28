package com.rpg.interfaces;

import java.util.List;

import com.rpg.cenarios.Dificuldade;

public interface GeradorDeFases {
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
