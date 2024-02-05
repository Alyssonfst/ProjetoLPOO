package MineSweeper.IG;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import MineSweeper.Lógica.C;

public class Menu extends JFrame implements MenuInterface {

    private static final String[] dificuldades = {"Fácil", "Médio", "Difícil"};
    private int dificuldadeAtualIndex = 0;

    private JButton dificuldadeButton;

    public Menu() {

        //Janela do Menu

        super("Campo Minado - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        setLayout(null);

        JButton jogarButton = new JButton("Jogar");
        jogarButton.setBounds(250, 100, 100, 30);
        jogarButton.addActionListener(e -> iniciar());
        add(jogarButton);

        dificuldadeButton = new JButton("Dificuldade: " + dificuldades[dificuldadeAtualIndex]);
        dificuldadeButton.setBounds(250, 150, 150, 30);
        dificuldadeButton.addActionListener(e -> mudarDificuldade());
        add(dificuldadeButton);

        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(250, 200, 100, 30);
        sairButton.addActionListener(e -> sair());
        add(sairButton);
    }

    //Ação de mudar dificuldade
    private void mudarDificuldade() {
        dificuldadeAtualIndex = (dificuldadeAtualIndex + 1) % dificuldades.length;
        dificuldadeButton.setText("Dificuldade: " + dificuldades[dificuldadeAtualIndex]);
    }

    //Dificuldade atrelada ao numero de bombas, linhas e colunas

    @Override
    public int dificuldade() {

        if(dificuldadeAtualIndex == 0) {

            C.NUM_BOMBAS = 20;
            C.NUM_COLUNAS = 10;
            C.NUM_LINHAS = 10;
        
        } else if(dificuldadeAtualIndex == 1) {

            C.NUM_BOMBAS = 50;
            C.NUM_COLUNAS = 15;
            C.NUM_LINHAS = 10;

        } else {

            C.NUM_BOMBAS = 100;
            C.NUM_COLUNAS = 20;
            C.NUM_LINHAS = 10;
        }

        return dificuldadeAtualIndex; 
    }

    //Inicialização do jogo, segundo a dificuldade

    @Override
    public void iniciar() {

        dificuldade();

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                Jogo jogo = new Jogo();
                
                jogo.run();
                
            }
        });
    }

    //Saída do jogo

    @Override
    public void sair() {
        System.exit(0);
    }

    //Runner do jogo

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
