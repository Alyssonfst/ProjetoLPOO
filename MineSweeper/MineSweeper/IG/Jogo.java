package MineSweeper.IG;

import javax.swing.SwingUtilities;

public class Jogo {

    private static Janela janela;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                janela = new Janela();
                janela.setVisible(true);
            }
        });
    }
}
