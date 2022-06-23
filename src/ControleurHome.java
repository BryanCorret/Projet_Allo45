import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurHome implements EventHandler<ActionEvent>{

  private AppliSondage app;

  public ControleurHome(AppliSondage appliSondage) {
    this.app = appliSondage;
  }

  @Override
  public void handle(ActionEvent event) {
      try{
        if (this.app.getUserRole() == 3){
          this.app.modeHomeAnalyste();
        }
        else {this.app.modeHomeSondeur();}
      }
      catch(Exception e){
        
      }
      }
  }
    

