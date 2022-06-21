import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurChangementFenetre implements EventHandler<ActionEvent>{

  private appliSondage app;

  public ControleurChangementFenetre(appliSondage appliSondage) {
    this.app = appliSondage;
  }

  @Override
  public void handle(ActionEvent event) {
      String rep = this.app.getFenetreActu();
      if (rep==null && this.app.getUserRole()){
        Alert AlertConnexion = new Alert(Alert.AlertType.INFORMATION);
            AlertConnexion.setTitle("Attention");
            AlertConnexion.setHeaderText("Sondage non selectionné");
            AlertConnexion.setContentText("Veuillez choisir un sondage avec le bouton Sélectionner un sondage");
      }
      switch(rep){
        case "Analyste":
          this.app.modeAnalyste();
          break;
        case "Sondeur":
          this.app.modeSondeur();
          break;
        case "Donnees":
          this.app.modeDonneesBrutes();
          break;
        case "ParamAnalyste":
          this.app.modeParametreAnalyste();
          break;
      }
  }
    
}
