package MineSweeper.Lógica;

import java.util.ArrayList;

public abstract class Celula {
    private boolean minada;
    private boolean bandeira;
    private boolean revelada;
    private boolean clicada;

    ArrayList<Celula> vizinhos;

    public Celula(boolean minada, boolean bandeira, boolean revelada, boolean clicada){
        this.minada = minada;
        this.bandeira = bandeira;
        this.revelada = revelada;
        this.clicada = clicada;

        this.vizinhos = new ArrayList<Celula>();
    }

    public boolean isRevelada(){
        return revelada;
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
        this.clicada = true;
        this.revelada = true;
        if(this.minada){
            return -1;
        }else{
            return numMinasNosVizinhos();
        }
    }

    //método para 
    @Override
    public String toString() {
        if (this.minada)
            return "-1";
        return "+" + this.numMinasNosVizinhos();
    }
}
