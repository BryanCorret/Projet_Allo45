import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ControleurConnexion implements EventHandler<ActionEvent> {
    
    private FenetreConnexion fenConnexion;
    private appliSondage sondage;

    public ControleurConnexion(FenetreConnexion fenConnexion, appliSondage appliSondage) {
        this.fenConnexion = fenConnexion;
        this.sondage = appliSondage;
    }

    @Override
    public void handle(ActionEvent event) {
        
        String NomU = fenConnexion.getNomU();
        String Mdp = fenConnexion.getMdp();

        //this.sondage.Connexion(NomU, Mdp); 

    }
}
