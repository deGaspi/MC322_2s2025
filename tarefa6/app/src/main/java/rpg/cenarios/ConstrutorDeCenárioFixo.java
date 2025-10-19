package rpg.cenarios;

import java.util.ArrayList;
import java.util.Random;

import rpg.armas.Arma;
import rpg.armas.Chinelo;
import rpg.armas.Lança;
import rpg.armas.Repique;
import rpg.armas.SemArma;
import rpg.interfaces.GeradorDeFases;
import rpg.monstro.FalsoPatriota;
import rpg.monstro.Imperialista;

/**
 * Constrói as Fases de acordo com o tipo de cenário
 * 
 */

public class ConstrutorDeCenárioFixo implements GeradorDeFases {
    private ArrayList<FaseDeCombate> listaDeFases = new ArrayList<FaseDeCombate>();
    private static final Random random = new Random();
    private Dificuldade dif;
    private Imperialista imp;
    

    public FaseDeCombate gerarFase(TipoCenario tipo, int nivel){
        FaseDeCombate fase = new FaseDeCombate(tipo);
        switch (tipo) {
            case ENTRADA:
                fase.addMonstro(new FalsoPatriota(
                    "Falso Patriota 1",
                    Math.round(8 + nivel * 1.1f),
                    Math.round(2 + nivel * 1.1f),
                    Math.round(10 + nivel * 0.5f),
                    getRandArma(this.dif), this.dif));
                fase.addMonstro(new FalsoPatriota(
                    "Falso Patriota 2",
                    Math.round(8 + nivel * 1.1f),
                    Math.round(2 + nivel * 1.1f),
                    Math.round(10 + nivel * 0.5f),
                    getRandArma(this.dif), this.dif));
                break;
            case CAVERNA:
                fase.addMonstro(new FalsoPatriota(
                    "Falso Patriota 1",
                    Math.round(8 + nivel * 1.1f),
                    Math.round(2 + nivel * 1.1f),
                    Math.round(10 + nivel * 0.5f),
                    getRandArma(this.dif), this.dif));
                fase.addMonstro(new FalsoPatriota(
                    "Falso Patriota 2",
                    Math.round(8 + nivel * 1.1f),
                    Math.round(2 + nivel * 1.1f),
                    Math.round(10 + nivel * 0.5f),
                    getRandArma(this.dif), this.dif));
                    fase.adicionarEvento(new EventoEntregar(fase, this.imp));
                break;
            case CHEFE:
                fase.addMonstro(this.imp);
                break;
            default:
                throw new AssertionError("Enum inesperado: " + tipo);
        }
        return fase;
    }

    public ConstrutorDeCenárioFixo(Dificuldade dificulty) {
        this.dif = dificulty;
        imp = new Imperialista(
                "Imperialista",
                Math.round(10),
                Math.round(3),
                Math.round(30),
                new Repique(dif), dif);
    }

    /**
     * Cria a primeira fase e a luta com o chefe
     * fases adicionais são adicionada dentro do laço
     */
    public ArrayList<FaseDeCombate> gerar(int n, Dificuldade dif) {
        if (n < 1) {
            throw new IllegalArgumentException("Número de fases deve ser positivo");
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        if (n > 1)
            listaDeFases.add(gerarFase(TipoCenario.ENTRADA, 1));
            n--;

        // Fases do meio: Batalhas com falsos patriotas para descer os andares
        // Evento com entreguista pode ocorrer.
        while (n > 1) {
            FaseDeCombate fase = gerarFase(TipoCenario.CAVERNA, n);
            listaDeFases.add(fase);
            n--;
        }

        // Ultima fase: Batalha com imperialista
        listaDeFases.add(gerarFase(TipoCenario.CHEFE, n));

        return listaDeFases;
    }

    //Escolha da arma para monstros com peso da dificuldade
    private static Arma getRandArma(Dificuldade dificuldade) {
        // O peso afeta a probabilidade da arma ser escolhida.
        int[] pesos = {
            5, // pesoDesarmado
            4,  // pesoChinelo
            1 + dificuldade.valor, // pesoLança
            0 + dificuldade.valor, // pesoRepique
        }; 
        
        int totalDosPesos = 0;
        for(int p : pesos){
            totalDosPesos += p;
        }

        // Cria o vetor candidatos, que atuará como espaço amostral.
        // Se uma arma tem peso 5, será colocado 5 números que representam essa arma em candidatos.
        int[] candidatos = new int[totalDosPesos];
        int counter = 0;
        for(int i=0; i<pesos.length; i++){
            for(int j=0; j<pesos[i]; j++){
                candidatos[counter] = i;
                counter++;
            }
        }
        int escolhido = candidatos[random.nextInt(totalDosPesos)];

        switch (escolhido) {
            case 0:
                return new SemArma();
            case 1:
                return new Chinelo(dificuldade);
            case 2:
                return new Lança(dificuldade);
            case 3:
                return new Repique(dificuldade);
            default:
                throw new AssertionError("Escolha inesperada: " + escolhido);
        }
    }
}
