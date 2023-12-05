package MineSweeper.Lógica;
public class Bandeira extends Celula {
    

    public void acaoBandeira(Tabuleiro tabuleiro, int largura, int altura) {

        Celula celula = tabuleiro.getCelula(largura, altura);

        celula.CliqueDireito();
    }
}
