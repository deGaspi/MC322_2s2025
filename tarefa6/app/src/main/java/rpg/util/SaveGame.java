package rpg.util;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "saveGame")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveGame {
    @XmlElement(name = "phaseProperties")
    private GameProperties gameProperties;
    
    @XmlElement(name = "hero")
    private HeroSave hero;

    // Construtores, getters e setters
    public SaveGame() {}

    public SaveGame(GameProperties gameProperties, HeroSave hero) {
        this.gameProperties = gameProperties;
        this.hero = hero;
    }

    public GameProperties getGameProperties() { return gameProperties; }
    public void setGameProperties(GameProperties gameProperties) { this.gameProperties = gameProperties; }
    
    public HeroSave getHero() { return hero; }
    public void setHero(HeroSave hero) { this.hero = hero; }
}