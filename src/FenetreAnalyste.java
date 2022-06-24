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
import javafx.scene.chart.*;
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

import java.util.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*; 
import javafx.geometry.*;



public class FenetreAnalyste extends BorderPane{

    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonParametre;
    private Button boutonDonneeBrute;

    private ComboBox<String> comboAnalyse;
    private ComboBox<String> comboClasse;
    private ComboBox<String> comboQuestion;
    private ComboBox<String> comboRep;

    //la liste des questions
    private Questionnaire questionnaire;

    private TextArea commentaire;

    private BorderPane lesFleches;

    private AppliSondage app;
    private String Sondageactu;
    public FenetreAnalyste(Button boutonHome, Button boutonRefresh, Button boutonParametre, Questionnaire questionnaire, BorderPane lesFleches,AppliSondage app, ComboBox<String> cbTypediag, ComboBox<String> cbTri,ComboBox<String> rep,ComboBox<String> questions){
        super();
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;
        this.boutonParametre = boutonParametre;
        this.boutonDonneeBrute = new Button("Données Brutes");
        this.boutonDonneeBrute.setStyle("-fx-background-color: MEDIUMBLUE;-fx-text-fill: white;");

        this.app = app;

        this.comboAnalyse = cbTypediag;
        //this.comboAnalyse.setOnAction(new ControleurChoixDiagramme(this));
        this.comboClasse = cbTri;
        this.comboQuestion = questions;
        this.comboRep = rep;
        this.Sondageactu = questionnaire.getTitreQ();
        this.questionnaire = questionnaire;
        

        this.commentaire = new TextArea();

        this.lesFleches = lesFleches;


        //on créer notre fenêtre
        
        //top
        this.setTop(topBorderPane());

        //center
        this.setCenter(centerVbox());

        //Left
        this.setLeft(leftVBox());

    }

    //les getteurs
    public Questionnaire getQuesionnaire(){
        return this.questionnaire;
    }
    public TextArea getCommentaire(){
        return this.commentaire;
    }
    
    public ComboBox<String>  getComboBoxAnalyse() {
        return this.comboAnalyse;

    }
    public ComboBox<String>  getComboBoxClasse() {
        return this.comboClasse;
    }
    public ComboBox<String> getComboBoxQuestion() {
        return this.comboQuestion;
    }


    public void setCommentaire(String comment){
        this.commentaire.setText(comment);
    }


