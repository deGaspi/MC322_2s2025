package com.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.rpg.monstro.FalsoPatriota;
import com.rpg.cenarios.Dificuldade;

public class testDificuldade {

    @Test
    public void testarDificuldade(){
        FalsoPatriota f1 = new FalsoPatriota("Atronach1", 10, 0, 0, null, Dificuldade.FACIL);
        FalsoPatriota f2 = new FalsoPatriota("Atronach2", 10, 0, 0, null, Dificuldade.DIFICIL);
        assertTrue(f1.getPontosDeVida() < f2.getPontosDeVida(), "Dificuldade não altera");
    }
    
}   
