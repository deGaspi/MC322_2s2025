package com.rpg.cenarios;

import java.util.ArrayList;
import java.util.List;

import com.rpg.armas.Arma;
import com.rpg.heroi.Herói;
import com.rpg.interfaces.Evento;
import com.rpg.interfaces.Fase;
import com.rpg.interfaces.Lootavel;
import com.rpg.monstro.Monstro;
import com.rpg.util.Desistencia;
import com.rpg.util.EquiparSemNivel;
import com.rpg.util.InputManager;

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
    public boolean iniciar(Herói heroi) throws Desistencia {
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
            postKillInteract(monstro, heroi);
        }
        faseConcluida = true;
        return ganhouFase;
    }

    private static void postKillInteract(Monstro inimigoMorto, Herói heroi) throws Desistencia {
        final String menu = """
                ==================================================
                [1] Vasculhar inimigo.
                [2] Informações do herói.
                [3] Continuar
                [4] Desistir do jogo
                ==================================================
                Digite sua opção >
                """;
        
        boolean loop = true;
        while(loop){
            switch (InputManager.lerInteiro(menu, 1, 4)) {
                case 1:
                    if (inimigoMorto instanceof Lootavel lootavel) {
                        var loot = lootavel.droparLoot();
                        if (loot instanceof Arma arma && arma.getDano() > heroi.getArma().getDano()) {
                            try{
                                heroi.receberArma(arma);
                            } catch(EquiparSemNivel e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    break;
                case 2:
                    heroi.exibirStatus();
                    break;
                case 3:
                    loop = false;
                    break;
                case 4:
                    throw new Desistencia("Desistiu");
                default:
                    throw new AssertionError("Input inexperado.");
            }
        }
        

    }
}
