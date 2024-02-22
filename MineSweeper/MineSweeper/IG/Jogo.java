package MineSweeper.IG;

public class Jogo {

    private Janela janela;

    public Jogo(long tempoInicial, Configuracoes conf) {
        this.janela = new Janela(tempoInicial, conf);
    }

    public void run() {
        janela.setVisible(true);
    }
}
