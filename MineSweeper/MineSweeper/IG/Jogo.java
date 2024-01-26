package MineSweeper.IG;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import MineSweeper.Lógica.C;
import MineSweeper.Lógica.Celula;
import MineSweeper.Lógica.Tabuleiro;

public class Jogo extends JFrame {

    private JButton[][] botoes;
    private Tabuleiro tabuleiro;

    public Jogo() {
        super("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        tabuleiro = new Tabuleiro();
        int numLinhas = C.NUM_LINHAS;
        int numColunas = C.NUM_COLUNAS;

        setLayout(new GridLayout(numLinhas, numColunas));
        botoes = new JButton[numLinhas][numColunas];

        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                botoes[i][j] = new JButton();
                final int linha = i;
                final int coluna = j;

                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicarCelula(linha, coluna);
                    }
                });

                add(botoes[i][j]);
            }
        }

        atualizarInterface();
    }

    private void clicarCelula(int linha, int coluna) {
        Celula celulaClicada = tabuleiro.getCelula(linha, coluna);
        
        if (celulaClicada.isMinada()) {
            JOptionPane.showMessageDialog(this, "Você perdeu! Clique em OK para reiniciar.");
            reiniciarJogo();
        } else if (!celulaClicada.isRevelada()) {
            if (celulaClicada.numMinasNosVizinhos() == 0) {
                revelarCelulasAdjacentes(linha, coluna);
            } else {
                celulaClicada.clicar(); 
            }
    
            atualizarInterface();

            if(tabuleiro.isFinalizado()){
                JOptionPane.showMessageDialog(this, "Você ganhou! Clique em OK para reiniciar.");
            reiniciarJogo();
            }
        }
    }
    
    private void revelarCelulasAdjacentes(int linha, int coluna) {
        if (linha >= 0 && linha < C.NUM_LINHAS && coluna >= 0 && coluna < C.NUM_COLUNAS) {
            Celula celulaAtual = tabuleiro.getCelula(linha, coluna);
    
            if (!celulaAtual.isRevelada() && celulaAtual.numMinasNosVizinhos() == 0) {
                celulaAtual.clicar(); 
    
                //abre os 8 casos vizinhos à celula em questão
                revelarCelulasAdjacentes(linha - 1, coluna - 1);
                revelarCelulasAdjacentes(linha - 1, coluna);
                revelarCelulasAdjacentes(linha - 1, coluna + 1);
                revelarCelulasAdjacentes(linha, coluna - 1);
                revelarCelulasAdjacentes(linha, coluna + 1);
                revelarCelulasAdjacentes(linha + 1, coluna - 1);
                revelarCelulasAdjacentes(linha + 1, coluna);
                revelarCelulasAdjacentes(linha + 1, coluna + 1);
            } else if (!celulaAtual.isRevelada() && celulaAtual.numMinasNosVizinhos() > 0) {
                celulaAtual.clicar();
            }
        }
    }

    private void atualizarInterface() {
        for (int i = 0; i < C.NUM_LINHAS; i++) {
            for (int j = 0; j < C.NUM_COLUNAS; j++) {
                Celula celulaAtual = tabuleiro.getCelula(i, j);
                JButton botaoAtual = botoes[i][j];

                if (celulaAtual.isRevelada()) {
                    botaoAtual.setEnabled(false);
                    botaoAtual.setText(celulaAtual.toString());
                } else {
                    botaoAtual.setEnabled(true);
                    botaoAtual.setText("");
                }
            }
        }
    }

    private void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
        atualizarInterface();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }
}
