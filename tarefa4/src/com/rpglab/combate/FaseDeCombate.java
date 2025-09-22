package com.rpglab.combate;

import java.util.ArrayList;
import java.util.List;

import com.rpglab.cenario.Fase;
import com.rpglab.cenario.TipoCenario;
import com.rpglab.personagens.heroi.Herói;
import com.rpglab.personagens.monstro.Monstro;

// Como se fosse a classe Batalha
public class FaseDeCombate implements Fase {
    private TipoCenario tipo;
    private InfoBatalha batalhaAtual = null;
    private boolean faseConcluida = false;
    private List<Monstro> monstros = new ArrayList<Monstro>();
    private List<Evento> eventos = new ArrayList<Evento>();

    public record InfoBatalha(int turno, Herói heroi, Monstro monstro) {
    }

    public FaseDeCombate(TipoCenario T) {
        tipo = T;
    }

    public TipoCenario getTipoCenario() {
        return tipo;
    }

    public InfoBatalha getInfoBatalha() {
        return batalhaAtual;
    }

    public void addMonstro(Monstro m) {
        monstros.add(m);
    }

    @Override
    public void adicionarEvento(Evento evento) {
        eventos.addFirst(evento);
    }

    @Override
    public boolean isConcluida() {
        return faseConcluida;
    }

    @Override
    public boolean iniciar(Herói heroi) {
        boolean ganhouFase = true;
        for (Monstro monstro : monstros) {
            int turno = 1;
            System.out.println("\n" + monstro.getNome() + " se aproxima com uma " + monstro.getArma().getNome()
                    + " para defender os interesses extrangeiros.");
            while (true) {
                // Print do turno
                System.out.println("\n-------------------- Turno " + turno + " --------------------");
                System.out.println("Status do inimigo:");
                monstro.exibirStatus();
                System.out.println("Status do herói:");
                heroi.exibirStatus();
                batalhaAtual = new InfoBatalha(turno, heroi, monstro);

                // Round do heroi
                if (monstro.getPontosDeVida() == 0)
                    break;
                if (heroi.getPontosDeVida() == 0) {
                    ganhouFase = false;
                    break;
                }
                heroi.escolherAcao().executar(heroi, monstro);
                ;

                // round do monstro
                if (monstro.getPontosDeVida() == 0)
                    break;
                if (heroi.getPontosDeVida() == 0) {
                    ganhouFase = false;
                    break;
                }
                monstro.escolherAcao().executar(monstro, heroi);
                ;

                // Loop de eventos
                for (var evento : eventos) {
                    if (evento.verificarGatilho()) {
                        eventos.remove(evento);
                        evento.executar();
                    }
                }
                System.out.println("-------------------------------------------------\n");
                turno++;
            }
            if (!ganhouFase)
                break;
        }
        faseConcluida = true;
        return ganhouFase;
    }
}
