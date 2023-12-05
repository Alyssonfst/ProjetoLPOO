package MineSweeper.Lógica;

import java.util.Random;


public class Tabuleiro {
    
    private Celula[][] tabuleiro;

    public Tabuleiro(int largura, int altura) {

        Random random = new Random();

        tabuleiro = new Celula[largura][altura];

        for (int i = 0; i < largura; i++) {
        
            for (int j = 0; j < altura; j++) {
        
                if(random.nextInt(100) < 40) {

                    tabuleiro[i][j] = new Mina();

            } else {

                    tabuleiro[i][j] = new Vazio();
                }
            }

        }

    }

    public Celula getCelula(int largura, int altura) {

        if (largura >= 0 && largura < tabuleiro.length && altura >= 0 && altura < tabuleiro[0].length) {

            return tabuleiro[largura][altura];
        }

        return null;
    }

    public void tableRestart(int largura, int altura) {

        new Tabuleiro(largura, altura);

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