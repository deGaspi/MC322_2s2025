package classes.cenarios;
import java.util.ArrayList;

import classes.interfaces.Fase;
import classes.heroi.Herói;
import classes.monstro.Monstro;


// Como se fosse a classe Batalha
public class FaseDeCombate implements Fase{
    private TipoCenario tipo;
    private ArrayList<Monstro> monstros = new ArrayList<Monstro>();


    public FaseDeCombate(TipoCenario T){
        tipo = T;
    }

    public TipoCenario getTipoCenario(){
        return this.tipo;
    }

    public void addMonstro(Monstro m){
        monstros.add(m);
    }

    public void iniciar(Herói H){ // Aqui estaria a batalha, falta completar
        System.out.println(tipo.getDescription());
        while(!this.isConcluida() || H.getPontosDeVida() != 0){
            EventoEntregar entregar = new EventoEntregar(monstros.get(monstros.size() - 1), tipo);
            for(Monstro m : monstros){
                while(m.getPontosDeVida() != 0){
                    entregar.executar();  // Incrementa o turno. Verifica se estamos na caverna, se sim, o último monstro deve ser o entreguista e dará tudo certo.
                    H.escolherAcao(m);
                    m.escolherAcao(H);
                }
            }
        }
    }

    public boolean isConcluida(){
        if (monstros.size() == 0){
            return true;
        }
        return false;
    }
    
}
