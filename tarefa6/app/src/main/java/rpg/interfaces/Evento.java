package rpg.interfaces;

public interface Evento {
    public boolean verificarGatilho(); // TODO: não faz sentido ser publico. tem que ser privado e chamado automaticamente pelo metodo executar().
    public void executar();
}
