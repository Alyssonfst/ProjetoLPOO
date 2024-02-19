package MineSweeper.Lógica;
import java.util.Random;

public class Tabuleiro {

    private Celula[][] tabuleiro;
    Random rand = new Random();
    

    public Tabuleiro(){
        tabuleiro = new Celula[C.NUM_LINHAS][C.NUM_COLUNAS];
        
        //Primeiro gera aleatoriamente celulasMinadas através do loop com a quantidade pré-definida
        for (int bombasGeradas = 0; bombasGeradas < C.NUM_BOMBAS + C.NUM_BOMBA_MALUCA; bombasGeradas++) {
            int linha = rand.nextInt(C.NUM_LINHAS);
            int coluna = rand.nextInt(C.NUM_COLUNAS);
            while(tabuleiro[linha][coluna] instanceof CelulaMinada){//garante que não seja gerado uma celulaMinada no mesmo espaço
                linha = rand.nextInt(C.NUM_LINHAS);
                coluna = rand.nextInt(C.NUM_COLUNAS);
            }
            tabuleiro[linha][coluna] = new CelulaMinada();
        }
        for (int i = 0; i < C.NUM_LINHAS; i++) {
            for (int j = 0; j < C.NUM_COLUNAS; j++) {
                if(tabuleiro[i][j] == null){//Ao sair do primeiro loop, preenche os espaços null com celulasVazias
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
    
    public Celula getCelula(int linha, int coluna){
        return tabuleiro[linha][coluna];
    }

    public boolean isFinalizado() {
        for (int i = 0; i < C.NUM_LINHAS; i++) {
            for (int j = 0; j < C.NUM_COLUNAS; j++) {
                Celula celulaAtual = tabuleiro[i][j];

                // Se encontrar uma célula vazia não revelada, o jogo ainda não está finalizado
                if (!celulaAtual.isRevelada() && !celulaAtual.isMinada()) {
                    return false;
                }
            }
        }
        // Se percorrer todas as células e não encontrar uma vazia não revelada, o jogo está finalizado
        return true;
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
