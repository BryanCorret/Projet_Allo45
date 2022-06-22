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

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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

    //la lise des questions
    private Questionnaire questionnaire;
    private Question questionActuel;

    private TextArea commentaire;

    private BorderPane lesFleches;

    private appliSondage app;
    
    public FenetreAnalyste(Button boutonHome, Button boutonRefresh, Button boutonParametre, Questionnaire questionnaire, BorderPane lesFleches,appliSondage app){
        super();
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;
        this.boutonParametre = boutonParametre;
        this.boutonDonneeBrute = new Button("Données Brutes");
        this.boutonDonneeBrute.setStyle("-fx-background-color: MEDIUMBLUE;-fx-text-fill: white;");

        this.app = app;

        this.comboAnalyse = new ComboBox<>();
        this.comboClasse = new ComboBox<>();
        this.comboQuestion = new ComboBox<>();

        this.questionnaire = questionnaire;
        this.questionActuel = this.questionnaire.getListQ().get(0); //la première question

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
    public Question getQuestionActuel(){
        return this.questionActuel;
    }
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


    //les setteurs
    public void setQuestionActuel(Question question){
        this.questionActuel = question;
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

        Label titreGraphique = new Label("\n    " + this.questionActuel.getTextQ());
        titreGraphique.setWrapText(true); //retour à la ligne automatique

        /* si c'est un diagramme circulaire
     _   _                                                           _                     _          _             
  __| | (_)  __ _   __ _   _ _   __ _   _ __    _ __    ___     __  (_)  _ _   __   _  _  | |  __ _  (_)  _ _   ___ 
 / _` | | | / _` | / _` | | '_| / _` | | '  \  | '  \  / -_)   / _| | | | '_| / _| | || | | | / _` | | | | '_| / -_)
 \__,_| |_| \__,_| \__, | |_|   \__,_| |_|_|_| |_|_|_| \___|   \__| |_| |_|   \__|  \_,_| |_| \__,_| |_| |_|   \___|
                   |___/                                                                                            

        */
        if (this.getComboBoxAnalyse().getValue().equals("PieChart")){
            
            PieChart tarte = new PieChart();

            //on rempli le diagramme circulaire
            Map<String, Integer> dico = new HashMap<>();
            //pour chaque question du questionnaire
            for (Question quest : this.questionnaire.getListQ()){
                //pour chaque réponse
                for ( Reponse rep : BiblioSQL.getReponse(this.app.getConnexion(),this.questionnaire.getIdQ()) ){
                //ajoute la clé , 1 valeur par défaut   ou  incrémente la valeur de la clé de 1 
                    dico.merge(rep.getValue(), 1, Integer::sum);
                    // autre possibilité si cela ne fonctionne pas :
                    // dico.merge(rep.getValue(), 1, (a, b) -> a + b);
                }
                //on ajoute la légende du diagramme circulaire
                for (String reponse : dico.keySet()){
                    tarte.getData ().setAll(new PieChart.Data ( reponse, dico.get(reponse) ) );
                }   
            }

            tarte.setLegendSide (Side.RIGHT) ; // pour mettre la légende à droite

            vboxGraphique.getChildren().addAll(titreGraphique, tarte, this.lesFleches);
        }
        
        /* si c'est un diagramme à bandes
     _   _                                                               _                       _            
  __| | (_)  __ _   __ _   _ _   __ _   _ __    _ __    ___     __ _    | |__   __ _   _ _    __| |  ___   ___
 / _` | | | / _` | / _` | | '_| / _` | | '  \  | '  \  / -_)   / _` |   | '_ \ / _` | | ' \  / _` | / -_) (_-<
 \__,_| |_| \__,_| \__, | |_|   \__,_| |_|_|_| |_|_|_| \___|   \__,_|   |_.__/ \__,_| |_||_| \__,_| \___| /__/
                   |___/                                                                                      

         */
        
        else if (this.getComboBoxAnalyse().getValue().equals("BarChart")) {
            //axe X
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Couleur des lampadaires");
            //axe Y
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("age sondé");
    
            // on créer le diagramme à bandes
            BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

            //à continuer
 


            vboxGraphique.getChildren().addAll(titreGraphique, barChart, this.lesFleches);
        }
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
        this.comboAnalyse.getItems().addAll("Camembert", "Histogramme", "Pouet", "Machin");
        this.comboAnalyse.getSelectionModel().select(0);;
        this.comboClasse.getItems().addAll("Tout", "Sexe", "Age", "Pieds");
        this.comboClasse.getSelectionModel().select(0);;


        //on rempli la ComboBox avec les questions
        for (Question question : this.questionnaire.getListQ()){
            this.comboQuestion.getItems().add(question.getTextQ());
        }

        vboxHaute.getChildren().addAll(Parametre, typeAnalyse, comboAnalyse, typeClasses, comboClasse,new Label("\n"), comboQuestion,new Label("\n"), this.boutonDonneeBrute);
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

}
