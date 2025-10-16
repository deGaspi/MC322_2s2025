package rpg.util;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "saveGame")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveGame {
    @XmlElement(name = "phaseProperties")
    private PhaseProperties phaseProperties;
    
    @XmlElement(name = "hero")
    private HeroSave hero;

    // Construtores, getters e setters
    public SaveGame() {}

    public SaveGame(PhaseProperties phaseProperties, HeroSave hero) {
        this.phaseProperties = phaseProperties;
        this.hero = hero;
    }

    public PhaseProperties getPhaseProperties() { return phaseProperties; }
    public void setPhaseProperties(PhaseProperties phaseProperties) { this.phaseProperties = phaseProperties; }
    
    public HeroSave getHero() { return hero; }
    public void setHero(HeroSave hero) { this.hero = hero; }
}