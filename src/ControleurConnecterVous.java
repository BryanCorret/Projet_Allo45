import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurConnecterVous implements EventHandler<MouseEvent> {
    
    FenetreInscription fenInscription = null;
    FenetreConnexion fenConnexion = null;
    appliSondage sondage;

    public ControleurConnecterVous(FenetreInscription fenetreInscription , appliSondage sondage) {
        this.fenInscription = fenetreInscription;
        this.sondage = sondage;
    }
   
    
    
    public ControleurConnecterVous(FenetreConnexion fenetreConnexion, appliSondage sondage2) {
        this.fenConnexion = fenetreConnexion;
        this.sondage = sondage2;
    }



    @Override
    public void handle(MouseEvent event) {
        // Changer la fenetre d'inscription a connecterVous
        if (!(fenInscription == null)) {
            System.out.println("Changer la fennetre d'inscription a connecterVous");
        
        }
       
        // Changer la fenetre de connexion a jeu
        if (!(fenConnexion == null)) {
            System.out.println("Changer la fennetre de connexion a jeu");
        }

    }
}
    
