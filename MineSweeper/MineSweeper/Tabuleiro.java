package MineSweeper;

public class Tabuleiro {
    
    private Celula[][] tabuleiro;
    
    public Celula getCelula(int largura, int altura) {

        if (largura >= 0 && largura < tabuleiro.length && altura >= 0 && altura < tabuleiro[0].length) {

            return tabuleiro[largura][altura];
        }

        return null;
    }


    public Tabuleiro(int largura, int altura) {

       tabuleiro = new Celula[largura][altura];

       tableStart();

    }

    private void tableStart() {


        for(int i = 0; i < tabuleiro.length; i++){
            
            for (int j = 0; j < tabuleiro[i].length; j++){

                tabuleiro[i][j] = new Celula();
            }
        }

    }

    public int contarMinas(int largura, int altura) {

        int numMina;

        numMina = 0;

        for(int i = -1; i <= 1; i++) {
            
            for(int j = -1; j <= 1; j++) {
                
                int vizinhaçaX = largura + i;

                int vizinhaçaY = altura + j;

                if (vizinhaçaX >= 0 && vizinhaçaX < tabuleiro.length && vizinhaçaY >= 0 && vizinhaçaY < tabuleiro[0].length) {

                    if(tabuleiro[vizinhaçaX][vizinhaçaY].isMina()) {

                        numMina += 1;
                    }
                }
    
            }
        
        }

        return numMina;
    
    }
    
}
