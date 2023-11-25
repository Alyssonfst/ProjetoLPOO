package MineSweeper;

public class VizinhoMina extends Celula {
    
    public int getNumeroMinasVizinhas(Tabuleiro tabuleiro, int largura, int altura) {

        return tabuleiro.contarMinas(largura, altura);
    }
}
