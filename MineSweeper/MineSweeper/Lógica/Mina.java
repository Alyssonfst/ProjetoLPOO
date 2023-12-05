package MineSweeper.Lógica;


public class Mina extends Celula {
    private boolean isMina;

    public Mina() {
        this.isMina = true;
    }

    public boolean isMina() {
        return isMina;
    }

    public void detonar() {

        Mina mina = new Mina();
    }
}