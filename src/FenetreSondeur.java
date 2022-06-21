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
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FenetreSondeur extends BorderPane {

    
    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonParametre;
    private List<String> listeQuestions;
    
    
  
    public FenetreSondeur(Button boutonHome,Button boutonRefresh,Button boutonParametre,List<String> listeQuestions){
        super();
        this.boutonHome = boutonHome;
        this.listeQuestions = listeQuestions;
        
        
        this.boutonRefresh = boutonRefresh;
        this.boutonParametre = boutonParametre;
        BorderPane borderTop = borderPaneTop();
        VBox vdroite = VBoxDroite();
        VBox vMid = VBoxMidTextArea();
        
        
        this.setTop(borderTop);
        this.setCenter(vMid);
        this.setRight(vdroite);
        
    }
    public List<String> getListe(){
        return this.listeQuestions;

    }

    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);

        Label ltitre = new Label("Titre sondage");

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("./user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);
       
  
        hID.getChildren().addAll(profil,this.boutonParametre);

        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,32));
        border.setStyle("-fx-background-color:#587F264B;");
        
        border.setLeft(hHome);
        border.setCenter(ltitre);
        border.setRight(hID);
        
        Insets arg1 =new Insets(30, 10, 20, 30);
       
        border.setPadding(arg1);

        return border;
    }

    
    private VBox VBoxMidTextArea(){
        VBox vMid = new VBox();
        Label lquestion = new Label(""+"this.getTitreQuestion");
        lquestion.setFont(Font.font(" Arial ",FontWeight.BOLD,24));
       

        TextArea treponse = new TextArea();
        
        BorderPane bot = FlecheBot();
        BorderPane bottom = BorderPaneBot();
        treponse.setStyle("-fx-control-inner-background:#ffdab9;");

        vMid.getChildren().addAll(lquestion,treponse,SliderMidSlider(),bot,new Label("\n"),bottom);
        vMid.setStyle("-fx-background-color:CORNSILK;");
        Insets arg3 = new Insets(20,20,20,20);
        vMid.setPadding(arg3);
        vMid.setAlignment(Pos.CENTER);

        return vMid;
    }
    private BorderPane BorderBoutons(){
        BorderPane boutons = new BorderPane();
        
        // img.setFitHeight(35);img.setFitWidth(35);
        
        Button b1 = new Button("Oui");
        Button b2 = new Button("Non");
        Button b3 = new Button("Je ne sais pas");
        b1.setStyle("-fx-background-color:#663366;-fx-text-fill: white;");
        b2.setStyle("-fx-background-color:#663366;-fx-text-fill: white;");
        b3.setStyle("-fx-background-color:#663366;-fx-text-fill: white;");

        boutons.setLeft(b1);
        boutons.setCenter(b2);
        boutons.setRight(b3);
        Insets arg1 = new Insets(20,20,20,20);
        boutons.setPadding(arg1);
        return boutons;
    }

    private VBox SliderMidSlider(){
        // int value;
        VBox res = new VBox();
        Slider resS = new Slider(0,100,50);
        
        Label valeur = new Label();
        resS.setShowTickLabels(true);
        resS.setShowTickMarks(true);

        
        // resS.valueProperty().addListener(new ChangeListener<Number>() {
        //     @Override
        //     public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        //         value = (int) resS.getValue();
        //         valeur.setText(Integer.toString(value));
        //     }
        // });
        
        res.getChildren().addAll(resS,valeur);

        return res;
    }


    private BorderPane FlecheBot(){
        BorderPane borderBot = new BorderPane();
        ImageView flecheAvant = new ImageView("./fleche.png");
        ImageView flecheApres = new ImageView("./fleche.png");
        flecheApres.setRotate(180.0);
        flecheApres.setFitHeight(40);flecheApres.setFitWidth(40);
        flecheAvant.setFitHeight(40);flecheAvant.setFitWidth(40);
        
        Button BflecheAvant = new Button("",flecheAvant);
        Button BflecheApres = new Button("",flecheApres);
        BflecheApres.setStyle("-fx-background-color:transparent;");
        BflecheAvant.setStyle("-fx-background-color:transparent;");

        BflecheApres.setId("flecheDroite");
        BflecheAvant.setId("flecheGauche");

        borderBot.setRight(flecheAvant);
        borderBot.setLeft(flecheApres);
        return borderBot;
    }


    private VBox VBoxDroite(){
        VBox vDroite = new VBox();
        Label ltitre = new Label("Sommaire: ");
        vDroite.getChildren().addAll(ltitre);
        
        for(String elem : this.listeQuestions){
            Button bouton =new Button(elem);
            bouton.setStyle("-fx-background-color:transparent;");
        
            vDroite.getChildren().add(bouton);
        }

        vDroite.setStyle("-fx-background-color: pink;");
        

        return vDroite;
    }
    
    private BorderPane BorderPaneBot(){
        BorderPane res = new BorderPane();
        Button resultats = new Button("Resultats");
        Button Valider = new Button("Valider");
        res.setLeft(resultats);
        res.setRight(Valider);
        return res;
    }
    
    
}
