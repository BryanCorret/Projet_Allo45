import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurConnecterVous implements EventHandler<MouseEvent> {
    
    FenetreInscription fenInscription;
    appliSondage sondage;

    public ControleurConnecterVous(FenetreInscription fenetreInscription , appliSondage sondage) {
        this.fenInscription = fenetreInscription;
        this.sondage = sondage;
    }
   
    
    
    @Override
    public void handle(MouseEvent event) {
        // Changer la fenetre d'inscription a connecterVous
        System.out.println("Changer la fennetre d'inscription a connecterVous");
       
    }
}
    