package MineSweeper;

public class Vazio extends Celula {
    private boolean isMina;

    public Vazio() {
        this.isMina = false;
    }

    public boolean isMina() {
        return isMina;
    }
}
