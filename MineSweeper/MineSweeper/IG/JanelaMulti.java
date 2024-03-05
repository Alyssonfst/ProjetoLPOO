package MineSweeper.IG;

import javax.swing.JOptionPane;

import MineSweeper.Lógica.C;
import MineSweeper.Lógica.Celula;

public class JanelaMulti extends JanelaSingle {
    
    private boolean jogador1 = true;
    private boolean jogador2 = false;
    private int pontos1 = 0;
    private int pontos2 = 0;
    private Configuracoes conf;
    private Menu menu;
    private int linhaClicada;
    private int colunaClicada;
    private int celulasReveladas1 = 0;
    private int celulasReveladas2 = 0;
    private int celulasReveladas = celulasReveladas1 + celulasReveladas2;
    private int minasReveladas = 0;
    private int minasTotais = C.NUM_BOMBAS;


    public JanelaMulti(long tempoInicial, Configuracoes conf) {

        super(tempoInicial, conf);
        jogadorAtual();

    }

    public void mudarJogador() {

        jogador1 = !jogador1;
        jogador2 = !jogador2;

        jogadorAtual();
        
    }

    public void jogadorAtual() {

        if(jogador1 == true) {

            setTitle("Jogador 1");
        
        } else {

            setTitle("Jogador 2");
        }
    }

    public int pontoMinas(int pontuacao) {

        pontuacao -= 100;

        return pontuacao;
    }
    
    @Override
    public void verificarFinalizado() {
        
        Celula celula = getTabuleiro().getCelula(linhaClicada, colunaClicada);

        for(int i = 0; i < C.NUM_LINHAS; i++) {

            for(int j = 0; j < C.NUM_COLUNAS; j++) {

                if(celula.isMinada() && celula.isRevelada()) {

                    minasReveladas++;
                }
            }
        }


        if(getTabuleiro().isFinalizado() || celulasReveladas == C.NUM_COLUNAS * C.NUM_LINHAS || minasTotais == minasReveladas) {

            JOptionPane.showMessageDialog(this, "Pontuação do Jogador 1: " + pontos1);
            JOptionPane.showMessageDialog(this, "Pontos do jogador 2: " + pontos2);
            
            int escolha = JOptionPane.showConfirmDialog(
                this,
                "Deseja reiniciar o jogo?",
                "Reiniciar",
                JOptionPane.YES_NO_OPTION);

                if(escolha == JOptionPane.YES_OPTION) {

                    reiniciarJogo();

                } else {

                    this.dispose();

                    if(menu == null) {

                        menu = new Menu();
                        
                    }

                    if(conf == null) {

                        conf = new Configuracoes();
                    }

                    menu.setVisible(true);
                }

        }
    }
    
    @Override
    public void clicarCelula(int linha, int coluna) {
        linhaClicada = linha;
        colunaClicada = coluna;
        Celula celula = getTabuleiro().getCelula(linha, coluna);

        if (jogador1) {

            celula = getTabuleiro().getCelula(linha, coluna);

            if (!celula.isRevelada() && !celula.isMinada()) {

                celulasReveladas1++;
                pontos1 = pontuacaoPorRevelarVazio(pontos1);
            }

        } else if (jogador2) {

            celula = getTabuleiro().getCelula(linha, coluna);

            if (!celula.isRevelada() && !celula.isMinada()) {

                celulasReveladas2++;
                pontos2 = pontuacaoPorRevelarVazio(pontos2);
            }
        }

        if(celula.isRevelada()) {
            return;
        }

        super.clicarCelula(linha, coluna);
        mudarJogador();
        verificarFinalizado();
        System.out.println(celulasReveladas1);
        System.out.println(celulasReveladas2);
    }

     @Override
     public void detonar() {
        
        Celula celulaAtual = getTabuleiro().getCelula(linhaClicada, colunaClicada);

        if(celulaAtual.isMinada()) {

            minasReveladas++;
            minasReveladas = minasReveladas - 100;
            if(jogador1) {

                pontos1 += pontoMinas(0);
                celulasReveladas1++;
                
            }
    
            if(jogador2) {
    
                pontos2 += pontoMinas(0);
                celulasReveladas2++;
            }
        
            
        }
        celulaAtual.setRevelada(true);
        atualizarInterface();
        
    }

    @Override
    public void revelarCelulasAdjacentes(int linha, int coluna) {

        super.revelarCelulasAdjacentes(linha, coluna);
        
        }
    

    @Override
    public void reiniciarJogo() {

        pontos1 = 0;
        pontos2 = 0;
        minasReveladas = 0;
        celulasReveladas1 = 0;
        celulasReveladas2 = 0;
        super.reiniciarJogo();
    }

    public int pontuacaoPorRevelarVazio(int pontos) {

            return pontos + 100; 
        
    }

}
