package MineSweeper.IG;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MineSweeper.Lógica.Tabuleiro;

import java.awt.GridLayout;

public class Janela extends JFrame {

    private final int largura_tab = 20;
    private final int altura_tab = 20;

    public Janela() {

        super("Campo Minado");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 600);
        
        JPanel painel = new JPanel(new GridLayout(largura_tab, altura_tab));

        Tabuleiro tabuleiro = new Tabuleiro(largura_tab, altura_tab);


        for (int i = 0; i < largura_tab; i++) {
        
            for (int j = 0; j < altura_tab; j++) {
        
                BotaoCelula botao = new BotaoCelula(tabuleiro.getCelula(i, j));
                botao.addActionListener(e -> revelar(botao, tabuleiro));

                painel.add(botao);
            }
        }

        add(painel);

        setLocationRelativeTo(null);

        setVisible(true);
    }
    
    public void revelar(BotaoCelula botao, Tabuleiro tabuleiro) {

        if(botao.getCelula().isMina()) {

            botao.detonar();

            tabuleiro.tableRestart(largura_tab, altura_tab);

        } else {

        }
    }

}

