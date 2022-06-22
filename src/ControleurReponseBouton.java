import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurReponseBouton implements EventHandler<ActionEvent>{

    private FenetreSondeur fenetr;

    public ControleurReponseBouton(FenetreSondeur main){
        this.fenetr = main;
    }
    @Override
    public void handle(ActionEvent event) {
        Button bouton = (Button) (event.getTarget());
        this.fenetr.setTexteChoice(bouton.getText());
        this.fenetr.maj(this.fenetr.getIde());
        
    }

}
