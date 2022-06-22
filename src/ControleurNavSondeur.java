import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurNavSondeur implements EventHandler<ActionEvent> {
    
    private ConnexionMySQL maConnexion;
    private appliSondage sondage;
    private ComboBox<String> txt1;
    private ComboBox<String> txt2;

    public ControleurNavSondeur(appliSondage sondage) {
        this.sondage = sondage;
        this.maConnexion = sondage.getConnexion();
        this.txt1 = new ComboBox<>();
        this.txt2 = new ComboBox<>();
        this.txt1.getItems().add("");
        this.txt2.getItems().add("");
    }

    @Override
    public void handle(ActionEvent event) {
       // ArrayList<String> lSondage = BiblioSQL.getListSondage(maConnexion);
       ArrayList<Questionnaire> lSondage = new ArrayList<>();
       lSondage.add(new Questionnaire(1, "Totot", "Ouvert"));
       lSondage.add(new Questionnaire(2, "ratatata", "Ouvert"));

        
        
        System.out.println("Lancement Pop-up");
        Alert Listesondage = new Alert(Alert.AlertType.INFORMATION);
        Listesondage.setTitle("Liste des sondages");
        Listesondage.setHeaderText("Liste des sondages");
        
        // VBox pour les boutons + group
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        Label Pan = new Label("Selectionnez un Panel");
        Label Quest = new Label("Selectionnez un questionnaire");
        Button valider = new Button("Commencer le sondage");
        valider.setOnAction(new ControleurVerifLancement(sondage, this));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialogVbox.getChildren().addAll(Pan, this.txt1, Quest, this.txt2, valider);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public String getTxt1(){
        return this.txt1.getValue();
    }
    public String getTxt2(){
        return this.txt2.getValue();
    }
}
