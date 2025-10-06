package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rpg.util.InputManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para InputManager.
 * Testa a leitura de entradas do usuário, simulando o System.in.
 */
class InputManagerTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayInputStream testIn;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @AfterEach
    /*
     * Restaura a entrada e saida padrao.
     */
    void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    /*
     */
    void setStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /*
     * Simula a entrada do usuario
     * 
     * @param data String representando a entrada a ser testada.
     */
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    // -----------------------------------------------------------------
    // Testes para o método lerString
    // -----------------------------------------------------------------

    @Test
    @DisplayName("lerString: Deve retornar a string fornecida (caso comum)")
    void testLerString_CasoComum() {
        String inputUsuario = "Uma entrada de texto qualquer";
        provideInput(inputUsuario + "\n");

        String mensagem = "Digite seu nome: ";
        var input = new InputManager(new Scanner(System.in));
        String resultado = input.lerString(mensagem);

        assertEquals(inputUsuario, resultado, "O resultado retornado deve ser igual à entrada do usuário.");
        assertEquals(mensagem, outContent.toString(), "A mensagem de prompt deve ser exibida corretamente.");
    }

    @Test
    @DisplayName("lerString: Deve retornar string vazia se o usuário só pressionar Enter (caso de exceção)")
    void testLerString_CasoExcecao_EntradaVazia() {
        provideInput("\n");

        String mensagem = "Digite algo (ou nada): ";
        var input = new InputManager(new Scanner(System.in));
        String resultado = input.lerString(mensagem);

        assertTrue(resultado.isEmpty(), "O resultado deve ser uma string vazia.");
        assertEquals(mensagem, outContent.toString(), "A mensagem de prompt deve ser exibida.");
    }

    // -----------------------------------------------------------------
    // Testes para o método lerSimNao
    // -----------------------------------------------------------------

    @Test
    @DisplayName("lerSimNao: Deve retornar true para a entrada 's' (caso comum)")
    void testLerSimNao_CasoComum_Sim() {
        provideInput("s\n"); // Simula a entrada "s"

        String mensagem = "Deseja continuar? (s/n): ";
        var input = new InputManager(new Scanner(System.in));
        boolean resultado = input.lerSimNao(mensagem);

        assertTrue(resultado, "O resultado deve ser true para a entrada 's'.");
        assertEquals(mensagem, outContent.toString(), "A mensagem de prompt deve ser exibida.");
    }

    @Test
    @DisplayName("lerSimNao: Deve retornar false para a entrada 'N' (caso comum com maiúscula)")
    void testLerSimNao_CasoComum_NaoMaiusculo() {
        provideInput("N\n"); // Simula a entrada "N"

        String mensagem = "Deseja sair? (s/n): ";
        var input = new InputManager(new Scanner(System.in));
        boolean resultado = input.lerSimNao(mensagem);

        assertFalse(resultado, "O resultado deve ser false para a entrada 'N'.");
        assertEquals(mensagem, outContent.toString(), "A mensagem de prompt deve ser exibida.");
    }

    @Test
    @DisplayName("lerSimNao: Deve exibir erro e repetir até receber entrada válida (caso de exceção)")
    void testLerSimNao_CasoExcecao_EntradaInvalida() {
        // Simula o usuário digitando "talvez", depois "sim", e finalmente "n"
        provideInput("talvez\nsim\nn\n");

        String mensagem = "Confirmar ação? (s/n): ";
        var input = new InputManager(new Scanner(System.in));
        boolean resultado = input.lerSimNao(mensagem);

        // A saída esperada em System.out é o prompt exibido 3 vezes
        String expectedOut = mensagem + mensagem + mensagem;

        // A saída esperada em System.err são duas mensagens de erro
        String lineSeparator = System.lineSeparator();
        String errorMsg = "Erro: Entrada inválida. Por favor, digite 's' para sim ou 'n' para não." + lineSeparator;
        String expectedErr = errorMsg + errorMsg;

        assertFalse(resultado, "O resultado final deve ser false, baseado na última entrada válida ('n').");
        assertEquals(expectedOut, outContent.toString(), "O prompt deve ser exibido a cada tentativa inválida.");
        assertEquals(expectedErr, errContent.toString(),
                "A mensagem de erro deve ser exibida para cada entrada inválida.");
    }
}