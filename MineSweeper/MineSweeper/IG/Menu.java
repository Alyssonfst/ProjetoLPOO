package MineSweeper.IG;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import MineSweeper.Lógica.C;

public class Menu extends JFrame implements MenuInterface {


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


        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(250, 200, 100, 30);
        sairButton.addActionListener(e -> sair());
        add(sairButton);

        JButton confButton = new JButton();
        confButton.setBounds(250, 150, 100, 30);
        confButton.setText("Configurações");
        confButton.addActionListener(e -> configuracoes());
        add(confButton);
    }

    //Inicialização do jogo, segundo a dificuldade

    @Override
    public void iniciar() {

        try{

            if(C.NUM_BOMBAS == 0) {

                throw new ExcecaoDificuldade("Não há bombas para iniciar o jogo. ");
            }

            this.dispose();
    
    
            SwingUtilities.invokeLater(new Runnable() {
                
                @Override
                public void run() {
    
                    
                    Jogo jogo = new Jogo();
                    
                    jogo.run();
                    
                }
            });
    
        
        } catch(ExcecaoDificuldade e) {
                
            System.err.println("Erro para inicializar: " + e.getMessage() + "Selecione uma dificuldade.");

            retornarAoMenu();
        }
    } 

    //Saída do jogo

    @Override
    public void sair() {
        System.exit(0);
    }

    @Override
    public void configuracoes() {

        this.dispose();
        
        Configuracoes conf = new Configuracoes();
        conf.setMenu(this);
        conf.setVisible(true);

    }

    //Voltar ao menu

    public void retornarAoMenu() {
        
        this.setVisible(true);

    }

    
    //Runner do jogo

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
