import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurParam implements EventHandler<ActionEvent> {

    private AppliSondage app;

    public ControleurParam(AppliSondage main){
        this.app = main;
    }
    @Override
    public void handle(ActionEvent event) {
            try{
              if (this.app.getUserRole() == 3){
                this.app.modeParametreAnalyste();
              }
              else {this.app.modeParametreAnalyste();}
            }
            catch(Exception e){
              
            }
        }    
}
