import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurDeconnexion implements EventHandler<ActionEvent>{

  private appliSondage app;

  public ControleurDeconnexion(appliSondage appliSondage) {
    this.app = appliSondage;
  }

  @Override
  public void handle(ActionEvent event) {
      try{
        BiblioSQL.exit(this.app.getConnexion());
        this.app.modeConnexion();
      }
      catch(Exception e){

      }
      }
  }
