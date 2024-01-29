package MineSweeper.IG;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

                botoes[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked (MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON1)
                            clicarCelula(linha, coluna);
                        if(e.getButton() == MouseEvent.BUTTON3){
                            marcarBandeira(linha, coluna);
                        }
                    }
                });

                add(botoes[i][j]);
            }
        }
    }

    private void clicarCelula(int linha, int coluna) {
        Celula celula = tabuleiro.getCelula(linha, coluna);
        
        if(celula.isBandeira()){
            return;
        }

        if (celula.isMinada()) {
            JOptionPane.showMessageDialog(this, "Você perdeu! Clique em OK para reiniciar.");
            reiniciarJogo();
        } else if (!celula.isRevelada()) {
            if (celula.numMinasNosVizinhos() == 0) {
                revelarCelulasAdjacentes(linha, coluna);
            } else {
                celula.clicar(); 
            }
    
            atualizarInterface();

            if(tabuleiro.isFinalizado()){
                JOptionPane.showMessageDialog(this, "Você ganhou! Clique em OK para reiniciar.");
            reiniciarJogo();
            }
        }
    }

    private void marcarBandeira(int linha, int coluna){
        Celula celula = tabuleiro.getCelula(linha, coluna);

        if(!celula.isRevelada()){
            if(celula.isBandeira()){
                celula.marcarBandeira();

                JButton botaoMarcado = botoes[linha][coluna];
                botaoMarcado.setEnabled(true);
                botaoMarcado.setText("");

            } else{
                celula.marcarBandeira();
                JButton botaoMarcado = botoes[linha][coluna];
                botaoMarcado.setEnabled(true);
                botaoMarcado.setText("B");
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
                    if (celulaAtual.isBandeira()) {
                        botaoAtual.setText("B");
                    } else {
                        botaoAtual.setText("");
                    }
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
