import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurNavSondeur implements EventHandler<ActionEvent> {
    
    private ConnexionMySQL maConnexion;
    private AppliSondage sondage;
    private ComboBox<String> txt1;
    private ComboBox<String> txt2;
    private Stage dialog;
    private int test;

    public ControleurNavSondeur(AppliSondage sondage) {
        this.sondage = sondage;
        this.maConnexion = sondage.getConnexion();
        this.txt1 = new ComboBox<>();
        this.txt2 = new ComboBox<>();
        for(Panel Pann : BiblioSQL.getToutLesPanels(this.sondage.getConnexion()))
        this.txt1.getItems().add(Pann.getNomPan());
        this.txt2.getItems().add("");
        this.dialog = new Stage();
        this.test = 0;
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
        this.maj();       
        // VBox pour les boutons + group
    }
    public void maj(){
        VBox dialogVbox = new VBox(20);
        ImageView logo = new ImageView("file:./IMG/logo_agrandi.png");
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        
        Label Pan = new Label("Selectionnez un Panel");
        Pan.setFont(Font.font(" Arial ",FontWeight.BOLD,30));

        Label Quest = new Label("Selectionnez un questionnaire");
        Quest.setFont(Font.font(" Arial ",FontWeight.BOLD,20));
        
        Button valider = new Button("Commencer le sondage");
        valider.setOnAction(new ControleurVerifLancement(sondage, this));
        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        this.txt1.setOnAction(new ControleurCBox2(this));
        dialogVbox.getChildren().addAll(logo, Pan, this.txt1, Quest, this.txt2, valider);
        Insets arg0 = new Insets(10);
        dialogVbox.setPadding(arg0);
        dialog.setScene(dialogScene);
        this.test+=1;
        // valider.setOnAction(new ControleurVerifLancement(this.sondage, this));
        dialog.show();
    }
    public String getTxt1(){
        return this.txt1.getValue();
    }
    public String getTxt2(){
        return this.txt2.getValue();
    }
    public void setText2(){
        this.txt2.getItems().clear();
        this.txt2.getItems().addAll(BiblioSQL.getNomDesQuestionnaireParRapportAUnPanel(this.sondage.getConnexion(), this.txt1.getValue()));
        System.out.println(this.txt2.getItems());
    }
    public Stage getStage(){
        return this.dialog;
    } 
    public void close(){
        this.dialog.close();
    }
}
