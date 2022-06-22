import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurVerifLancement implements EventHandler<ActionEvent>{

    private ControleurNavSondeur ctrl;
    private appliSondage main;

    public ControleurVerifLancement(appliSondage main, ControleurNavSondeur ctrl){
        this.main = main;
        this.ctrl = ctrl;
    }
    @Override
    public void handle(ActionEvent event) {
        this.main.modeSondeur(BiblioSQL.);
    }

}