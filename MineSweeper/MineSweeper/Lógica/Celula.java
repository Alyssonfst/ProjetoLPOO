package MineSweeper.Lógica;

import java.util.ArrayList;

public abstract class Celula {
    private boolean minada;
    private boolean bandeira;
    private boolean revelada;
    private boolean maluca;

    ArrayList<Celula> vizinhos;

    public Celula(boolean minada, boolean bandeira, boolean revelada, boolean maluca){
        this.minada = minada;
        this.bandeira = bandeira;
        this.revelada = revelada;
        this.maluca = maluca;

        this.vizinhos = new ArrayList<Celula>();
    }

    public boolean isMinada(){
        return minada;
    }

    public boolean isBandeira(){
        return bandeira;
    }

    public boolean isRevelada(){
        return revelada;
    }

    public boolean isMaluca() {
        return maluca;
    }

    public void adcionarVizinhos(Celula e){
        this.vizinhos.add(e);
    }

    public boolean marcarBandeira(){
        this.bandeira = !this.bandeira;
        return this.bandeira;
    }

    public int numMinasNosVizinhos(){
        int n = 0;
        for(Celula vizinho : this.vizinhos){
            if(vizinho.minada) n++;
        }
        return n;
    }

    //-1 mina
    //0 vazio
    //n minas nos vizinhos
    public int clicar(){
        this.revelada = true;
        if(this.minada){
            return -1;
        }else{
            return numMinasNosVizinhos();
        }
    }

    //método para mostrar o número de minas ao redor de uma célula
    @Override
    public String toString() {
        if (this.minada)
            return "-1";
        return "+" + this.numMinasNosVizinhos();
    }

    public void setValor(String valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValor'");
    }
}
