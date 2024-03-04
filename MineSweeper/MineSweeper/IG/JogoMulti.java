package MineSweeper.IG;

public class JogoMulti extends JogoSingle {
    
    private JanelaMulti janela;
    
    public JogoMulti(long tempoInicial, Configuracoes conf) {

        super(tempoInicial, conf);
        this.janela = new JanelaMulti(tempoInicial, conf);
    }

    @Override
    public void run() {
        
        janela.setVisible(true);
    }
}
