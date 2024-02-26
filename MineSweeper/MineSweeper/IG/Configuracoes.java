package MineSweeper.IG;

import javax.swing.JButton;
import javax.swing.JFrame;

import MineSweeper.Lógica.C;

public class Configuracoes extends JFrame{
    
    private static final String[] dificuldades = {"Fácil", "Médio", "Difícil"};
    private int dificuldadeAtualIndex = 0;
    private JButton dificuldadeButton;
    private Menu menu;
    private boolean modoMaluco = false;
    private JButton malucoButton;
    private String modoAtual;
    private int numBandeiras;


    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getNumBandeiras() {
        return numBandeiras;
    }

    public void setNumBandeiras(int numBandeiras) {
        this.numBandeiras = numBandeiras;
    }
    
    public Configuracoes() {
    
        
    //Janela

    super("Campo Minado - Configurações");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);

    setLayout(null);
    
    dificuldadeButton = new JButton("Dificuldade: " + dificuldades[dificuldadeAtualIndex]);
    dificuldadeButton.setBounds(225, 100, 150, 30);
    dificuldadeButton.addActionListener(e -> mudarDificuldade());
    add(dificuldadeButton);

    JButton retornarButton = new JButton("Retornar");
    retornarButton.setBounds(225, 200, 150, 30);
    retornarButton.addActionListener(e -> retornar());
    add(retornarButton);

    malucoButton = new JButton("Modo normal");
    malucoButton.setBounds(225, 150, 150, 30);
    malucoButton.addActionListener(e -> mudarMaluquice());
    add(malucoButton);

    dificuldade();
    
}

    //Voltar ao menu

    public void retornar() {

        this.setVisible(false);
        menu.retornarAoMenu();
    }
    

    //Ação de mudar dificuldade
    private void mudarDificuldade() {
        
        dificuldadeAtualIndex = (dificuldadeAtualIndex + 1) % dificuldades.length;
        dificuldadeButton.setText("Dificuldade: " + dificuldades[dificuldadeAtualIndex]);
        dificuldade();
    }

    private void modoDeJogo() {

        if(modoMaluco == false) {

            modoAtual = "Normal";
        
        } else {

            modoAtual = "Maluco";
        }

        malucoButton.setText("Modo " + modoAtual);
    }

    private void mudarMaluquice() {

        modoMaluco = !modoMaluco;
        
        modoDeJogo();
        dificuldade();
    }
        
    public boolean getModoMaluco() {
        return modoMaluco;
    }

    //Dificuldade atrelada ao numero de bombas, linhas e colunas

    int dificuldade() {

        if(dificuldadeAtualIndex == 0) {

            if(modoMaluco == true) {

                C.NUM_BOMBA_MALUCA = 10;
            }

            C.NUM_BOMBAS = 15 - C.NUM_BOMBA_MALUCA;
            C.NUM_COLUNAS = 10;
            C.NUM_LINHAS = 10;
            numBandeiras = 15;

        } else if(dificuldadeAtualIndex == 1) {
            
            if(modoMaluco == true) {

                C.NUM_BOMBA_MALUCA = 20;
            }

            C.NUM_BOMBAS = 30 - C.NUM_BOMBA_MALUCA;
            C.NUM_COLUNAS = 20;
            C.NUM_LINHAS = 10;
            numBandeiras = 30;

        } else {

            if(modoMaluco == true) {

                C.NUM_BOMBA_MALUCA = 70;
            }

            C.NUM_BOMBAS = 70 - C.NUM_BOMBA_MALUCA;
            C.NUM_COLUNAS = 20;
            C.NUM_LINHAS = 20;
            numBandeiras = 70;
            
            
        }

        return dificuldadeAtualIndex;

    }


}