public class ControlGroup {
    Model model;
    Vue vue;
    ControlBouton controlButton;

    public ControlGroup(Model model){
        this.model = model;
        vue = new Vue(model);
        controlButton = new ControlBouton(model, vue);
    }
}
