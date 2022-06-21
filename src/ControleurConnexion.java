import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


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
        System.out.println("Nom utilisateur : "+ NomU+ "Mdp : "+ Mdp);

        // this.sondage.Connexion(NomU, Mdp)
        if(this.sondage.estCorrect(NomU, Mdp)){
            System.out.println("Connexion réussie");
            // POp up
            this.sondage.Connexion(NomU, Mdp);
            Alert AlertConnexion = new Alert(Alert.AlertType.INFORMATION);
            AlertConnexion.setTitle("Connexion réussie");
            AlertConnexion.setHeaderText("Bienvenue "+NomU);
            AlertConnexion.setContentText("Vous êtes connecté");
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
