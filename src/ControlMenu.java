import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

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
            System.out.println("jshdjkqslkjdfqkm,lksq,lk");
            System.exit(0);
        }
        if (a.getSource() == v.getBestScores()){
            cb.getTimer().stop();
            m.setPlay(false);
            v.getPause().setText("Pause");
            v.actualiser();
            v.getFinDePartie().showMessageDialog(v.getJFrame(), "Best scores :\n    1- " + m.lecture(1)
                    + "\n   2- " + m.lecture(2) + "\n    3- " + m.lecture(3), "Best scores", JOptionPane.INFORMATION_MESSAGE);
            m.setPlay(true);
            v.actualiser();

        }
    }
}
