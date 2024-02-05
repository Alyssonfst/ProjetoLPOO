package MineSweeper.IG;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Botao extends JButton {

    private int linha;
    private int coluna;
    private JanelaInterface janelaInterface;

    public Botao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    janelaInterface.clicarCelula(linha, coluna);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    janelaInterface.marcarBandeira(linha, coluna);
                }
            }
        });
    }

    public void setJanelaListener(JanelaInterface interfaces) {
        this.janelaInterface = interfaces;
    }
}

