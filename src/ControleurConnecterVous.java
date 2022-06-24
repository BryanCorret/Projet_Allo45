import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurConnecterVous implements EventHandler<MouseEvent> {
    
    FenetreInscription fenInscription = null;
    FenetreConnexion fenConnexion = null;
    AppliSondage sondage;

    public ControleurConnecterVous(FenetreInscription fenetreInscription , AppliSondage sondage) {
        this.fenInscription = fenetreInscription;
        this.sondage = sondage;
    }
    
    @Override
    public void handle(MouseEvent event) {
        // Changer la fenetre d'inscription a connecterVous
            System.out.println("Changer la fennetre d'inscription a connecterVous");
            this.sondage.modeConnexion();    
        }
}