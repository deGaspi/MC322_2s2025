package rpg.interfaces;

import java.util.List;

import rpg.cenarios.Dificuldade;

public interface GeradorDeFases {
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
