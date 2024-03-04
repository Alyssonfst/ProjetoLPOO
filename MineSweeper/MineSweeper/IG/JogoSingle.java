package MineSweeper.IG;

public class JogoSingle {

    private JanelaSingle janela;

    public JogoSingle(long tempoInicial, Configuracoes conf) {
        this.janela = new JanelaSingle(tempoInicial, conf);
    }

    public void run() {
        janela.setVisible(true);
    }
}
