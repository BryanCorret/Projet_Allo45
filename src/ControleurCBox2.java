import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurCBox2 implements EventHandler<ActionEvent> {
    private ControleurNavSondeur ctrl;

    public ControleurCBox2(ControleurNavSondeur verif){
        this.ctrl = verif;
    }
    @Override
    public void handle(ActionEvent event) {
        if(!(this.ctrl.getTxt1().equals("")))
        ctrl.setText2();
        ctrl.maj();
    }
    
}
