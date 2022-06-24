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

public class FenetreHomeAnalyste extends BorderPane {

    
    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonDeco;
    private Button boutonResearch;
    private Button boutonParam;
    private String sondageid;
    private AppliSondage sondage;
    public String SondageNom;
    
  
    public FenetreHomeAnalyste(Button boutonHome,Button boutonRefresh,Button boutonDeco, Button Param,AppliSondage s){
        super();
        this.sondage = s;
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;
        this.boutonDeco = boutonDeco;
        this.boutonParam = Param;
        this.sondageid = "";
        this.SondageNom = "";
        this.sondage = s;
        BorderPane borderTop = borderPaneTop();
        HBox hBox = HBoxMid();
        this.setTop(borderTop);
        this.setCenter(hBox);
    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        HBox hRefresh = new HBox();
        Text textHome = new Text("Sondage séléctionné : " + SondageNom);
        textHome.setFont(Font.font("Arial",15));
        hRefresh.getChildren().add(textHome);
        hRefresh.setAlignment(Pos.CENTER);
        hRefresh.setPadding(new Insets(10,-310,10,10));

        
        if (sondageid.equals("")){
            textHome.setVisible(false);
        }
        else{
            textHome.setVisible(true);
        }

        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh, this.boutonParam, hRefresh);
        hHome.setSpacing(-5);


        Label ltitre = new Label("Bienvenue "+this.sondage.getUtilisateur().getPrenom());
        ltitre.setAlignment(Pos.CENTER);

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

        if(!(this.sondageid.equals("")) && !(this.SondageNom.equals(""))){
            System.out.println("Sondage séléctionné par la vue : " + this.SondageNom);
            System.out.println("Sondage séléctionné par la vue : " + this.sondageid);
        }
     

        return border;
    }



    private HBox HBoxMid(){
        HBox hBox = new HBox();

        ImageView Ilogo = new ImageView("file:IMG/User.png");
        ImageView IFormul =new ImageView("file:IMG/Formulaire.png");
        ImageView ISearch =new ImageView("file:IMG/Search.png");
        ImageView ISave =new ImageView("file:IMG/Save.png");
        Ilogo.setFitHeight(70);Ilogo.setFitWidth(70);
        IFormul.setFitHeight(70);IFormul.setFitWidth(70);
        ISearch.setFitHeight(70);ISearch.setFitWidth(70);
        ISave.setFitHeight(70);ISave.setFitWidth(70);
        
       
        Button bouton1 = new Button("Editer le compte",Ilogo);
        Button bouton2 = new Button("Sélectionner le Sondage",IFormul);
        bouton2.setOnAction(new ControleurNavAnalyste(this, this.sondage));
        Button bouton3 = new Button("Rechercher une question",ISearch);
        Button bouton4 = new Button("Inspecter le Sondage",ISave);
        bouton4.setOnAction(new ControleurInpecterElement(this.sondage, this));

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


    public void setSondageId(String id){
        this.sondageid = id;
    }
    public String getSondageid() {
        return sondageid;
    }

    public void setSondageNom(String sondageNom) {
        SondageNom = sondageNom;
    }
    public void MajVue(){
        this.setTop(borderPaneTop());
    }
}