package rpg.interfaces;

import java.util.List;

import rpg.cenarios.Dificuldade;
import rpg.cenarios.FaseDeCombate;

public interface GeradorDeFases {
    public List<FaseDeCombate> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
