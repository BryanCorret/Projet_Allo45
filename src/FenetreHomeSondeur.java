import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData ;
import javafx.scene.text.FontWeight;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreHomeSondeur extends BorderPane {

    
    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonDeco;
    private AppliSondage sondage;
    
    
  
    public FenetreHomeSondeur(AppliSondage sondage, Button boutonHome2,Button boutonRefresh,Button boutonDeconnexion){
        super();
        this.sondage = sondage;
        this.boutonHome = boutonHome2;
        this.boutonRefresh = boutonRefresh;
        this.boutonDeco = boutonDeconnexion;
        BorderPane borderTop = borderPaneTop();
        HBox hBox = HBoxMid();
        this.setTop(borderTop);
        this.setCenter(hBox);
    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);
        System.out.println(this.sondage.getUtilisateur());
        Label ltitre = new Label("Bienvenue "+ this.sondage.getUtilisateur().getPrenom());

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("file:IMG/user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);
       
        Button Deco = this.boutonDeco;  
        hID.getChildren().addAll(profil,Deco);

        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,32));
        border.setStyle("-fx-background-color:#ffffff;");
        
        border.setLeft(hHome);
        border.setCenter(ltitre);
        border.setRight(hID);

        Insets arg1 =new Insets(30, 10, 20, 30);
       
        border.setPadding(arg1);

        return border;
    }

    

    private HBox HBoxMid(){
        HBox hBox = new HBox();

        ImageView Ilogo = new ImageView("file:IMG/User.png");
        ImageView IFormul =new ImageView("file:IMG/Formulaire.png");
        ImageView IEye =new ImageView("file:IMG/Eye.png");
        
        Ilogo.setFitHeight(80);Ilogo.setFitWidth(80);
        IFormul.setFitHeight(80);IFormul.setFitWidth(80);
        IEye.setFitHeight(80);IEye.setFitWidth(80);
        
       
        Button bouton1 = new Button("Editer le compte",Ilogo);
        Button bouton2 = new Button("Nouveau Formulaire",IFormul);
        Button bouton3 = new Button("Inspecter les Sondages",IEye);
        bouton2.setOnAction(new ControleurNavSondeur(this.sondage));
        bouton3.setOnAction(new ControleurInspectSondage(this, sondage));
        

        bouton1.setContentDisplay(ContentDisplay.TOP);
        bouton2.setContentDisplay(ContentDisplay.TOP);
        bouton3.setContentDisplay(ContentDisplay.TOP);
       
        
        bouton1.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#15d798;-fx-font-family: Arial;-fx-font-size: 18px;");
        bouton2.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#ff9900;-fx-font-family: Arial;-fx-font-size: 18px;");
        bouton3.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#047f04;-fx-font-family: Arial;-fx-font-size: 18px;");
       
        
        hBox.setStyle("-fx-background-color:#d9d9d9;");
        
        hBox.setSpacing(30.0);
        Insets arg2 =new Insets(20, 10, 10, 50);
       
        hBox.setPadding(arg2);
        hBox.getChildren().addAll(bouton1,bouton2,bouton3);

       
        bouton1.setPrefSize(300, 100);bouton2.setPrefSize(300, 100);bouton3.setPrefSize(300, 100);
        

        return hBox;
    }

    
    
}