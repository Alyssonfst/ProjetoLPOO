package MineSweeper.IG;

import MineSweeper.Lógica.C;
import MineSweeper.Lógica.Celula;

public class JanelaMulti extends JanelaSingle {
    
    private boolean jogador1 = true;
    private boolean jogador2 = false;
    private int pontos1 = 0;
    private int pontos2 = 0;
    private Configuracoes conf;
    private int linhaClicada;
    private int colunaClicada;
    private int celulasReveladas = 0;

    public JanelaMulti(long tempoInicial, Configuracoes conf) {

        super(tempoInicial, conf);
        System.out.println("multiplayer on");
        jogadorAtual();

    }

    public void mudarJogador() {

        jogador1 = !jogador1;
        jogador2 = !jogador2;

        jogadorAtual();
        
    }

    public void jogadorAtual() {

        if(jogador1 == true) {

            System.out.println("jogador 1");
            setTitle("Jogador 1");
        
        } else {

            System.out.println("jogador 2");
            setTitle("Jogador 2");
        }
    }

    public int pontoMinas(int pontuacao) {

        pontuacao -= 10;

        return pontuacao;
    }
    
    @Override
    public void verificarFinalizado() {
        
        if(getTabuleiro().isFinalizado() || celulasReveladas == C.NUM_COLUNAS * C.NUM_LINHAS) {

            super.verificarFinalizado();
        }
    }
    
    @Override
    public void clicarCelula(int linha, int coluna) {
   
        linhaClicada = linha;
        colunaClicada = coluna;
        super.clicarCelula(linha, coluna);     
        mudarJogador();
    }

     @Override
     public void detonar() {
        
        Celula celulaAtual = getTabuleiro().getCelula(linhaClicada, colunaClicada);

        if(jogador1) {

            pontoMinas(pontos1);
            
        }

        if(jogador2) {

            pontoMinas(pontos2);
        }
        
        celulasReveladas++;
        celulaAtual.setRevelada(true);
        atualizarInterface();
        
    }

    @Override
    public void revelarCelulasAdjacentes(int linha, int coluna) {

        super.revelarCelulasAdjacentes(linha, coluna);
        celulasReveladas++;
        System.out.println(celulasReveladas);
    }
    


}
