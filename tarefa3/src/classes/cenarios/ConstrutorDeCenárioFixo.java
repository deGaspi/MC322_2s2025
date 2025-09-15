package classes.cenarios;

import java.util.ArrayList;
import java.util.List;

import classes.interfaces.GeradorDeFases;
import classes.monstro.Entreguista;
import classes.monstro.FalsoPatriota;
import classes.monstro.Imperialista;
import classes.interfaces.Fase;

// Basicamente, a mesma coisa que você fez, mas com a implementação diferente
public class ConstrutorDeCenárioFixo implements GeradorDeFases{
    private ConstrutorDeCenárioFixo() {
    }

    public List<Fase> gerar(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Número de fases deve ser positivo");
        }

        List<Fase> listaDeFases = new ArrayList<Fase>();

        // Ultima fase: Batalha com imperialista
        var imperialista = new Imperialista("Imperialista", 10*n, 3*n, 30*n);
        var ultimaFase = new FaseDeCombate(TipoCenario.CHEFE);
        ultimaFase.addMonstro(imperialista);
        listaDeFases.addLast(ultimaFase);
        n--;

        if (n == 0)
            return listaDeFases;

        // Fases do meio: Batalhas com falsos patriotas e entreguistas para descer os andares
        while (n > 1) {
            var fase = new FaseDeCombate(TipoCenario.CAVERNA);
            fase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8*n, 2*n, 8*n));
            fase.addMonstro(new FalsoPatriota("Falso Patriota 2", 8*n, 2*n, 8*n));
            fase.addMonstro(new Entreguista("Entreguista", 8*n, 2*n, 8*n, imperialista));
            listaDeFases.addFirst(fase);
            n--;
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        var primeiraFase = new FaseDeCombate(TipoCenario.ENTRADA);
        primeiraFase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8*n, 2*n, 8*n));
        primeiraFase.addMonstro(new FalsoPatriota("Flaso Patriota 2", 8*n, 2*n, 8*n));
        listaDeFases.addFirst(primeiraFase);

        return listaDeFases;
    }
}
