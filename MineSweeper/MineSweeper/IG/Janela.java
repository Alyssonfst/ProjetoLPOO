package MineSweeper.IG;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MineSweeper.Lógica.C;
import MineSweeper.Lógica.Celula;
import MineSweeper.Lógica.Tabuleiro;

public class Janela extends JFrame implements JanelaInterface {

    private Botao[][] botoes;
    private Tabuleiro tabuleiro;

    public Janela() {
        super("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        tabuleiro = new Tabuleiro();
        int numLinhas = C.NUM_LINHAS;
        int numColunas = C.NUM_COLUNAS;

        setLayout(new GridLayout(numLinhas, numColunas));

        botoes = new Botao[numLinhas][numColunas];

        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                botoes[i][j] = new Botao(i, j);
                botoes[i][j].setJanelaListener(this);
                add(botoes[i][j]);
            }
        }
    }

    @Override
    public void clicarCelula(int linha, int coluna) {
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


    @Override
    public void marcarBandeira(int linha, int coluna) {
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

    @Override
    public void revelarCelulasAdjacentes(int linha, int coluna) {
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

    @Override
    public void atualizarInterface() {
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
    @Override
    public void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
        atualizarInterface();
    }
}
