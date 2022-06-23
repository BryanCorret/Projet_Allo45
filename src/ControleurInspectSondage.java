import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurInspectSondage implements EventHandler<ActionEvent>{
    FenetreHomeSondeur fenHomSond;
    AppliSondage sondage;

    public ControleurInspectSondage(FenetreHomeSondeur fenetre, AppliSondage sondage2) {
        this.fenHomSond = fenetre;
        this.sondage = sondage2;
    }

    @Override
    public void handle(ActionEvent event) {
        String temp = "";
        for(String Titre: BiblioSQL.getListQuestionnaires(this.sondage.getConnexion())){
            temp += Titre +"\n";
        }
        Alert Informations = new Alert(Alert.AlertType.INFORMATION);
            Informations.setTitle("Affichage des sondages disponibles");
            Informations.setHeaderText("Sondages de la base de données ALLO45");
            Informations.setContentText(temp);
            Informations.showAndWait();
}
    
}