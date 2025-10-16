package rpg.util;

import javax.xml.bind.annotation.*;

import rpg.cenarios.Dificuldade;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhaseProperties {
    @XmlElement(name = "phase")
    private int phase;
    
    @XmlElement(name = "dificulty")
    private String dificulty;

    @XmlElement(name = "defeatedBoth")
    private boolean defeatedBoth;

    // Construtores, getters e setters
    public PhaseProperties() {}

    public PhaseProperties(int phase, Dificuldade dificulty, boolean defeatedBoth) {
        this.phase = phase;
        this.dificulty = dificulty.name();
        this.defeatedBoth = defeatedBoth;
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }
    
    public String getDificulty() { return dificulty; }
    public void setDificulty(String dificulty) { this.dificulty = dificulty; }

    public boolean isDefeatedBoth() { return defeatedBoth; }
    public void setDefeatedBoth(boolean defeatedBoth) { this.defeatedBoth = defeatedBoth;}
}