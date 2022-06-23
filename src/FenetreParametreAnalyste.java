import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetreParametreAnalyste extends BorderPane {
            private Button boutonHome;
            private Button boutonRefresh;
            private FileChooser fileChooser = new FileChooser();
            private AppliSondage sondage;
            
            
    public FenetreParametreAnalyste(Button boutonHome,Button boutonRefresh){
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh; 
        this.sondage = new AppliSondage();

        this.setTop(borderPaneTop());
        this.setCenter(borderPaneCenter());
    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);

        Label ltitre = new Label("Parametre"+"this.getNom()");

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("file:IMG/user.jpg");
        profil.setFitHeight(50);
        profil.setFitWidth(50);
        
        hID.getChildren().addAll(profil);

        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,25));
        border.setStyle("-fx-background-color:#ffffff;");
        
        border.setLeft(hHome);
        border.setCenter(ltitre);
        border.setRight(hID);

        Insets arg1 =new Insets(30, 10, 20, 30);
        
        border.setPadding(arg1);

        return border;
    }

    private BorderPane borderPaneCenter(){
        BorderPane border = new BorderPane();
        border.setStyle("-fx-background-color:#A0A3A9;");
       // GRID PANE
       GridPane gridPane = new GridPane();
       gridPane.setPadding(new Insets(10, 10, 10, 10));
       gridPane.setVgap(10);
       gridPane.setHgap(10);
       
        // text
        VBox vBox = new VBox();
        Text text = new Text("Parametre");
        text.setFont(Font.font(" Arial ",FontWeight.BOLD,25));
        
        
        // imageVIew
        ImageView imageViewQ = new ImageView(new Image("file:IMG/ButtuonExportQuest.png"));
        ImageView imageViewS = new ImageView(new Image("file:IMG/ButtonExport.png"));
        ImageView imageViewI = new ImageView(new Image("file:IMG/ButtonImport.png"));

        // taille Image View
        imageViewQ.setFitHeight(55);
        imageViewQ.setFitWidth(135);
        
        imageViewS.setFitHeight(55);
        imageViewS.setFitWidth(135);
        
        imageViewI.setFitHeight(55);
        imageViewI.setFitWidth(135);
        
        // ajout button

        Button buttonExportQ = new Button("",imageViewQ);
        Button buttonExportS= new Button("",imageViewS);
        Button buttonImport = new Button("",imageViewI);

        buttonExportQ.setStyle("-fx-background-color: transparent;");
        buttonExportS.setStyle("-fx-background-color: transparent;");
        buttonImport.setStyle("-fx-background-color: transparent;");

        
        
        // ajout button
        gridPane.add(buttonExportQ, 0, 1);
        gridPane.add(buttonExportS, 0, 2);
        gridPane.add(buttonImport, 0, 3);

        // combo box
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("CSV","JSON","XML");
        comboBox.setValue("CSV");
        comboBox.setStyle("-fx-background-color: transparent;" + "-fx-border: solid;"+"-fx-border-color: #000000;" + "-fx-border-width: 1px; " + "-fx-font-size: 15px;");
        
        gridPane.add(comboBox, 1,1);

        ComboBox<String> comboBox2 = new ComboBox<String>();
        comboBox2.getItems().addAll("JSON","CSV");
        comboBox2.setValue("JSON");
        comboBox2.setStyle("-fx-background-color: transparent;" + "-fx-border: solid;"+"-fx-border-color: #000000;" + "-fx-border-width: 1px; " + "-fx-font-size: 15px;");
        
        gridPane.add(comboBox2, 1,2);

        // file chooser

        Button buttonFile = new Button("Choisir un fichier");
        buttonFile.setOnAction(new ControleurFileChooser(this, this.sondage)
           
        );

        buttonFile.setStyle("-fx-background-color: transparent;" + "-fx-border: solid;"+"-fx-border-color: #000000;" + "-fx-border-width: 1px; " + "-fx-font-size: 15px;");


        gridPane.add(buttonFile, 1,3);

        border.setTop(vBox);
        border.setCenter(gridPane);
        return border;
    }

  public FileChooser getFileChooser() {
        return fileChooser;
    }



  
}
        

