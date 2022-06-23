import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurInscrit implements EventHandler<MouseEvent> {
    
    FenetreConnexion fenConnexion;
    AppliSondage sondage;

    public ControleurInscrit(FenetreConnexion fenetreConnexion, AppliSondage sondage2) {
        this.fenConnexion = fenetreConnexion;
        this.sondage = sondage2;
    }

    @Override
    public void handle(MouseEvent event) {
        // Changer la fenetre d'inscription a connecterVous
        System.out.println("Changer la fennetre d'inscription a connecterVous");
        this.sondage.modeInscription();
        
    }
}
    

