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

        final float multiplicadorHP, multiplicadorDano, multiplicadorXP;
        switch (dif) {
            case FACIL:
                multiplicadorHP = 1f;
                multiplicadorDano = 1f;
                multiplicadorXP = 1f;
                break;
            case MEDIO:
                multiplicadorHP = 1.5f;
                multiplicadorDano = 1.5f;
                multiplicadorXP = 1.5f;
                break;
            case DIFICIL:
                multiplicadorHP = 2f;
                multiplicadorDano = 2f;
                multiplicadorXP = 2f;
                break;
            default:
                throw new AssertionError("Dificuldade inesperada, valor recebido: " + dif);
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        FaseDeCombate primeiraFase = new FaseDeCombate(TipoCenario.ENTRADA);
        primeiraFase.addMonstro(new FalsoPatriota(
                "Falso Patriota 1",
                Math.round(8 * multiplicadorHP),
                Math.round(2 * multiplicadorDano),
                Math.round(8 * multiplicadorXP),
                getRandArma(dif)));
        primeiraFase.addMonstro(new FalsoPatriota(
                "Falso Patriota 2",
                Math.round(8 * multiplicadorHP),
                Math.round(2 * multiplicadorDano),
                Math.round(8 * multiplicadorXP),
                getRandArma(dif)));
        listaDeFases.add(primeiraFase);

        // Ultima fase: Batalha com imperialista
        var imperialista = new Imperialista(
                "Imperialista",
                Math.round(10 * multiplicadorHP),
                Math.round(3 * multiplicadorDano),
                Math.round(30 * multiplicadorXP),
                getRandArma(dif));
        FaseDeCombate ultimaFase = new FaseDeCombate(TipoCenario.CHEFE);
        ultimaFase.addMonstro(imperialista);
        n--;

        if (n == 0)
            return listaDeFases;

        // Fases do meio: Batalhas com falsos patriotas para descer os andares
        // Evento com entreguista pode ocorrer.
        while (n > 1) {
            FaseDeCombate fase = new FaseDeCombate(TipoCenario.CAVERNA);
            fase.addMonstro(new FalsoPatriota(
                "Falso Patriota 1",
                Math.round(8 * multiplicadorHP),
                Math.round(2 * multiplicadorDano),
                Math.round(8 * multiplicadorXP),
                getRandArma(dif)));
            fase.addMonstro(new FalsoPatriota(
                "Falso Patriota 2",
                Math.round(8 * multiplicadorHP),
                Math.round(2 * multiplicadorDano),
                Math.round(8 * multiplicadorXP),
                getRandArma(dif)));
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
        for (var candidato : escolhas.values()) {
            totalDosPesos += candidato.peso;
        }

        int n = random.nextInt(totalDosPesos);
        escolhas escolhido = null;
        for (escolhas candidato : escolhas.values()) {
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