    // les méthodes pour découper la création de la fenetre
    public BorderPane topBorderPane(){

        BorderPane bp = new BorderPane();

        HBox hboxBoutons = new HBox();
        hboxBoutons.getChildren().addAll(this.boutonHome,this.boutonRefresh);


        Label titreSondage = new Label(this.questionnaire.getTitreQ());
        titreSondage.setFont(Font.font(" Arial ",FontWeight.BOLD,15));

        HBox hboxAvatar = new HBox();

        ImageView profil = new ImageView("file:IMG/user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);

        hboxAvatar.getChildren().addAll(profil, this.boutonParametre, new Label(""));

        bp.setStyle("-fx-background-color:#587f264b;");

        bp.setLeft(hboxBoutons);
        bp.setCenter(titreSondage);
        bp.setRight(hboxAvatar);

        return bp;
    }
    
    public VBox centerVbox(){
        
        //la grande vbox
        VBox vbox = new VBox();
        

        //la partie graphique
        VBox vboxGraphique = new VBox();

        PieChart Circulaire = this.app.getPieChart();
        Label titreGraphique = new Label("\n    " + this.comboQuestion.getValue());
        titreGraphique.setWrapText(true); //retour à la ligne automatique
            // pour mettre la légende à droite
        Circulaire.setLegendSide(Side.LEFT);
        // vbox.getChildren().add(this.sondage.createPieChart);


        
        // else if (Sondageactu.equals("Histogramme")){
        // final CategoryAxis xAxis = new CategoryAxis();
        // final NumberAxis yAxis = new NumberAxis();
        // final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        // bc.setTitle("Histograme Réponse");
        // xAxis.setLabel("Country");       
        // yAxis.setLabel("Value");
 
        // XYChart.Series series1 = new XYChart.Series();
        // XYChart.Series series2 = new XYChart.Series();
        // XYChart.Series series3 = new XYChart.Series();
        
        // series1.setName("R1");
        // series2.setName("R2"); 
        // series3.setName("R3"); 

        // series1.getData().add(new XYChart.Data("OUI", 12));
        
        // series2.getData().add(new XYChart.Data("NON", 11)); 
        
        // series3.getData().add(new XYChart.Data("Ne sais pas", 3));      

 

        // System.out.println("Histograme");
        
        


        vboxGraphique.getChildren().addAll(titreGraphique,Circulaire,this.lesFleches); // ,this.lesFleches
        vboxGraphique.setBackground(new Background(new BackgroundFill(Color.GAINSBORO,CornerRadii.EMPTY, Insets.EMPTY)));



        //la partie commentaire
        Label titreCommentaire = new Label("        Commentaire");
        this.commentaire.setWrapText(true);
        this.commentaire.setPrefHeight(100.0); // taille maximal

        //on ajoute tout
        vbox.getChildren().addAll(vboxGraphique, titreCommentaire, commentaire);

        vbox.setPadding(new Insets(5,5,0,5));
        
        return vbox;
        }
    public VBox leftVBox(){

        //une grande vbox contenant deux vbox
        VBox vbox = new VBox();

        VBox vboxHaute = new VBox();
        VBox vboxBasse = new VBox();


        //la vbox en haut
        Text Parametre = new Text("Paramètres");
        Parametre.setFont(Font.font( "Arial", FontWeight.BOLD,20));

        Text typeAnalyse = new Text("\nType D'analyse");
        Text typeClasses = new Text("\nTri par Classes");


        //on rempli les ComboBox
        // this.comboAnalyse.getItems().addAll("Circulaire", "Histogramme", "Pouet", "Machin");
        // this.comboAnalyse.getSelectionModel().select(0);

        // this.comboClasse.getItems().addAll("Tout", "Sexe", "Age", "Pieds");
        // this.comboClasse.getSelectionModel().select(0);


        // //on rempli la ComboBox avec les questions
        // for (Question question : this.questionnaire.getListQ()){
        //     this.comboQuestion.getItems().add(question.getTextQ());
        // }
        // this.comboQuestion.getSelectionModel().select(0);


        vboxHaute.getChildren().addAll(Parametre, typeAnalyse, this.comboAnalyse, typeClasses, this.comboClasse,this.comboRep,new Label("\n"), this.comboQuestion,new Label("\n"), this.boutonDonneeBrute);
        vboxHaute.setBackground(new Background(new BackgroundFill(Color.GAINSBORO,CornerRadii.EMPTY, Insets.EMPTY)));
        vboxHaute.setAlignment(Pos.CENTER);

        //la vbox en bas
        Text titreDonne = new Text("Données Autres\n");
        titreDonne.setFont(Font.font("Arial", FontWeight.BOLD,16));
        
        TextArea zoneDonne = new TextArea("La secrétaire était très gentille. Le reste était assez moyen");
        zoneDonne.setWrapText(true); //retour à la ligne automatique
        zoneDonne.setPrefWidth(150.0); // taille maximal
        zoneDonne.setEditable(false); //empêche la modification du texte

        vboxBasse.getChildren().addAll(titreDonne, zoneDonne);
        vboxBasse.setBackground(new Background(new BackgroundFill(Color.GAINSBORO,CornerRadii.EMPTY, Insets.EMPTY)));


        //on ajoute les deux petites vbox à la grande vbox
        vbox.getChildren().addAll(vboxHaute, new Label("\n"), vboxBasse);
        vbox.setPadding(new Insets(0,5,5,5));
        return vbox;
    }
    public void majAffichageDiagrame(){
        this.setCenter(centerVbox());
    }
    public void setSondeActu(String sondage){
        this.Sondageactu = sondage;
    }

    public ComboBox<String> getComboAnalyse() {
        return comboAnalyse;
    }
}
