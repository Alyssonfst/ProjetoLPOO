package MineSweeper.IG;

public interface JanelaInterface {
    void clicarCelula(int linha, int coluna);
    void marcarBandeira(int linha, int coluna);
    void revelarCelulasAdjacentes(int linha, int coluna);
    void atualizarInterface();
    void reiniciarJogo();
}
