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

public class FenetreHome extends BorderPane {

    
    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonDeco;
    private Button boutonCompte;
    private Button boutonSelect;
    private Button boutonResearch;
    private Button boutonInspect;
    
  
    public FenetreHome(Button boutonHome,Button boutonRefresh,Button boutonDeco){
        super();
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;
        this.boutonDeco = boutonDeco;
        BorderPane borderTop = borderPaneTop();
        HBox hBox = HBoxMid();
        
        this.setTop(borderTop);
        this.setCenter(hBox);
    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);

        Label ltitre = new Label("Bienvenue"+"this.getNom()");

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("./user.jpg");
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

        ImageView Ilogo = new ImageView("./User.png");
        ImageView IFormul =new ImageView("./Formulaire.png");
        ImageView ISearch =new ImageView("./Search.png");
        ImageView ISave =new ImageView("./Save.png");
        Ilogo.setFitHeight(70);Ilogo.setFitWidth(70);
        IFormul.setFitHeight(70);IFormul.setFitWidth(70);
        ISearch.setFitHeight(70);ISearch.setFitWidth(70);
        ISave.setFitHeight(70);ISave.setFitWidth(70);
        
       
        Button bouton1 = new Button("Editer le compte",Ilogo);
        Button bouton2 = new Button("Sélectionner le Sondage",IFormul);
        Button bouton3 = new Button("Rechercher une question",ISearch);
        Button bouton4 = new Button("Inspecter le Sondage",ISave);

        bouton1.setContentDisplay(ContentDisplay.TOP);
        bouton2.setContentDisplay(ContentDisplay.TOP);
        bouton3.setContentDisplay(ContentDisplay.TOP);
        bouton4.setContentDisplay(ContentDisplay.TOP);
        
        bouton1.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#15d798;-fx-font-family: Arial;-fx-font-size: 18px;");
        bouton2.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#ff9900;-fx-font-family: Arial;-fx-font-size: 18px;");
        bouton3.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#9900ff;-fx-font-family: Arial;-fx-font-size: 18px;");
        bouton4.setStyle("-fx-text-fill:#ffffff;-fx-background-color:#047f04;-fx-font-family: Arial;-fx-font-size: 18px;");
        
        
        hBox.setStyle("-fx-background-color:#d9d9d9;");
        
        hBox.setSpacing(30.0);
        Insets arg2 =new Insets(20, 10, 10, 50);
       
        hBox.setPadding(arg2);
        hBox.getChildren().addAll(bouton1,bouton2,bouton3,bouton4);

       
        bouton1.setPrefSize(300, 100);bouton2.setPrefSize(300, 100);bouton3.setPrefSize(300, 100);bouton4.setPrefSize(300, 100);
        

        return hBox;
    }

    
    
}