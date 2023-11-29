package MineSweeper;

import java.util.Random;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Tabuleiro extends JFrame {
    
    private Celula[][] tabuleiro;

    public Tabuleiro(int largura, int altura) {

        Random random = new Random();

        tabuleiro = new Celula[largura][altura];

        setTitle("Campo Minado");
        setLayout(new GridLayout(largura, altura));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        for (int i = 0; i < largura; i++) {
        
            for (int j = 0; j < altura; j++) {
        
                if(random.nextInt(100) < 60) {

                    tabuleiro[i][j] = new Mina();
                    add(tabuleiro[i][j]);

            } else {

                    tabuleiro[i][j] = new Vazio();
                    add(tabuleiro[i][j]);
                }
            }

        }
                setVisible(true);

    }

    public Celula getCelula(int largura, int altura) {

        if (largura >= 0 && largura < tabuleiro.length && altura >= 0 && altura < tabuleiro[0].length) {

            return tabuleiro[largura][altura];
        }

        return null;
    }

    public void tableStart() {

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