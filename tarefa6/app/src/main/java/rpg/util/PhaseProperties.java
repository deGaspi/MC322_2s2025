package rpg.util;

import javax.xml.bind.annotation.*;

import rpg.cenarios.Dificuldade;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhaseProperties {
    @XmlElement(name = "phase")
    private int phase;
    
    @XmlElement(name = "dificulty")
    private String dificulty;

    // Construtores, getters e setters
    public PhaseProperties() {}

    public PhaseProperties(int phase, Dificuldade dificulty) {
        this.phase = phase;
        this.dificulty = dificulty.name();
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }
    
    public String getDificulty() { return dificulty; }
    public void setDificulty(String dificulty) { this.dificulty = dificulty; }
}