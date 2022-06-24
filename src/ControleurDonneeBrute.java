import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurDonneeBrute implements EventHandler<ActionEvent>{
    AppliSondage app;
    public ControleurDonneeBrute(AppliSondage app){
        this.app = app;
    }
    @Override
    public void handle(ActionEvent arg0) {
    
        this.app.modeDonneesBrutes();
    }

}
