package MineSweeper;

public class Clique {
    
    public void processarClique(int largura, int altura) {

        Celula celulaClicada = new Celula();

        if(celulaClicada.isMina()) {

            System.out.println("Game over");
            
        } else { 
          
            System.out.println("");
            
        }
    }
}
