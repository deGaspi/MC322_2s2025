package classes;

import java.util.ArrayList;
import java.util.List;

import classes.monstro.Entreguista;
import classes.monstro.FalsoPatriota;
import classes.monstro.Imperialista;
import classes.monstro.Monstro;

public class ConstrutorDeCenário {
    private ConstrutorDeCenário() {
    }

    public static List<Fase> gerarFases(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Número de fases deve ser positivo");
        }

        List<Fase> listaDeFases = new ArrayList<Fase>();

        // Ultima fase: Batalha com imperialista
        var imperialista = new Imperialista("Imperialista", 1, 1, 1); // TODO: arrumar status.
        var ultimaFase = new Fase(
                1, // TODO: arrumar nível.
                "Você desce até o fim da caverna. Agora é a sua chance de acabar com imperialista!!",
                new ArrayList<Monstro>()
            );
        ultimaFase.monstros().add(imperialista);
        listaDeFases.addLast(ultimaFase);
        n--;

        if (n == 0)
            return listaDeFases;

        // Fases do meio: Batalhas com falsos patriotas e entreguistas para descer os andares
        while (n > 1) {
            List<Monstro> monstros = new ArrayList<Monstro>();
            monstros.add(new FalsoPatriota("Falso Patriota 1", 1, 1, 1)); // TODO: arrumar status.
            monstros.add(new FalsoPatriota("Falso Patriota 2", 1, 1, 1)); // TODO: arrumar status.
            monstros.add(new Entreguista("Entreguista", 1, 1, 1, imperialista)); // TODO: arrumar status.
            var fase = new Fase(
                    1, // TODO: arrumar nível.
                    "Você desce para o " + n + "° andar, e se depara com 2 falsos patriotas e um entreguista.", // TODO: testar se a numeração dos andares está certa.
                    monstros
                );
            listaDeFases.addFirst(fase);
            n--;
        }

        // Primeira fase: Batalha com falsos patriotas para entrar na caverna.
        List<Monstro> monstros = new ArrayList<Monstro>();
        monstros.add(new FalsoPatriota("Falso Patriota 1", 1, 1, 1)); // TODO: arrumar status.
        monstros.add(new FalsoPatriota("Flaso Patriota 2", 1, 1, 1)); // TODO: arrumar status.
        var primeiraFase = new Fase(
                1, // TODO: arrumar nível.
                "Você se aproxima da entrada da caverna, porém dois falsos patriotas te impedem. Prepare-se para a batalha!!",
                monstros
            );
        listaDeFases.addFirst(primeiraFase);

        return listaDeFases;
    }
}
