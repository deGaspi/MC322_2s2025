package rpg.cenarios;

import java.util.ArrayList;
import java.util.List;

import rpg.armas.Arma;
import rpg.heroi.Heroi;
import rpg.interfaces.Evento;
import rpg.interfaces.Fase;
import rpg.interfaces.Lootavel;
import rpg.monstro.Monstro;
import rpg.util.paradaJogador;
import rpg.util.EquiparSemNivel;
import rpg.util.InputManager;
/**
 * É responsavel por administrar a progressão de fase.
 * Recebe o tipo de fase no construtor que dita o andamento
 * da fase
 */
public class FaseDeCombate implements Fase {
    private TipoCenario tipo;
    private InfoBatalha batalhaAtual = null;
    private boolean faseConcluida = false;
    private List<Monstro> monstros = new ArrayList<Monstro>();
    private List<Evento> eventos = new ArrayList<Evento>();

    public record InfoBatalha(int turno, Heroi heroi, Monstro monstro) {
    }

    public FaseDeCombate(TipoCenario T) {
        tipo = T;
    }

    @Override
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
    public String getState() {
        int mostrosDerrotados = 0;
        for (var monstro: monstros) {
            if (monstro.getPontosDeVida() > 0) {
                break;
            }
            mostrosDerrotados++;
        }
        return ""+mostrosDerrotados;
    }

    @Override
    public void setState(String state) {
        int i;
        try {
            i = Integer.parseInt(state);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
        if (i > monstros.size()) {
            throw new AssertionError();
        }
        if (i == monstros.size()) {
            faseConcluida = true;
        }
        while (i > 0) {
            monstros.get(--i).setPontosDeVida(0);
        }
    }

    public void matarMonstros(int n) {
        if (n > monstros.size()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            monstros.get(i).setPontosDeVida(0);
        }

    } 

    /**
     * Inicia a fase, mostra algumas informações e começa o laço que alterna
     * as ações do herói e do inimigo, ativa o EventoEntregar depois de cada 
     * alternação e verifica se a luta acabou no durante o laço, se acabou, 
     * chama PostKillInteract
     */
    @Override
    public boolean iniciar(Heroi heroi) throws paradaJogador {
        boolean ganhouFase = true;
        for (Monstro monstro : monstros) {
            if (monstro.getPontosDeVida() == 0) {
                continue;
            }
            
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
        faseConcluida  = true;
        return ganhouFase;
    }
    /**
     * Interação
     * Escolha opções depois que a batalha com cada inimigo acaba,
     * implementado com um switch simples
     * @param inimigoMorto
     * @param heroi
     * @throws Desistencia
     */
    private static void postKillInteract(Monstro inimigoMorto, Heroi heroi) throws paradaJogador {
        final String menu = """
                ==================================================
                [1] Vasculhar inimigo.
                [2] Informações do herói.
                [3] Continuar
                [4] Salvar Jogo
                [5] Desistir do jogo
                ==================================================
                Digite sua opção >
                """;
        
        boolean loop = true;
        while(loop){
            switch (InputManager.lerInteiro(menu, 1, 5)) {
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
                    throw new paradaJogador("Salvando Jogo", true);
                case 5:
                    throw new paradaJogador("Desistiu", false);
                default:
                    throw new AssertionError("Input inexperado.");
            }
        }
        

    }
}
