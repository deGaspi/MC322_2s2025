package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.rpg.heroi.Passista;
import com.rpg.util.EquiparSemNivel;
import com.rpg.armas.Repique;
import com.rpg.cenarios.Dificuldade;
public class testExceções {
    @Test
    public void testEquiparSemNivel(){
        Passista p = new Passista("null", 10, 10, 0, 0);
        Repique r = new Repique(Dificuldade.DIFICIL);
        EquiparSemNivel e = assertThrows(EquiparSemNivel.class, () -> p.receberArma(r));
        assertEquals(e.getMessage(), p.getNome() + " não tem experiência suficiente para lidar com " + r.getNome());
}
}

