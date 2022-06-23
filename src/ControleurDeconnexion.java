import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ControleurDeconnexion implements EventHandler<ActionEvent>{

  private AppliSondage app;

  public ControleurDeconnexion(AppliSondage appliSondage) {
    this.app = appliSondage;
  }

  @Override
  public void handle(ActionEvent event) {
      try{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Voulez-vous vraiment vous déconnecter");
        Optional<ButtonType> option = alert.showAndWait();
        
        if (option.get() == null) {
          System.out.println("Pas de choix");
       } 
       else if (option.get() == ButtonType.OK) {
          this.app.modeConnexion();
          BiblioSQL.exit(this.app.getConnexion());


       } 
       else {
        System.out.println("Rester");}
      }
      
      catch(Exception e){

      }
      
  }
}
