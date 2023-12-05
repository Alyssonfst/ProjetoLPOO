package MineSweeper.IG;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import MineSweeper.Lógica.Celula;
import MineSweeper.Lógica.Tabuleiro;


public class BotaoCelula extends JButton{
    
    private Celula celula;

    public BotaoCelula(Celula celula) {

        this.celula = celula;
    }

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }

    public void detonar() {
        
        JOptionPane.showMessageDialog(this, "Game Over");

        System.out.println("Game Over");
    
    }
}
