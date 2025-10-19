package rpg.util;

import javax.xml.bind.annotation.*;

import rpg.cenarios.Dificuldade;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhaseProperties {
    @XmlElement(name = "phase")
    private int phase;

    @XmlElement(name = "nOfPhases")
    private int nOfPhases;
    
    @XmlElement(name = "dificulty")
    private String dificulty;

    @XmlElement(name = "defeatedBoth")
    private boolean defeatedBoth;

    // Construtores, getters e setters
    public PhaseProperties() {}

    public PhaseProperties(int nOfPhases, int phase, Dificuldade dificulty, boolean defeatedBoth) {
        this.nOfPhases = nOfPhases;
        this.phase = phase;
        this.dificulty = dificulty.name();
        this.defeatedBoth = defeatedBoth;
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }

    public int getNOfPhases() { return nOfPhases; }
    public void setNOfPhases(int nOfPhases) { this.nOfPhases = nOfPhases; }
    
    public String getDificulty() { return dificulty; }
    public void setDificulty(String dificulty) { this.dificulty = dificulty; }

    public boolean isDefeatedBoth() { return defeatedBoth; }
    public void setDefeatedBoth(boolean defeatedBoth) { this.defeatedBoth = defeatedBoth;}
}