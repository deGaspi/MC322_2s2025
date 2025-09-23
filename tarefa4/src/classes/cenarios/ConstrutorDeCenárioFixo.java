package classes.cenarios;

import java.util.ArrayList;
import java.util.Random;

import classes.interfaces.GeradorDeFases;
import classes.monstro.FalsoPatriota;
import classes.monstro.Imperialista;
import classes.armas.Arma;
import classes.armas.Chinelo;
import classes.armas.Lança;
import classes.armas.Repique;
import classes.armas.SemArma;
import classes.interfaces.Fase;

public class ConstrutorDeCenárioFixo implements GeradorDeFases {
    private ArrayList<Fase> listaDeFases = new ArrayList<Fase>();
    private static final Random random = new Random();

    public ConstrutorDeCenárioFixo() {
    }

    public ArrayList<Fase> gerar(int n, Dificuldade dif) {
        if (n < 1) {
            throw new IllegalArgumentException("Número de fases deve ser positivo");
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        FaseDeCombate primeiraFase = new FaseDeCombate(TipoCenario.ENTRADA);
        primeiraFase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8, 2, 8,  getRandArma(dif)));
        primeiraFase.addMonstro(new FalsoPatriota("Falso Patriota 2", 8, 2, 8,  getRandArma(dif)));
        listaDeFases.add(primeiraFase);

        // Ultima fase: Batalha com imperialista
        var imperialista = new Imperialista("Imperialista", 10 * n, 3 * n, 30 * n,  getRandArma(dif));
        FaseDeCombate ultimaFase = new FaseDeCombate(TipoCenario.CHEFE);
        ultimaFase.addMonstro(imperialista);
        n--;

        if (n == 0)
            return listaDeFases;

        // Fases do meio: Batalhas com falsos patriotas para descer os andares
        // Evento com entreguista pode ocorrer.
        while (n > 1) {
            FaseDeCombate fase = new FaseDeCombate(TipoCenario.CAVERNA);
            fase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8 * n, 2 * n, 8 * n,  getRandArma(dif)));
            fase.addMonstro(new FalsoPatriota("Falso Patriota 2", 8 * n, 2 * n, 8 * n,  getRandArma(dif)));
            fase.adicionarEvento(new EventoEntregar(fase, imperialista));
            listaDeFases.add(fase);
            n--;
        }

        listaDeFases.add(ultimaFase);

        return listaDeFases;
    }

    private static Arma getRandArma(Dificuldade dificuldade) {
        enum escolhas {
            CHINELO(1), LANÇA(1), REPIQUE(2), DESARMADO(2);
            public int peso;
            escolhas(int peso) {
                this.peso = peso;
            }
        }

        int totalDosPesos = 0;
        for (var candidato: escolhas.values()) {
            totalDosPesos += candidato.peso;
        }

        int n = random.nextInt(totalDosPesos);
        escolhas escolhido = null;
        for (escolhas candidato: escolhas.values()) {
            totalDosPesos -= candidato.peso;
            if (totalDosPesos <= n) {
                escolhido = candidato;
                break;
            }
        }

        switch (escolhido) {
            case CHINELO:
                return new Chinelo(dificuldade);
            case LANÇA:
                return new Lança(dificuldade);
            case REPIQUE:
                return new Repique(dificuldade);
            case DESARMADO:
                return new SemArma();
            default:
                throw new AssertionError("Enum inesperado: " + escolhido);
        }
    }

}
