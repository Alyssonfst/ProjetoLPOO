package MineSweeper;

public class Clique {
    
    public void processarClique(Celula celulaClicada, int largura, int altura) {

        if(celulaClicada.isMina()) {

            System.out.println("Game over");
            
        } else { 
          
            System.out.println("");
            
        }
    }
}
