import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Vue extends JFrame {

    private Model model;

    private JMenuBar barreMenu;
    private JMenu options;
    private JMenuItem nouvellePartie;
    private JMenuItem bestScores;

    private JToggleButton[][] listeBoutton;
    private JProgressBar progressBar;
    private JLabel level;
    private JLabel tries;
    private JLabel score;
    //private Icon[] = new Icon[size];
    private Icon icone1;
    private Icon icone2;
    private Icon icone3;
    private Icon icone4;
    private Icon icone5;
    private Icon icone6;
    private Icon icone7;
    private Icon icone8;
    private URL urlImage1 = getClass().getResource("images/378303.jpeg");
    private URL urlImage2 = getClass().getResource("images/378323.jpeg");
    private URL urlImage3 = getClass().getResource("images/378343.jpeg");
    private URL urlImage4 = getClass().getResource("images/378363.jpeg");
    private URL urlImage5 = getClass().getResource("images/379593.jpeg");
    private URL urlImage6 = getClass().getResource("images/526993.jpeg");
    private URL urlImage7 = getClass().getResource("images/527013.jpeg");
    private URL urlImage8 = getClass().getResource("images/563493.jpeg");

    private JPanel gamezone;



    public Vue(Model model){
        this.model = model;
        while (!model.testGrille(model.getTabColor())){
            model.remplirTabColor();
        }
        initAttribut();
        ajouterWidget();
        setTitle("Tucjeweled");
        setSize(800, 800);
        actualiser();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void setControlButton(ActionListener actionListener){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                listeBoutton[i][j].addActionListener(actionListener);
            }

        }
    }

    public void initAttribut(){
        listeBoutton = new JToggleButton[8][8];
        gamezone = new JPanel(new GridLayout(8,8));
        level = new JLabel("Level : " + model.getLevel());
        tries = new JLabel("Tries : " + model.getTries());
        score = new JLabel("Score : " + model.getScore());
        progressBar = new JProgressBar(0,100);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                listeBoutton[i][j] = new JToggleButton();
                listeBoutton[i][j].setActionCommand(""+i+j);
                gamezone.add(listeBoutton[i][j]);
            }
        }
        icone1 = new ImageIcon(urlImage1);
        icone2 = new ImageIcon(urlImage2);
        icone3 = new ImageIcon(urlImage3);
        icone4 = new ImageIcon(urlImage4);
        icone5 = new ImageIcon(urlImage5);
        icone6 = new ImageIcon(urlImage6);
        icone7 = new ImageIcon(urlImage7);
        icone8 = new ImageIcon(urlImage8);

    }

    public void ajouterWidget(){
        // création de tous les composants graphiques de la fenêtre
        JPanel board = new JPanel();
        JPanel test1 = new JPanel();
        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.X_AXIS));

        barreMenu = new JMenuBar();
        options = new JMenu("Options");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        bestScores = new JMenuItem("Meilleurs Scores");

        infos.add(Box.createHorizontalGlue());
        infos.add(level);
        infos.add(Box.createHorizontalGlue());
        infos.add(tries);
        infos.add(Box.createHorizontalGlue());
        infos.add(score);
        infos.add(Box.createHorizontalGlue());

        options.add(nouvellePartie);
        options.addSeparator();
        options.add(bestScores);

        test1.add(progressBar);
        test1.setLayout(new BoxLayout(test1, BoxLayout.LINE_AXIS));

        barreMenu.add(options);
        board.add(infos);
        board.add(gamezone);
        board.add(test1);
        board.setLayout(new BoxLayout(board, BoxLayout.PAGE_AXIS));

        setJMenuBar(barreMenu);
        setContentPane(board);
    }

    public void actualiser(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch(model.getTabColor()[i][j]){
                    case 1:
                        listeBoutton[i][j].setIcon(icone1);
                        break;
                    case 2:
                        listeBoutton[i][j].setIcon(icone2);
                        break;
                    case 3:
                        listeBoutton[i][j].setIcon(icone3);
                        break;
                    case 4:
                        listeBoutton[i][j].setIcon(icone4);
                        break;
                    case 5:
                        listeBoutton[i][j].setIcon(icone5);
                        break;
                    case 6:
                        listeBoutton[i][j].setIcon(icone6);
                        break;
                    case 7:
                        listeBoutton[i][j].setIcon(icone7);
                        break;
                    case 8:
                        listeBoutton[i][j].setIcon(icone8);
                        break;
                }
            }
        }
    }

    public JToggleButton[][] getListeBoutton() {
        return listeBoutton;
    }
}