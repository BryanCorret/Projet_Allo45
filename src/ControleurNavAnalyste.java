import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControleurNavAnalyste implements EventHandler<ActionEvent> {
    
    private FenetreHomeAnalyste fen;
    private ConnexionMySQL maConnexion;
    private appliSondage sondage;

    public ControleurNavAnalyste(FenetreHomeAnalyste fen, appliSondage sondage) {
        this.fen =fen;
        this.sondage = sondage;
        this.maConnexion = sondage.getConnexion();
    }

    @Override
    public void handle(ActionEvent event) {
       // ArrayList<String> lSondage = BiblioSQL.getListSondage(maConnexion);
       ArrayList<String> lSondage = new ArrayList<>();
        lSondage.add("Sondage 1");
        lSondage.add("Sondage 2");
        lSondage.add("Sondage 3");
        lSondage.add("Sondage 4");
        lSondage.add("Sondage 5");
        
        System.out.println("Lancement Pop-up");
        Alert Listesondage = new Alert(Alert.AlertType.INFORMATION);
        Listesondage.setTitle("Liste des sondages");
        Listesondage.setHeaderText("Liste des sondages");
        
        // VBox pour les boutons + group
        VBox vbox = new VBox();
        ToggleGroup group = new ToggleGroup();
        
        
        // radio buttons
        for (String sondage : lSondage) {
            RadioButton s = new RadioButton("" + sondage);
            vbox.getChildren().add(s);
            s.setToggleGroup(group);
        }
        // radio button sélectionné
        Listesondage.getDialogPane().setContent(vbox);
        Listesondage.showAndWait();
        String s = group.getSelectedToggle().toString();
        String[] parts = s.split("'"); 
        System.out.println("Sondage sélectionné : " + parts[1]);
        this.fen.setSondageSelectionne(parts[1]);

   
        this.fen.MajVue();
    }

}
