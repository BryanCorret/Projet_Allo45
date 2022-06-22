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
    private ComboBox<String> comboMultiple;
    private Slider slider;
    private TextArea area;
    private Questionnaire sondage;
    private Question questionActuelle;
    private String valeurBouton;
    private BorderPane fleche;
    private ConnexionMySQL connexionSQL;
    
  
    public FenetreSondeur(Button boutonHome,Button boutonRefresh,Button boutonParametre,Questionnaire sondage, BorderPane fleche,ConnexionMySQL connexionSQL){
        super();
        this.boutonHome = boutonHome;
        this.connexionSQL=connexionSQL;
        this.fleche = fleche;
        this.area= new TextArea();

        this.sondage = sondage;

        this.questionActuelle = sondage.getListQ().get(0);

        this.comboMultiple = new ComboBox<>();

        this.boutonRefresh = boutonRefresh;

        this.boutonParametre = boutonParametre;
        this.valeurBouton = " ";

        this.slider = new Slider(0, this.questionActuelle.getMaxVal(), this.questionActuelle.getMaxVal()/2);

        BorderPane borderTop = borderPaneTop();
        VBox vdroite = VBoxDroite();
        BorderPane vMid = VBoxMidTextArea();
        
        
        this.setTop(borderTop);
        this.setCenter(vMid);
        this.setRight(vdroite);
        
    }
    
    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);

        Label ltitre = new Label("Titre sondage");

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("file:./IMG/user.jpg");
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

    public String getTextComboBoxMultiple() throws NullPointerException{
        try{
            return this.comboMultiple.getValue();
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
    }
    
    

    private BorderPane VBoxMidTextArea(){
        BorderPane vMid = new BorderPane();
        VBox vBot = new VBox();
        Label lquestion = new Label(""+this.questionActuelle.getTextQ());
        lquestion.setFont(Font.font(" Arial ",FontWeight.BOLD,24));
       

        TextArea treponse = this.area;
        
        BorderPane bot = this.fleche;
        BorderPane bottom = BorderPaneBot();
        treponse.setStyle("-fx-control-inner-background:#ffdab9;");

        vMid.getChildren().addAll(lquestion);
        
        switch(this.questionActuelle.getType()){
            case 'u' : vMid.getChildren().addAll(treponse,BorderBoutons());
            case 'm' : vMid.getChildren().addAll(treponse,comboBoxMultiple());
            case 'c' : vMid.getChildren().add(classementTile());
            case 'n' : vMid.getChildren().addAll(treponse,SliderMidSlider());
            case 'l' : vMid.getChildren().add(treponse);
        }
        
        
        
        vBot.getChildren().addAll(bot,new Label("\n"),bottom);
        vMid.setStyle("-fx-background-color:CORNSILK;");
        Insets arg3 = new Insets(20,20,20,20);
        vMid.setPadding(arg3);
        vMid.setTop(lquestion);
        BorderPane.setAlignment(lquestion, Pos.CENTER);
        vMid.setCenter(classementTile());
        vMid.setBottom(vBot);

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
    
    private ComboBox<String> comboBoxMultiple(){
        ComboBox<String> res = this.comboMultiple;
        for(String reponsePossible : this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ())){
            res.getItems().add(reponsePossible);
        }
        
        return res;
    }

    private TilePane classementTile(){
        TilePane res = new TilePane(Orientation.VERTICAL) ;
        
        for(int i=0; i< this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).size();i++){
            HBox reponse = new HBox();  
            Label lreponse = new Label();
            TextField tfreponse = new TextField();
            lreponse.setText(this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).get(i));

            Insets a = new Insets(10,5,10,10);
            lreponse.setPadding(a);
            reponse.getChildren().addAll(lreponse,tfreponse,new Label("  "));
            
            res.getChildren().addAll(reponse);
        
        }
        
        res.setAlignment(Pos.CENTER);

        return res;
    }
   
    private VBox SliderMidSlider(){
        
        VBox res = new VBox();
        Slider resS = this.slider;
        
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
    

    

    private VBox VBoxDroite(){
        VBox vDroite = new VBox();
        Label ltitre = new Label("Sommaire: ");
        vDroite.getChildren().addAll(ltitre);

        for(Question elem : BiblioSQL.getQuestionnaire(this.connexionSQL, sondage.getIdQ())){
            Button bouton =new Button(String.valueOf(elem.get(1)));
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
    



    // Ici commence les getteurs et setteurs 

    // Slider
    public double getSlider(){
        return this.slider.getValue();
    }
    public void setSlider(double valeur){
        this.slider.setValue(valeur);
    }
    
    // TexteArea
    public String getTextArea(){
        return this.area.getText();
    }
    public void setTextArea(String texte){
        this.area.setText(texte);
    }

    // Sondage
    public Questionnaire getSondage(){return this.sondage;}

    // Question
    public Question getQuestion(){ return this.questionActuelle;}

    public void setQuestion(Question que){this.questionActuelle=que;}

    // Reponse
    public char getTypeReponse(){return this.questionActuelle.getType();}

    //ComboBox 
    public String getValeurCombo(){return this.comboMultiple.getValue();}
    public void setValeurCombo(String valeur){this.comboMultiple.setValue(valeur);}

    // Le controleur set une valeur a cette variable
    // Bouton
    public void setValeurBouton(String val){this.valeurBouton=val;}
    public String getValeurBouton(){return this.valeurBouton;}
    
    

}
