import java.sql.SQLException;

import javax.print.DocFlavor.STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class ControleurConnexion implements EventHandler<ActionEvent> {
    
    private FenetreConnexion fenConnexion;
    private AppliSondage sondage;
    private ConnexionMySQL connexion;

    public ControleurConnexion(FenetreConnexion fenConnexion, AppliSondage appliSondage,ConnexionMySQL co) {
        this.fenConnexion = fenConnexion;
        this.sondage = appliSondage;
        this.connexion = co;
    }

    @Override
    public void handle(ActionEvent event) {
            System.out.println("Est tu connecté ? ");
            try{
            Utilisateur userX = BiblioSQL.login(this.sondage.getConnexion(), this.fenConnexion.getNomU(), this.fenConnexion.getMdp());
        // this.sondage.Connexion(NomU, Mdp)
            this.sondage.setUtilisateur(userX);
            switch(userX.getIdRole()){
                // case 1: this.sondage.modeConcepteur() / On a pas de concepteur
                case 2:
                    this.sondage.modeHomeSondeur();
                    break;
                case 3:
                    this.sondage.modeHomeAnalyste();
                    break;
            }
        }
        catch(Exception ex){
            System.out.println("Connexion échouée");
            this.fenConnexion.setErreur(true);
            this.fenConnexion.majVue();
        }  
    } 

}
