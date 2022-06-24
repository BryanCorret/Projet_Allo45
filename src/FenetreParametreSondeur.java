import javafx.application.Application;
import javafx.application.Platform;
// import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.text.*; 
import javafx.geometry.*;



public class FenetreParametreSondeur extends BorderPane{

    private Button boutonHome;
    private Button boutonRefresh;
    
    private FenetreSondeur sondeur;
    private AppliSondage appli;

    private ConnexionMySQL laConnexionMySQL;
    private Sonde sonde;
    private Utilisateur utilisateur;


    public FenetreParametreSondeur(Button boutonHome, Button boutonRefresh){
        super();

        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;

        //on assemble la fenêtre

        //top
        this.setTop(this.borderPaneTop());
        
        //center
        this.setCenter(this.gridPaneCenter());
    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        
        ImageView profil = new ImageView("file:./IMG/user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);

        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh, profil);
        
        border.setStyle("-fx-background-color:#587F264B;");
        
        Insets arg1 =new Insets(30, 10, 20, 30);
        border.setPadding(arg1);

        return border;
    }


    private GridPane gridPaneCenter(){
        GridPane gp = new GridPane();

        //le texte/titre
        Text titreParametre = new Text("Paramètres");
        titreParametre.setFont(Font.font(" Arial ",FontWeight.BOLD,22));

        //les boutons

        //les images des boutons
        ImageView ivValider= new ImageView(new Image("file:IMG/ButtonValider"));
        ImageView ivResultat= new ImageView(new Image("file:IMG/ButtonResultat"));
        ivValider.setFitHeight(55);ivValider.setFitWidth(135);
        ivResultat.setFitHeight(55);ivResultat.setFitWidth(135);

        //les boutons
        Button buttonValider = new Button("", ivValider);
        Button buttonResultat = new Button("", ivResultat);

        buttonValider.setStyle("-fx-background-color: transparent;");buttonResultat.setStyle("-fx-background-color: transparent;");
        
        //on met en action les boutons
        buttonValider.setOnAction(new ControleurValiderReponse(this.sondeur, this.appli, this.laConnexionMySQL, this.sonde, this.utilisateur));
        buttonResultat.setOnAction(new ControleurResultat(this.sondeur));

        //on met en forme tous les éléments
        gp.add(titreParametre,0,0);
        gp.add(buttonValider, 0,1);gp.add(buttonResultat, 1,1);
        gp.setAlignment(Pos.CENTER);

        return gp;
    }

}