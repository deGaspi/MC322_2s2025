package rpg.util;

import javax.xml.bind.annotation.*;

import rpg.cenarios.Dificuldade;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameProperties {
    @XmlElement(name = "phase")
    private int phase;

    @XmlElement(name = "nOfPhases")
    private int nOfPhases;
    
    @XmlElement(name = "dificulty")
    private String dificulty;

    @XmlElement(name = "phaseState")
    private String phaseState;

    // Construtores, getters e setters
    public GameProperties() {}

    public GameProperties(int nOfPhases, int phase, Dificuldade dificulty, String phaseState) {
        this.nOfPhases = nOfPhases;
        this.phase = phase;
        this.dificulty = dificulty.name();
        this.phaseState = phaseState;
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }

    public int getNOfPhases() { return nOfPhases; }
    public void setNOfPhases(int nOfPhases) { this.nOfPhases = nOfPhases; }
    
    public String getDificulty() { return dificulty; }
    public void setDificulty(String dificulty) { this.dificulty = dificulty; }

    public String getPhaseState() { return phaseState; }
    public void setPhaseState(String phaseState) { this.phaseState = phaseState; }
}