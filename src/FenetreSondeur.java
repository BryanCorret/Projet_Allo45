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

    private AppliSondage main;
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
    private String choice;
    private int ide;
    private TextField classRes;
    private String resClassement;
    
  
    public FenetreSondeur(AppliSondage main,Button boutonHome,Button boutonRefresh,Button boutonParametre,Questionnaire sondage,ConnexionMySQL connexionSQL){
        super();
        this.init(main,boutonHome,boutonRefresh,boutonParametre,sondage,connexionSQL);
        this.maj(0);
    }
    public void init(AppliSondage main,Button boutonHome,Button boutonRefresh,Button boutonParametre,Questionnaire sondage,ConnexionMySQL connexionSQL){
        this.main = main;
        this.boutonHome = boutonHome;
        this.connexionSQL=connexionSQL;
        this.fleche = this.lesFleches();
        this.choice = "";
        this.ide = 0;
        this.sondage = sondage;
        this.resClassement = "";

        this.questionActuelle = sondage.getListQ().get(this.ide);
        this.resClassement = "";

        this.comboMultiple = new ComboBox<>();

        this.boutonRefresh = boutonRefresh;

        this.boutonParametre = boutonParametre;
        this.boutonParametre.setOnAction(new ControleurParam(this.main));
        this.valeurBouton = " ";

        this.slider = new Slider(0, this.questionActuelle.getMaxVal(), this.questionActuelle.getMaxVal()/2);

        BorderPane borderTop = borderPaneTop();
        VBox vdroite = VBoxDroite();
        BorderPane Bmid = VBoxMidTextArea();
        
        
        this.setTop(borderTop);
        this.setCenter(Bmid);
        this.setRight(vdroite);
    
    }
    public void maj(int id){
        this.fleche = this.lesFleches();
        this.questionActuelle = sondage.getListQ().get(id);
        this.comboMultiple = new ComboBox<>();
        this.valeurBouton = " ";
        this.slider = new Slider(0, this.questionActuelle.getMaxVal(), this.questionActuelle.getMaxVal()/2);
        BorderPane borderTop = borderPaneTop();
        VBox vdroite = VBoxDroite();
        BorderPane Bmid = VBoxMidTextArea();
        this.setTop(borderTop);
        this.setCenter(Bmid);
        this.setRight(vdroite);
    }
    public void setIde(int o){
        this.ide = o;
    }
    public int getIde(){
        return this.ide;
    }
    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh);

        Label ltitre = new Label(this.sondage.getTitreQ());

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("file:./IMG/user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);
       
  
        hID.getChildren().addAll(profil,this.boutonParametre);

        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,32));
        border.setStyle("-fx-background-color:#5D6D7E ;");
        ltitre.setStyle("-fx-text-fill: white;");
        
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
        BorderPane res = new BorderPane();
        VBox vMid = new VBox();
        VBox vBot = new VBox();
        Label lquestion = new Label(""+this.questionActuelle.getTextQ());
        lquestion.setFont(Font.font(" Arial ",FontWeight.BOLD,24));
        lquestion.setStyle("-fx-text-fill: white;");
            
    
            
        this.area = new TextArea();
            
        BorderPane bot = this.fleche;
        BorderPane bottom = BorderPaneBot();
        this.area.setStyle("-fx-control-inner-background:#ffdab9;");
    
        vMid.getChildren().addAll(new Label("\n"),new Label("\n"),new Label("\n"));
            
        System.out.println(this.questionActuelle.getType());
        switch(this.questionActuelle.getType()){
            case 'u' : vMid.getChildren().add(BorderBoutons()); break;
            case 'm' : vMid.getChildren().add(comboBoxMultiple());break;
            case 'c' : vMid.getChildren().add(classementTile());break;
            case 'n' : vMid.getChildren().add(SliderMidSlider());break;
            case 'l' : vMid.getChildren().add(this.area);break;
        }
            
            
            
        vBot.getChildren().addAll(bot,new Label("\n"),bottom);
        res.setStyle("-fx-background-color:#CACFD2;");
        Insets arg3 = new Insets(40,20,20,20);
        vMid.setPadding(arg3);
        res.setTop(lquestion);
        BorderPane.setAlignment(lquestion, Pos.CENTER);
        res.setCenter(vMid);
        res.setBottom(vBot);
        return res;
    }
    private HBox BorderBoutons(){
        HBox boutons = new HBox();
        
        // img.setFitHeight(35);img.setFitWidth(35);
        for(String elem: this.questionActuelle.getValeursPossible(this.connexionSQL, this.sondage.getIdQ())){
            Button b1 = new Button(elem);
            b1.setOnAction(new ControleurReponseBouton(this));
            b1.setPrefWidth(100);
            b1.setStyle("-fx-background-color:#678466;-fx-text-fill: white;");
            boutons.getChildren().add(b1);
        }
        boutons.getChildren().add(new Text(this.choice));
        boutons.setSpacing(60);
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

    private BorderPane classementTile(){
        BorderPane bfinal = new BorderPane();
        TilePane res = new TilePane(Orientation.VERTICAL) ;
        
        // for(int i=0; i< this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).size();i++){
        //     HBox reponse = new HBox();  
        //     Label lreponse = new Label();
        //     TextField tfreponse = new TextField();
        //     lreponse.setText(this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).get(i));

        //     Insets a = new Insets(10,5,10,10);
        //     lreponse.setPadding(a);
        //     reponse.getChildren().addAll(lreponse,tfreponse,new Label("  "));
            
        //     res.getChildren().addAll(reponse);
        
        // }
        
        List<String> valeursPossibles = new ArrayList<>();
        valeursPossibles = this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ());
        for(int i=0;i< valeursPossibles.size();i++){
            HBox reponse = new HBox();  
            Label lreponse = new Label();
            
            lreponse.setText(this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).get(i));
            this.setValeurBouton(this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).get(i));

            lreponse.setText(this.questionActuelle.getValeursPossible(this.connexionSQL,sondage.getIdQ()).get(i));
            Insets a = new Insets(10,5,10,10);
            lreponse.setPadding(a);
            reponse.getChildren().addAll(new Label((String.valueOf(i)), lreponse));
            res.getChildren().add(reponse);
        }
        
        
        res.setAlignment(Pos.CENTER);
        VBox vbox = new VBox();

        Label instruction = new Label("Entrez dans le champs ci-dessous les 3 indices des réponses dans l'ordre choisit, Ex: 1,2,3 ou 1 2 3");
        instruction.setFont(Font.font(" Arial ",FontWeight.BOLD,15));
        
        this.classRes = new TextField();
        this.resClassement += this.classRes.getText();

        vbox.getChildren().addAll(instruction,this.classRes);
        bfinal.setCenter(res);
        bfinal.setBottom(vbox);
        return bfinal;
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
        Label nomSonde = new Label("Nom: "+this.main.getSondeActu().getNomSond());
        Label prenomSonde = new Label("Prenom: "+this.main.getSondeActu().getPrenomSond());
        Label numSonde = new Label("numero du Sonde: " +String.valueOf(this.main.getSondeActu().getNumSond()));
        Label telSonde = new Label(this.main.getSondeActu().getTelephoneSond());
          
        Label ltitre = new Label(" Sommaire: ");
        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,18));
        nomSonde.setStyle("-fx-text-fill: white;");
        numSonde.setStyle("-fx-text-fill: white;");
        prenomSonde.setStyle("-fx-text-fill: white;");
        numSonde.setStyle("-fx-text-fill: white;");
        telSonde.setStyle("-fx-text-fill: white;");
        ltitre.setStyle("-fx-text-fill: white;");
    
        vDroite.getChildren().addAll(numSonde,new Label("\n"),nomSonde,new Label("\n"),prenomSonde,new Label("\n"),telSonde,new Label("\n"),ltitre);

        for(Question elem : BiblioSQL.getQuestionQuestionnaire(this.connexionSQL, sondage.getIdQ())){
            Label bouton =new Label((String) elem.getTextQ());
            bouton.setStyle("-fx-background-color:transparent;");
            bouton.setStyle("-fx-text-fill: white;");
            
            vDroite.getChildren().add(bouton);
        }
          
        vDroite.setStyle("-fx-background-color:#7F8C8D  ;");
        return vDroite;
    }
    
    private BorderPane BorderPaneBot(){
        BorderPane res = new BorderPane();
        Button resultats = new Button("Resultats");
        Button Valider = new Button("Valider");
        Valider.setOnAction(new ControleurValiderReponse(this, this.main, this.main.getConnexion(), this.main.getSondeActu(), this.main.getUtilisateur()));
        
        res.setLeft(resultats);
        res.setRight(Valider);
        // while(this.getIde()<this.getSondage().getListQ().size()-1){
        //     Valider.setDisable(false);
        // }
        System.out.println(this.getSondage().getListQ().size()-1);
        Valider.setDisable(true);
        System.out.println(this.questionActuelle.getNumQ()-4);
        
        if(this.questionActuelle.getNumQ()==this.getSondage().getListQ().size()){
            Valider.setDisable(false);
        }
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
    public char getTypeReponse(){
        if(this.ide == this.sondage.getListQ().size()){
            return sondage.getListQ().get(0).getType();
        }
            return sondage.getListQ().get(this.ide-1).getType();
    }

    //ComboBox 
    public String getValeurCombo(){return this.comboMultiple.getValue();}
    public void setValeurCombo(String valeur){this.comboMultiple.setValue(valeur);}
    public void addRep(){
        ControleurValiderReponse temp = new ControleurValiderReponse(this, this.main, this.main.getConnexion(), this.main.getSondeActu(), this.main.getUtilisateur());
        temp.addRepSet();
    }
    //Classement
    public String getClassement(){
        // List<String> res = new ArrayList<>();
        // String [] newStr ;
        // if(this.resClassement.contains(" ")){ newStr =this.resClassement.split("\\s+");}
        // else{ newStr =this.resClassement.split("\\,");}
        // for(String elem : newStr){
        //     res.add(elem);
        // }
        // return res;
        return this.classRes.getText();
    }


    // Le controleur set une valeur a cette variable
    // Bouton
    public void setValeurBouton(String val){this.valeurBouton=val;}
    public String getValeurBouton(){return this.valeurBouton;}
    
    public BorderPane lesFleches(){
        //les flèches
        BorderPane bpFleche = new BorderPane();
        ImageView imgFlecheGauche = new ImageView("file:IMG/fleche.png");
        ImageView imgFlecheDroite = new ImageView("file:IMG/fleche.png");
        imgFlecheDroite.setRotate(180.0);
        imgFlecheGauche.setFitHeight(40);imgFlecheGauche.setFitWidth(40);
        imgFlecheDroite.setFitHeight(40);imgFlecheDroite.setFitWidth(40);

        Button boutonFlecheGauche = new Button("", imgFlecheGauche);
        Button boutonFlecheDroite = new Button("", imgFlecheDroite);
        //cache la partie visible des boutons
        // boutonFlecheGauche.setStyle("-fx-background-color:transparent;");
        // boutonFlecheDroite.setStyle("-fx-background-color:transparent;");

        //pour les différencier dans le Controlleur Fleche
        boutonFlecheGauche.setId("flecheGauche");
        boutonFlecheDroite.setId("flecheDroite");

        boutonFlecheDroite.setOnAction(new ControleurFleche(this.main,this));
        boutonFlecheGauche.setOnAction(new ControleurFleche(this.main,this));
       
        bpFleche.setRight(boutonFlecheDroite);
        bpFleche.setLeft(boutonFlecheGauche);
        return bpFleche;
    }

    public void setTexteChoice(String choix){
        this.choice = choix;
    }
    public String getTextChoice(){
        System.out.println(this.choice);
        return this.choice;
    }
}
