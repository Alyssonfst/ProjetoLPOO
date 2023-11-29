package MineSweeper;

import javax.swing.JButton;

public class Celula  extends JButton{

    private boolean isMina;

    private boolean isVizinhoMina;

    private boolean Bandeira;

    public boolean temBandeira() {

        return Bandeira;
    }


    public boolean mudarBandeira() {

        Bandeira = !Bandeira;

        return Bandeira;
    }


    public void CliqueDireito() {

        mudarBandeira();
    }

    public boolean isMina() {

        return isMina;
    }

    public void setMine(boolean mina) {

        this.isMina = mina;
    }

    public boolean isVizinhoMina() {

        return isVizinhoMina;
    }

    public void setVizinhoMina(boolean vizinhoMina) {
        
        this.isVizinhoMina = vizinhoMina;
    }

    public void revelarCelula() {


    }


}
