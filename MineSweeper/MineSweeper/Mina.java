package MineSweeper;

public class Mina extends Celula {

    @Override
    public boolean isMina() {
        
        return true;
    }

    public void detonar(Tabuleiro tabuleiro, int largura, int altura) {

        if (tabuleiro.getCelula(largura, altura).isMina()) {

            System.out.println("Game Over");
        }
    }
}