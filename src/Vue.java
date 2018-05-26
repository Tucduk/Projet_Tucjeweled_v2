import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class Vue extends JFrame {

    private Model model;

    private JMenuBar barreMenu;
    private JMenu options;
    private JMenuItem quitter;
    private JMenuItem nouvellePartie;
    private JMenuItem bestScores;
    private JMenuItem pause;

    private JToggleButton[][] listeBoutton;
    private JProgressBar progressBar;
    private JLabel level;
    private JLabel tries;
    private JLabel score;
    private Icon icone1;
    private Icon icone2;
    private Icon icone3;
    private Icon icone4;
    private Icon icone5;
    private Icon icone6;
    private Icon icone7;
    private Icon icone8;
    private Icon icone9;
    private URL urlImage1 = getClass().getResource("images/tuccrispy.jpeg");
    private URL urlImage2 = getClass().getResource("images/378323.jpeg");
    private URL urlImage3 = getClass().getResource("images/imagetucamericain.jpeg");
    private URL urlImage4 = getClass().getResource("images/americain2.jpg");
    private URL urlImage5 = getClass().getResource("images/379593.jpeg");
    private URL urlImage6 = getClass().getResource("images/526993.jpeg");
    private URL urlImage7 = getClass().getResource("images/527013.jpeg");
    private URL urlImage8 = getClass().getResource("images/563493.jpeg");
    private URL urlImage9 = getClass().getResource("images/gris.jpg");

    private JPanel gamezone;

    private JOptionPane finDePartie;



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

    public void setControlMenu(ActionListener actionListener){
        bestScores.addActionListener(actionListener);
        quitter.addActionListener(actionListener);
        nouvellePartie.addActionListener(actionListener);
        pause.addActionListener(actionListener);
    }

    public void initAttribut(){
        listeBoutton = new JToggleButton[8][8];
        gamezone = new JPanel(new GridLayout(8,8));
        level = new JLabel("Level : " + model.getLevel());
        tries = new JLabel("Tries : " + model.getTries());
        score = new JLabel("Score : " + model.getScore());
        progressBar = new JProgressBar(0,100);
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        finDePartie = new JOptionPane();

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
        icone9 = new ImageIcon(urlImage9);

    }

    public void ajouterWidget(){
        // création de tous les composants graphiques de la fenêtre
        JPanel board = new JPanel();
        JPanel test1 = new JPanel();
        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.X_AXIS));

        barreMenu = new JMenuBar();
        options = new JMenu("Options");
        quitter = new JMenuItem("Quitter");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        bestScores = new JMenuItem("Meilleurs Scores");
        pause = new JMenuItem("Pause");

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
        barreMenu.add(quitter);
        barreMenu.add(pause);
        board.add(infos);
        board.add(gamezone);
        board.add(test1);
        board.setLayout(new BoxLayout(board, BoxLayout.PAGE_AXIS));

        setJMenuBar(barreMenu);
        setContentPane(board);
    }

    public void actualiser(){
        if ( !model.isPartieFinie()){

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
                    if (!model.isPlay()){
                        listeBoutton[i][j].setIcon(icone9);
                    }
                }
            }
            changeValueProgressBar();
            level.setText("Level : " + model.getLevel());
            tries.setText("Tries : " + model.getTries());
            score.setText("Score : " + model.getScore());
            System.out.println("Level : " + model.getLevel() + " Tries : " + model.getTries()+ " Score : " + model.getScore());
        }
    }

    public JToggleButton[][] getListeBoutton() {
        return listeBoutton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void  changeValueProgressBar(){
        this.progressBar.setValue(progressBar.getValue() + (model.getValeurAjouterProgressBarre()*10));
        model.setValeurAjouterProgressBarre(0);
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JOptionPane getFinDePartie() {
        return finDePartie;
    }

    public void setFinDePartie(JOptionPane finDePartie) {
        this.finDePartie = finDePartie;
    }

    public JFrame getJFrame(){
        return this;
    }

    public JMenuItem getQuitter() {
        return quitter;
    }

    public void setQuitter(JMenu quitter) {
        this.quitter = quitter;
    }

    public JMenuItem getNouvellePartie() {
        return nouvellePartie;
    }

    public void setNouvellePartie(JMenuItem nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

    public JMenuItem getBestScores() {
        return bestScores;
    }

    public void setBestScores(JMenuItem bestScores) {
        this.bestScores = bestScores;
    }

    public JMenuItem getPause() {
        return pause;
    }

    public void setPause(JMenuItem pause) {
        this.pause = pause;
    }
}