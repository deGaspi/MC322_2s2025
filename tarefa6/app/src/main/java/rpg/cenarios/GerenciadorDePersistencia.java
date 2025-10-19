
package rpg.cenarios;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


import rpg.heroi.Heroi;
import rpg.util.*;
import rpg.armas.*;


/**
 * Do jeito que a tarefa pede eu só consigo pensar em jeitos que
 * reestruturaria boa parte do código, como eu não quero ficar maluco,
 * irei fazer assim:
 * carregar jogo construirá as fases restantes e as colocará em um array o qual será
 * percorrido pela classe jogo
 * o método salvar jogo irá salvar as informações necessárias em um arquivo xml
 */
public class GerenciadorDePersistencia {
    private Heroi h;
    private int nOfFases;
    private int faseInicial;
    private List<FaseDeCombate> listaDeFases;
    private Dificuldade dif;

    public GerenciadorDePersistencia(){
    }


    public void salvarJogo(int nDeFases, String nomeArquivo, int faseAtual, Dificuldade dif, Heroi heroi, boolean derrotouOsDois) {
        try {
            // Criar estrutura de salvamento
            PhaseProperties phaseProps = new PhaseProperties(nDeFases, faseAtual, dif, derrotouOsDois);
            HeroSave heroSave = new HeroSave(heroi);
            SaveGame saveGame = new SaveGame(phaseProps, heroSave);

            // Configurar JAXB
            JAXBContext context = JAXBContext.newInstance(SaveGame.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Salvar em arquivo
            File file = new File("savedGames/"+ nomeArquivo + ".xml");
            marshaller.marshal(saveGame, file);
            System.out.println("Jogo salvo com sucesso em: " + file.getAbsolutePath());

        } catch (JAXBException e) {
            System.err.println("Erro ao salvar o jogo: " + e);
        }
    }

    public void carregarJogo(File file) throws Exception {
        try {
            // Configurar JAXB
            JAXBContext context = JAXBContext.newInstance(SaveGame.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Carregar dados
            SaveGame saveGame = (SaveGame) unmarshaller.unmarshal(file);
            PhaseProperties phaseProps = saveGame.getPhaseProperties();
            HeroSave heroSave = saveGame.getHero();

            // Restaurar estado do jogo
            this.nOfFases = phaseProps.getNOfPhases();
            this.faseInicial = phaseProps.getPhase();
            this.dif = Dificuldade.valueOf(phaseProps.getDificulty());
            
            // Recriar herói baseado na classe salva
            this.h = heroSave.getHClass().getDefaultInstance();
            this.h.setPontosDeVida(heroSave.getHLP());
            this.h.setForca(heroSave.getHStrength());
            this.h.setNivel(heroSave.getHLevel());
            this.h.setXp(heroSave.getHXp());

            //Lógica para equipar a arma a partir do nome
            //Poderia ser feito num Enum, mas o Hilder e a Ketty não deixaram
            switch(heroSave.getHWeapon()){
                case "Chinelo":
                    h.setArma(new Chinelo(this.dif));
                    break;
                case "Lança de Porta Bandeira":
                    h.setArma(new Lança(this.dif));
                    break;
                case "Repique-mor":
                    h.setArma(new Repique(this.dif));
                    break;
                case "Desarmado":
                    h.setArma(new SemArma());
                    break;
                default:
                    throw new Exception("Deu ruim no carregamento da arma");
            }

            if(phaseProps.isDefeatedBoth()){
                this.faseInicial++;
            }

            //Recria a lista de Fases
            ConstrutorDeCenárioFixo construtor = new ConstrutorDeCenárioFixo(dif);
            listaDeFases = construtor.gerar(nOfFases, dif);

        } catch (JAXBException e) {
            throw new Exception("Erro ao carregar o jogo: " + e.getMessage());
        }
    }
    
    public int getFaseInicial() { return faseInicial; }
    public int getNDeFases() { return nOfFases; }
    public List<FaseDeCombate> getListaDeFases() { return listaDeFases; }
    public Heroi getHeroi() { return h; }
    public Dificuldade getDificuldade() { return dif; }
}