import java.sql.SQLException;

import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class ControleurConnexion implements EventHandler<ActionEvent> {
    
    private FenetreConnexion fenConnexion;
    private appliSondage sondage;
    private ConnexionMySQL connexion;

    public ControleurConnexion(FenetreConnexion fenConnexion, appliSondage appliSondage,ConnexionMySQL co) {
        this.fenConnexion = fenConnexion;
        this.sondage = appliSondage;
        this.connexion = co;
    }

    @Override
    public void handle(ActionEvent event) {
        
        String NomU = fenConnexion.getNomU();
        String Mdp = fenConnexion.getMdp();
            System.out.println("Est tu connecté ? ");
            BiblioSQL.login(this.sondage.getConnexion(), this.fenConnexion.getNomU(), this.fenConnexion.getMdp());
        // this.sondage.Connexion(NomU, Mdp)
        if(BiblioSQL.userExists(NomU, Mdp) != -1){
            this.sondage.setUtilisateur(BiblioSQL.login(this.connexion,NomU, Mdp));
            System.out.println("Connexion réussie");
            switch(BiblioSQL.userExists(NomU, Mdp)){
                // case 1: this.sondage.modeConcepteur() / On a pas de concepteur
                case 2:
                    this.sondage.modeHomeSondeur();
                    break;
                case 3:
                    this.sondage.modeHomeAnalyste();
                    break;
            }
        }
        else{
            System.out.println("Connexion échouée");
            Alert AlertErreur = new Alert(Alert.AlertType.INFORMATION);
            AlertErreur.setTitle("Connexion échouée");
            AlertErreur.setHeaderText("Erreur");
            AlertErreur.setContentText("Votre nom d'utilisateur ou votre mot de passe est incorrect");
            
        } 

    }
}
