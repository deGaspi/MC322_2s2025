package classes.cenarios;
import java.util.ArrayList;
import java.util.Random;

import classes.interfaces.Fase;
import classes.heroi.Herói;
import classes.monstro.Monstro;
import classes.monstro.Monstro.monstroEnum;


// Como se fosse a classe Batalha
public class FaseDeCombate implements Fase{
    private TipoCenario tipo;
    private ArrayList<Monstro> monstros = new ArrayList<Monstro>();
    private int turno = 0;
    Random random = new Random();

    public FaseDeCombate(TipoCenario T){
        tipo = T;
    }

    public TipoCenario getTipoCenario(){
        return this.tipo;
    }

    public void addMonstro(Monstro m){
        monstros.add(m);
    }

    public boolean iniciar(Herói H){ // Aqui estaria a batalha, falta completar
        while(!this.isConcluida() || H.getPontosDeVida() != 0){
            EventoEntregar entregar = new EventoEntregar(monstros.get(monstros.size() - 1), tipo, turno);
            for(Monstro m : monstros){
                boolean ganhou;
                while(true){
                    if(m.getTipo() == monstroEnum.ENTREGUISTA){
                        System.out.println("O entreguista foge para os Estados Unidos");
                        System.out.println("-------------------------------------------------\n");
                        return true;
                    }
                    System.out.println("\n"+m.getNome() + " se aproxima com uma " + m.getArma().getNome() +" para defender os interesses extrangeiros.");
                    switch(random.nextInt(2)){
                        case 0:
                            entregar.setCombatente(m);
                            break;
                        case 1:
                            entregar.setCombatente(H);
                            break;
                    }
                    entregar.executar();
                    turno++;
                    System.out.println("\n-------------------- Turno " + turno + " --------------------");
                    System.out.println("Status do inimigo:");
                    m.exibirStatus();
                    System.out.println("Status do herói:");
                    H.exibirStatus();
                    if (m.getPontosDeVida() == 0) {
                        ganhou = true;
                        break;
                    }
                    if (H.getPontosDeVida() == 0) {
                        ganhou = false;
                        break;
                    }
                    H.escolherAcao(m);

                    // Verifica se a batalha acabou
                    if (m.getPontosDeVida() == 0) {
                        ganhou = true;
                        break;
                    }
                    if (H.getPontosDeVida() == 0) {
                        ganhou = false;
                        break;
                    }
                    m.escolherAcao(H);
                    System.out.println("-------------------------------------------------\n");
                }
                if(!ganhou){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isConcluida(){
        if (monstros.size() == 0){
            return true;
        }
        return false;
    }
    
}
