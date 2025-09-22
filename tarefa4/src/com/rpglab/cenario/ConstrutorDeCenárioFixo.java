package com.rpglab.cenario;

import java.util.ArrayList;

import com.rpglab.combate.EventoEntregar;
import com.rpglab.combate.FaseDeCombate;
import com.rpglab.personagens.monstro.FalsoPatriota;
import com.rpglab.personagens.monstro.Imperialista;


public class ConstrutorDeCenárioFixo implements GeradorDeFases {
    private ArrayList<Fase> listaDeFases = new ArrayList<Fase>();

    public ConstrutorDeCenárioFixo() {
    }

    public ArrayList<Fase> gerar(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Número de fases deve ser positivo");
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        FaseDeCombate primeiraFase = new FaseDeCombate(TipoCenario.ENTRADA);
        primeiraFase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8, 2, 8));
        primeiraFase.addMonstro(new FalsoPatriota("Falso Patriota 2", 8, 2, 8));
        listaDeFases.add(primeiraFase);

        // Ultima fase: Batalha com imperialista
        var imperialista = new Imperialista("Imperialista", 10 * n, 3 * n, 30 * n);
        FaseDeCombate ultimaFase = new FaseDeCombate(TipoCenario.CHEFE);
        ultimaFase.addMonstro(imperialista);
        n--;

        if (n == 0)
            return listaDeFases;

        // Fases do meio: Batalhas com falsos patriotas para descer os andares
        // Evento com entreguista pode ocorrer.
        while (n > 1) {
            FaseDeCombate fase = new FaseDeCombate(TipoCenario.CAVERNA);
            fase.addMonstro(new FalsoPatriota("Falso Patriota 1", 8 * n, 2 * n, 8 * n));
            fase.addMonstro(new FalsoPatriota("Falso Patriota 2", 8 * n, 2 * n, 8 * n));
            fase.adicionarEvento(new EventoEntregar(fase, imperialista));
            listaDeFases.add(fase);
            n--;
        }

        listaDeFases.add(ultimaFase);

        return listaDeFases;
    }
}
