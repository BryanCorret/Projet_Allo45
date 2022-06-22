import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControleurNavSondeur implements EventHandler<ActionEvent> {
    
    private FenetreHomeAnalyste fen;
    private ConnexionMySQL maConnexion;
    private appliSondage sondage;

    public ControleurNavSondeur(FenetreHomeAnalyste fen, appliSondage sondage) {
        this.fen =fen;
        this.sondage = sondage;
        this.maConnexion = sondage.getConnexion();
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
        VBox vbox = new VBox();
        ToggleGroup group = new ToggleGroup();
        
        
        // radio buttons
        for (Questionnaire sondage : lSondage) {
            RadioButton s = new RadioButton(sondage.getIdQ()+". " + sondage.toString());
            
            vbox.getChildren().addAll(s);
            s.setToggleGroup(group);
        }
        // radio button sélectionné
        Listesondage.getDialogPane().setContent(vbox);
        Listesondage.showAndWait();
        // Afficher le nom sur la vue
        try{
        String s = group.getSelectedToggle().toString();
        String[] parts = s.split("'"); 
        
        String Nom = parts[1].split(" ")[1];

        System.out.println("Sondage sélectionné : " + Nom);
        this.fen.setSondageNom(Nom);
        String id = parts[1].split(" ")[0].charAt(0)+"";

        System.out.println("Num sondage sélectionné : " + id);
        

        this.fen.setSondageId(id);
        
        
        this.fen.MajVue();
        }catch(NullPointerException e){
            System.out.println("Pas de sondage sélectionné");
        }
    }

}
