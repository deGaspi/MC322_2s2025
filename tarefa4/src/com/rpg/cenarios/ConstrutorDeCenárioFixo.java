package com.rpg.cenarios;

import java.util.ArrayList;
import java.util.Random;

import com.rpg.armas.Arma;
import com.rpg.armas.Chinelo;
import com.rpg.armas.Lança;
import com.rpg.armas.Repique;
import com.rpg.armas.SemArma;
import com.rpg.interfaces.Fase;
import com.rpg.interfaces.GeradorDeFases;
import com.rpg.monstro.FalsoPatriota;
import com.rpg.monstro.Imperialista;

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
                multiplicadorHP = 1.25f;
                multiplicadorDano = 1.25f;
                multiplicadorXP = 1.25f;
                break;
            case DIFICIL:
                multiplicadorHP = 1.5f;
                multiplicadorDano = 1.5f;
                multiplicadorXP = 1.5f;
                break;
            default:
                throw new AssertionError("Dificuldade inesperada, valor recebido: " + dif);
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        FaseDeCombate primeiraFase = new FaseDeCombate(TipoCenario.ENTRADA);
        primeiraFase.addMonstro(new FalsoPatriota(
                "Falso Patriota 1",
                Math.round(8 * multiplicadorHP - n * 1.1f),
                Math.round(2 * multiplicadorDano - n * 1.1f),
                Math.round(10 * multiplicadorXP - n * 0.5f),
                getRandArma(dif)));
        primeiraFase.addMonstro(new FalsoPatriota(
                "Falso Patriota 2",
                Math.round(8 * multiplicadorHP),
                Math.round(2 * multiplicadorDano),
                Math.round(10 * multiplicadorXP),
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
                Math.round(8 * multiplicadorHP - n * 1.1f),
                Math.round(2 * multiplicadorDano - n * 1.1f),
                Math.round(10 * multiplicadorXP - n * 0.5f),
                getRandArma(dif)));
            fase.addMonstro(new FalsoPatriota(
                "Falso Patriota 2",
                Math.round(8 * multiplicadorHP - n * 1.1f),
                Math.round(2 * multiplicadorDano - n * 1.1f),
                Math.round(10 * multiplicadorXP - n * 0.5f),
                getRandArma(dif)));
            fase.adicionarEvento(new EventoEntregar(fase, imperialista));
            listaDeFases.add(fase);
            n--;
        }

        listaDeFases.add(ultimaFase);

        return listaDeFases;
    }

    //Escolha da arma com peso da dificuldade
    private static Arma getRandArma(Dificuldade dificuldade) {
        //pesoDesarmado, pesoChinelo, pesoLança, pesoRepique
        int[] pesos = {5, 4, 1 + dificuldade.valor, 0 + dificuldade.valor}; //o peso afeta a probabilidade da arma ser escolhida
        
        //Obtem o total dos pesos
        int totalDosPesos = 0;
        for(int p : pesos){
            totalDosPesos += p;
        }

        //Cria o vetor candidatos, que atuará como espaço amostral
        //Se uma arma tem peso 5, será colocado 5 números que representam essa arma em candidatos
        int[] candidatos = new int[totalDosPesos];
        int counter = 0;
        for(int i=0; i<pesos.length; i++){
            for(int j=0; j<pesos[i]; j++){
                candidatos[counter] = i;
                counter++;
            }
        }
        //Assim, temos chances equiprováveis de obter qualquer valor em candidatos
        int escolhido = candidatos[random.nextInt(totalDosPesos)];

        switch (escolhido) {
            case 1:
                return new Chinelo(dificuldade);
            case 2:
                return new Lança(dificuldade);
            case 3:
                return new Repique(dificuldade);
            case 0:
                return new SemArma();
            default:
                throw new AssertionError("Enum inesperado: " + escolhido);
        }
    }

}
