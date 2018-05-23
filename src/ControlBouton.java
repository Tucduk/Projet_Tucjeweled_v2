import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBouton implements ActionListener {

    private Model model;
    private Vue vue;



    public ControlBouton(Model model, Vue vue){
        this.model = model;
        this.vue = vue;
        vue.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e) {


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
                        model.destroyLorC();
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
}