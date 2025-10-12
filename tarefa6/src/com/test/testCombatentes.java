package com.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.rpg.heroi.Passista;
import com.rpg.monstro.FalsoPatriota;
import com.rpg.interfaces.Combatente;
import com.rpg.interfaces.Lootavel;
import com.rpg.armas.SemArma;
import com.rpg.cenarios.Dificuldade;

public class testCombatentes {
    @Test
    public void implementsCombatente(){
        Passista p = new Passista("fulano", 10, 10, 10, 10);
        assertTrue(p instanceof Combatente, "Herói não implementa Combatente");
        FalsoPatriota f = new FalsoPatriota("Dagoth-Ur", 0, 0, 0, new SemArma(), Dificuldade.FACIL);
        assertTrue(f instanceof Combatente, "Monstro não implementa Combatente");
    }

    @Test
    public void heroiRecebeAtacaAlvo(){
        int dano = 10;
        Passista p = new Passista("fulano", 10000, 5, 0, 0);
        FalsoPatriota f = new FalsoPatriota("Dagoth-Ur", 10000, 5, 0, new SemArma(), Dificuldade.FACIL);
        int fLP = f.getPontosDeVida(); //Pontos de vida antes do dano
        p.darDano(f, dano);
        assertTrue((f.getPontosDeVida() == (fLP - dano)), "Heroi não ataca");
        int pLP = p.getPontosDeVida();
        f.darDano(p, dano);
        assertTrue((p.getPontosDeVida() == (pLP - dano)), "Heroi não recebe dano");
    }

    @Test
    public void monstroRecebeAtacaAlvo(){
        int dano = 10;
        Passista p = new Passista("fulano", 10000, 5, 0, 0);
        FalsoPatriota f = new FalsoPatriota("Dagoth-Ur", 10000, 5, 0, new SemArma(), Dificuldade.FACIL);
        int fLP = f.getPontosDeVida(); //Pontos de vida antes do dano
        p.darDano(f, dano);
        assertTrue((f.getPontosDeVida() == (fLP - dano)), "Monstro não recebe dano");
        int pLP = p.getPontosDeVida();
        f.darDano(p, dano);
        assertTrue((p.getPontosDeVida() == (pLP - dano)), "Monstro não ataca");
    }

    @Test
    public void monstroLootavel(){
        FalsoPatriota f = new FalsoPatriota("Dagoth-Ur", 10000, 5, 0, new SemArma(), Dificuldade.FACIL);
        assertTrue(f instanceof Lootavel, "Monstro não implementa Lootavel");
    }
}
