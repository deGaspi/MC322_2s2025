package classes;

import java.util.List;

import classes.monstro.Monstro;

public record Fase(int nivel, String ambiente, List<Monstro> monstros) {
}
