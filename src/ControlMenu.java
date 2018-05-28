import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu implements ActionListener {
    Model m;
    Vue v;
    ControlBouton cb;
    public ControlMenu(Model m, Vue v, ControlBouton cb){
        this.m = m;
        this.v = v;
        this.cb = cb;
        v.setControlMenu(this);
    }
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == v.getNouvellePartie()){
            v.dispose();
            m.setPlay(true);
            cb.getTimer().stop();
            m.setPartieFinie(false);
            Model model = new Model();
            ControlGroup control = new ControlGroup(model);
            v.actualiser();
        }
        if (a.getSource() == v.getPause() && m.isPlay() == true){
            cb.getTimer().stop();
            v.getPause().setText("Play");
            m.setPlay(false);
            v.actualiser();
        }else if (a.getSource() == v.getPause() && m.isPlay() == false){
            cb.getTimer().start();
            v.getPause().setText("Pause");
            m.setPlay(true);
            v.actualiser();
        }
        if (a.getSource() == v.getQuitter()){
            System.exit(0);
        }
        if (a.getSource() == v.getBestScores()){
            cb.getTimer().stop();
            m.setPlay(false);
            v.getPause().setText("Pause");
            v.actualiser();
            m.lecture();
            v.getFinDePartie().showMessageDialog(v.getJFrame(), "Best scores :\n    1- " + m.getScore1()
                    + "\n   2- " + m.getScore2() + "\n    3- " + m.getScore3(), "Best scores", JOptionPane.INFORMATION_MESSAGE);
            m.setPlay(true);
            if (m.isPartieFinie() == false){
                cb.getTimer().start();
            }
            v.actualiser();

        }
        if (a.getSource() == v.getHint() && m.isPlay() == true){
            m.Hint();
            if (m.isHintligne() == 0){
                v.actualiser();
                cb.getTimer().stop();
                cb.finDePartie();
                m.gestionScores();
            }
            if (m.isHintligne() == 1){
                v.getListeBoutton()[m.getCoordi()][m.getCoordj()].setBackground(Color.green);
                v.getListeBoutton()[m.getCoordi()+1][m.getCoordj()].setBackground(Color.green);
            }
            if (m.isHintligne() == 2){
                v.getListeBoutton()[m.getCoordi()][m.getCoordj()].setBackground(Color.green);
                v.getListeBoutton()[m.getCoordi()][m.getCoordj()+1].setBackground(Color.green);
            }
            m.setHintligne(0);
        }
    }
}
