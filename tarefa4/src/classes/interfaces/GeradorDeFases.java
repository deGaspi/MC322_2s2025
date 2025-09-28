package classes.interfaces;

import java.util.List;

import classes.cenarios.Dificuldade;

public interface GeradorDeFases {
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
