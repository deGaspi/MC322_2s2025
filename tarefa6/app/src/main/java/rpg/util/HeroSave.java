package rpg.util;

import rpg.heroi.Heroi;
import rpg.heroi.HeroEnum;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class HeroSave {
    @XmlElement(name = "hLP")
    private int hLP;
    
    @XmlElement(name = "hStrength")
    private int hStrength;
    
    @XmlElement(name = "hLevel")
    private int hLevel;
    
    @XmlElement(name = "hXp")
    private int hXp;
    
    @XmlElement(name = "hWeapon")
    private String hWeapon;
    
    @XmlElement(name = "hClass")
    private HeroEnum hClass;

    // Construtor padrão necessário para JAXB
    public HeroSave() {}

    public HeroSave(Heroi heroi) {
        this.hLP = heroi.getPontosDeVida();
        this.hStrength = heroi.getForca();
        this.hLevel = heroi.getNivel();
        this.hXp = heroi.getXp();
        this.hWeapon = heroi.getArma().getNome();
        this.hClass = heroi.getHeroType();
    }

    // Getters e Setters
    public int getHLP() { return hLP; }
    public void setHLP(int hLP) { this.hLP = hLP; }
    
    public int getHStrength() { return hStrength; }
    public void setHStrength(int hStrength) { this.hStrength = hStrength; }
    
    public int getHLevel() { return hLevel; }
    public void setHLevel(int hLevel) { this.hLevel = hLevel; }
    
    public int getHXp() { return hXp; }
    public void setHXp(int hXp) { this.hXp = hXp; }
    
    public String getHWeapon() { return hWeapon; }
    public void setHWeapon(String hWeapon) { this.hWeapon = hWeapon; }
    
    public HeroEnum getHClass() { return hClass; }
    public void setHClass(HeroEnum hClass) { this.hClass = hClass; }
}