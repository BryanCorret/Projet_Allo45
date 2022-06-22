import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurHome implements EventHandler<ActionEvent>{

  private appliSondage app;

  public ControleurHome(appliSondage appliSondage) {
    this.app = appliSondage;
  }

  @Override
  public void handle(ActionEvent event) {
      try{
        if (this.app.getUserRole() == 1){
          this.app.modeHomeAnalyste();
        }
        else {this.app.modeHomeSondeur();}
      }
      catch(Exception e){
        
      }
      }
  }
    

