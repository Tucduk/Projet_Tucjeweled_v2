import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlBouton implements ActionListener {

    private Model model;
    private Vue vue;
    private Timer timer=new javax.swing.Timer(3000,new ActionListener() {
        public void actionPerformed(ActionEvent a) {
            if (vue.getProgressBar().getValue() < 100){
                vue.getProgressBar().setValue((vue.getProgressBar().getValue()-10)%100);
                vue.actualiser();
            }else {
                model.setLevel(model.getLevel()+1);
                vue.getProgressBar().setValue((50)%100);
                vue.actualiser();
            }
            if (vue.getProgressBar().getValue()<10){
                timer.stop();
                finDePartie();
            }

        }
    });

    public void finDePartie() {
        vue.getFinDePartie().showMessageDialog(vue.getJFrame(), "La partie est finie. \n Score: " + model.getScore(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        model.setPlay(false);
        vue.actualiser();
        model.setPartieFinie(true);
        model.pause();




        /*model.setPartieFinie(true);
        int reponse = vue.getFinDePartie().showConfirmDialog(vue.getJFrame(), "La partie est finie, votre score est de" + model.getScore() + " \nNouvelle partie ?", "Fin de la partie",
                JOptionPane.YES_NO_OPTION);
        if (reponse == 0) {
            Model model = new Model();
            ControlGroup control = new ControlGroup(model);
        }*/
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

    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}