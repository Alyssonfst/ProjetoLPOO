package MineSweeper.Lógica;
import java.util.Random;

public class Tabuleiro {

    private Celula[][] tabuleiro;
    Random rand = new Random();
    

    public Tabuleiro(){
        tabuleiro = new Celula[C.NUM_LINHAS][C.NUM_COLUNAS];
        
        for (int bombasGeradas = 0; bombasGeradas < C.NUM_BOMBAS; bombasGeradas++) {
            int linha = rand.nextInt(C.NUM_LINHAS);
            int coluna = rand.nextInt(C.NUM_COLUNAS);
            while(tabuleiro[linha][coluna] instanceof CelulaMinada){
                linha = rand.nextInt(C.NUM_LINHAS);
                coluna = rand.nextInt(C.NUM_COLUNAS);
            }
            tabuleiro[linha][coluna] = new CelulaMinada();
        }
        for (int i = 0; i < C.NUM_LINHAS; i++) {
            for (int j = 0; j < C.NUM_COLUNAS; j++) {
                if(tabuleiro[i][j] == null){
                    tabuleiro[i][j] = new CelulaVazia();
                }
            }
        }
    
        // Segundo loop para adicionar vizinhos
        for (int i = 0; i < C.NUM_LINHAS; i++) {
            for (int j = 0; j < C.NUM_COLUNAS; j++) {
                 if (i > 0) {
                    if (j > 0) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i - 1][j - 1]);
                    tabuleiro[i][j].adcionarVizinhos(tabuleiro[i - 1][j]);
                    if (j < C.NUM_COLUNAS - 1) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i - 1][j + 1]);
                }
                if (j > 0) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i][j - 1]);
                if (j < C.NUM_COLUNAS - 1) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i][j + 1]);
    
                if (i < C.NUM_LINHAS - 1) {
                    if (j > 0) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i + 1][j - 1]);
                    tabuleiro[i][j].adcionarVizinhos(tabuleiro[i + 1][j]);
                    if (j < C.NUM_COLUNAS - 1) tabuleiro[i][j].adcionarVizinhos(tabuleiro[i + 1][j + 1]);
                }
            }
        }
    }
    public int clicar(int linha, int coluna){
        return tabuleiro[linha][coluna].clicar();
    }

    public Celula getCelula(int linha, int coluna){
        return tabuleiro[linha][coluna];
    }


    //método para visualizar a criação do campo em forma de matriz
    @Override
    public String toString() {
        String str = " ";

        for(int i = 0; i < C.NUM_LINHAS; i++){
            for(int j = 0; j < C.NUM_COLUNAS; j++){
                str += tabuleiro[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }


}
