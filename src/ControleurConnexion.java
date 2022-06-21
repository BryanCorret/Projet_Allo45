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
        try{
            System.out.println("Est tu connecté ? ");
            ConnexionMySQL laConnexion = new ConnexionMySQL();
            laConnexion.connecter("root", "mdp_root");
            BiblioSQL.login(laConnexion, this.fenConnexion.getNomU(), this.fenConnexion.getMdp());
        }    
        catch (ClassNotFoundException ex){
            System.out.println("Driver MySQL non trouvé!!!");
        }
        catch (SQLException ex){
            System.out.println("Erreur de connexion!!!");
        }

        // this.sondage.Connexion(NomU, Mdp)
        if(BiblioSQL.userExists(this.connexion,NomU, Mdp)){
            System.out.println("Connexion réussie");
            // POp up
            BiblioSQL.login(this.connexion,NomU, Mdp);
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
