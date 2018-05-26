public class ControlGroup {
    Model model;
    Vue vue;
    ControlBouton controlButton;
    ControlMenu controlMenu;

    public ControlGroup(Model model){
        this.model = model;
        vue = new Vue(model);
        controlButton = new ControlBouton(model, vue);
        controlMenu = new ControlMenu(model,vue, controlButton);
    }
}
