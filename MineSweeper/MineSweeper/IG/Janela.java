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
    private long tempoFinal;
    private long tempoInicial;
    private Configuracoes conf;
    private long pontuacao;
    private Menu menu;
    private boolean primeiroClique = true;

    //Janela do campo minado
    public Janela(long tempoInicial, Configuracoes conf) {
        super("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        this.conf = conf;
        this.tempoInicial = tempoInicial;

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


    //Ações que ocorrerão ao clicar na célula (esquerda)
    @Override
    public void clicarCelula(int linha, int coluna) {
        Celula celula = tabuleiro.getCelula(linha, coluna);
        
        if(celula.isBandeira()){
            return;
        }

        if(celula.isMinada() && primeiroClique) {

            celula.setMinada(false);
            primeiroClique = false;
            clicarCelula(linha, coluna);
        }
        if (celula.isMinada()) {
                        
            int escolha = JOptionPane.showConfirmDialog(

                this,
                "Você perdeu! Gostaria de reiniciar o jogo?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
                );

            if(escolha == JOptionPane.YES_OPTION) {
            reiniciarJogo();
            } else {

                this.setVisible(false);

                if(menu == null) {

                    menu = new Menu();
                }

                menu.setVisible(true);
            }

        
        } else if (!celula.isRevelada()) {

            if(primeiroClique) {
                
                revelarCelulasAdjacentes(linha, coluna);
                primeiroClique = false;
            }
            if (celula.numMinasNosVizinhos() == 0) {
                revelarCelulasAdjacentes(linha, coluna);
            } else {
                celula.clicar(); 
            }
    
            atualizarInterface();

            if(tabuleiro.isFinalizado()){

                tempoFinal = System.currentTimeMillis()/1000;

                if(conf.dificuldade() == 0){
                    
                    pontuacao = 1000 - (tempoFinal - tempoInicial)*10;
                    
                
                } else if(conf.dificuldade() == 1) {

                    pontuacao = 125/10 *(1000 -  (tempoFinal - tempoInicial));
                } else if(conf.dificuldade() == 2) {

                    pontuacao = (1000 - (tempoFinal - tempoInicial)) * 15;
                }

                JOptionPane.showMessageDialog(this, "Você ganhou! Sua pontuação: " + pontuacao);
                reiniciarJogo();
            }
        }
    }

    //Marcação com o botão direito

    @Override
    public void marcarBandeira(int linha, int coluna) {
        Celula celula = tabuleiro.getCelula(linha, coluna);

        int bandeiras = conf.getNumBandeiras();

        if(bandeiras == 0) {

        if(!celula.isMaluca() && celula.isBandeira()) {

            celula.marcarBandeira();

            conf.setNumBandeiras(bandeiras+1);

            JButton botaoMarcado = botoes[linha][coluna];
            botaoMarcado.setEnabled(true);
            botaoMarcado.setText("");
        }
    
   } else {

         if(!celula.isRevelada()){
            
            if(!celula.isBandeira() && celula.isMaluca()) {
                    
                System.out.println("celula maluca");
                celula.marcarBandeira();

                conf.setNumBandeiras(bandeiras-1);

                JButton botaoMarcado = botoes[linha][coluna];
                botaoMarcado.setEnabled(true);
                botaoMarcado.setText("B");

                tabuleiro.mudarMinas();

                atualizarInterface();
                

        } else if(!celula.isMaluca() && !celula.isBandeira()) {

            System.out.println("celula misteriosa");
            celula.marcarBandeira();

            conf.setNumBandeiras(bandeiras-1);


            JButton botaoMarcado = botoes[linha][coluna];

            botaoMarcado.setEnabled(true);

            botaoMarcado.setText("B");


        } else if(!celula.isMaluca() && celula.isBandeira()) {

                
            celula.marcarBandeira();

            conf.setNumBandeiras(bandeiras+1);


            JButton botaoMarcado = botoes[linha][coluna];

            botaoMarcado.setEnabled(true);

            botaoMarcado.setText("");

        }

    }

}

}

    
    //Revelando as células após o clique


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


    //Atualização da interface, sempre que necessario
    
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

    

    


    //Reiniciar

    @Override
    public void reiniciarJogo() {
        primeiroClique = true;
        tabuleiro = new Tabuleiro();
        tempoInicial = System.currentTimeMillis()/1000;
        atualizarInterface();
    }
}    