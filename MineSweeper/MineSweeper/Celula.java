package MineSweeper;

public class Celula {

    private boolean isMina;

    private int isVizinhoMina;


    public boolean isMina() {

        return isMina;
    }

    public void setMine(boolean mina) {

        this.isMina = mina;
    }

    public int isVizinhoMina() {

        return isVizinhoMina;
    }

    public void setVizinhoMina(int numMinas) {
        
        this.isVizinhoMina = numMinas;
    }

}
