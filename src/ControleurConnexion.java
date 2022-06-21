import java.sql.SQLException;

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
        
        // String NomU = fenConnexion.getNomU();
        // String Mdp = fenConnexion.getMdp();
        try{
            System.out.println("Est tu connecté ? ");
            ConnexionMySQL laConnexion = new ConnexionMySQL();
            laConnexion.connecter("root", "mdp_root");
            BiblioSQL.login(laConnexion, this.fenConnexion.getNomU(), this.fenConnexion.getMdp());
        }    
        catch (ClassNotFoundException ex){
            System.out.println("Driver MySQL non trouvé!!!");
            System.exit(1);
        }
        catch (SQLException ex){
            System.out.println("Erreur de connexion!!!");
            System.exit(1);
        }

        //this.sondage.Connexion(NomU, Mdp); 

    }
}
