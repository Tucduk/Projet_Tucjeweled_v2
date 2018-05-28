import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlBouton implements ActionListener {

    private Model model;
    private Vue vue;
    private Timer timer=new javax.swing.Timer(3000,new ActionListener() {
        public void actionPerformed(ActionEvent a) {
            if (vue.getProgressBar().getValue() < 100){
                vue.getProgressBar().setValue((vue.getProgressBar().getValue()- model.getLevel())%100);
                vue.actualiser();
            }else {
                model.setLevel(model.getLevel()+1);
                model.setTries(15);
                vue.getProgressBar().setValue((50)%100);
                vue.actualiser();
            }
            if (vue.getProgressBar().getValue()<=0 || model.getTries()<=0){
                vue.actualiser();
                timer.stop();
                if (model.getTries()<=0){
                    model.setBecauseOfTries(true);
                }
                finDePartie();
                model.gestionScores();
            }

        }
    });

    public void finDePartie() {
        String messageFin = " le temp est ecoule";
        String messageFin2 = "";
        if (model.isMeilleur()){
            messageFin2 = "\nmais vous avez battu le meilleur score";
            model.setMeilleur(false);
        }

        if (model.isBecauseOfTries()){
            messageFin = " vous n'avez plus essais";
        }
        if (model.isHintligne()==0){
            messageFin = " vous ne pouvez plus rien faire";
        }
        vue.getFinDePartie().showMessageDialog(vue.getJFrame(), "La partie est finie car " + messageFin + messageFin2 +
                " \n Score: " + model.getScore() + "\n Et rappellez vous que seul les Tuc Original sont comestible", "Game Over", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("images/tucOriginal.jpg")));
        model.setPlay(false);
        vue.actualiser();
        model.setPartieFinie(true);
        model.pause();
    }

    public ControlBouton(Model model, Vue vue){
        this.model = model;
        this.vue = vue;
        vue.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if(vue.getListeBoutton()[i][j].isSelected() && model.isSelect() == false) {
                        model.setX1(i);
                        model.setY1(j);
                        model.setCouleurtemp(model.getTabColor()[i][j]);
                        model.setSelect2(true);
                    }else if (vue.getListeBoutton()[i][j].isSelected() && vue.getListeBoutton()[i][j] != vue.getListeBoutton()[model.getX1()][model.getY1()] && model.isSelect() == true) {
                        model.echanger(i,j);
                        vue.getListeBoutton()[i][j].setSelected(false);
                        vue.getListeBoutton()[model.getX1()][model.getY1()].setSelected(false);
                        vue.actualiser();
                        model.setSelect(false);
                        model.setSelect2(false);
                    }
                }
            }
        if (model.isSelect() == false && model.isSelect2() == true){
            model.setSelect(true);
        }
            vue.getListeBoutton()[model.getCoordi() + 1][model.getCoordj()].setBackground(null);
            vue.getListeBoutton()[model.getCoordi()][model.getCoordj()].setBackground(null);

            vue.getListeBoutton()[model.getCoordi()][model.getCoordj()+1].setBackground(null);
            vue.getListeBoutton()[model.getCoordi()][model.getCoordj()].setBackground(null);
            vue.actualiser();


    }

    public Timer getTimer() {
        return timer;
    }
}