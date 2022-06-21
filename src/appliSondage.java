import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.chart.Chart;

public class appliSondage extends Application{

    private RequetesSQL lesRequetes;

    private ConnexionMySQL ConnexionSQL;

    private Button boutonConnexion;

    private Button boutonAnalyste;

    private Button boutonSondeur;

    private Button boutonDonneesBrutes;

    private Button boutonHome;

    private Button boutonDeconnexion;

    private Button boutonInscription;

    private Button boutonParam;

    private Button boutonRefresh;

    private String sondageSelectionne;

    private String fenetreActu;

    private Scene scene;

    @Override
    public void init(){
        this.ConnexionSQL = new ConnexionMySQL();
        this.lesRequetes = new RequetesSQL(this.ConnexionSQL);
        this.boutonAnalyste = new Button("Analyser les sondage");
        this.boutonSondeur = new Button("Sélectionner");
        this.boutonDonneesBrutes = new Button("Données Brutes");
        this.boutonHome = new Button(); //image home
        this.boutonParam = new Button(); //image Param
        this.boutonRefresh = new Button(); //image f5
        this.boutonDeconnexion = new Button(); //image deconnexion
        ImageView home = new ImageView("./home.png");
        ImageView refresh = new ImageView("./reload.png");
        ImageView deco = new ImageView("./Disconnect.png");
        ImageView param = new ImageView("./parametre.jpg");
        home.setFitHeight(30);home.setFitWidth(30);
        refresh.setFitHeight(30);refresh.setFitWidth(30);
        deco.setFitHeight(30);deco.setFitWidth(30);
        param.setFitHeight(30);deco.setFitWidth(30);
        this.boutonHome.setGraphic(home);
        this.boutonRefresh.setGraphic(refresh);
        this.boutonDeconnexion.setGraphic(deco);
        this.boutonParam.setGraphic(param);
        this.boutonParam.setStyle("-fx-background-color:transparent;");
        this.boutonHome.setStyle("-fx-background-color:transparent;");
        this.boutonConnexion.setStyle("-fx-background-color:transparent;");
        this.boutonDeconnexion.setStyle("-fx-background-color:transparent;");

        ControleurChangementFenetre windowSwitcher = new ControleurChangementFenetre(this);
        RetourHome controlHome = new RetourHome(this);

        this.boutonDeconnexion.setOnAction(windowSwitcher);
        this.boutonInscription.setOnAction(windowSwitcher);
        this.boutonConnexion.setOnAction(windowSwitcher);
        this.boutonAnalyste.setOnAction(windowSwitcher);
        this.boutonSondeur.setOnAction(windowSwitcher);
        this.boutonDonneesBrutes.setOnAction(windowSwitcher);
        this.boutonHome.setOnAction(controlHome);
        this.boutonParam.setOnAction(windowSwitcher);
        this.boutonRefresh.setOnAction(new ControleurRefresh(this));

    }

    @Override
    public void start(Stage stage){
        Pane root = new FenetreConnexion();
        this.scene = new Scene(root);
        this.fenetreActu = "Connexion";
        stage.setScene(scene);
        stage.setTitle("Allo45");
        stage.show();
    }

    public void modeAnalyste(){
        this.fenetreActu = "Analyste";
        Pane root = new FenetreAnalyste(this.boutonHome,this.boutonParam,this.boutonRefresh,this.sondageSelectionne);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeHome(){
        this.fenetreActu = "Home";
        Pane root = new FenetreHome(this.boutonHome,this.boutonRefresh,this.boutonDeconnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }


    public void modeDonneesBrutes(){
        this.fenetreActu = "Donnees";
        Pane root = new FenetreDonneesBrutes(this.btnConnexion); //fenetre pas encore faite
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeSondeur(){
        this.fenetreActu = "Sondeur";
        Pane root = new FenetreSondeur(this.boutonHome,this.boutonRefresh,this.boutonParam); //fenetre pas encore faite
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene(); //redimensionne le root à la place nécéssaire à l'affichage de l'appli
    }

    public void modeConnexion(){
        this.fenetreActu = "Connexion";
        Pane root = new FenetreConnexion();
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeInscription(){
        this.fenetreActu = "Inscription";
        Pane root = new FenetreInscription();
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeParametreAnalyste(){
        this.fenetreActu = "ParamAnalyste";
        Pane root = new FenetreParametreAnalyste(this.boutonHome,this.boutonRefresh);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void remplirSondage(Questionnaire q){
        this.sondageSelectionne = q;
    }
    public String getFenetreActu(){
        return this.fenetreActu;
    }

    public void majAffichageAnalyste(){

    }

    public void EnregistrerReponses(){

    }

    public void majAffichageSondeur(){

    }
    
    public List<String> rechercherSondage(String StrRecherche){
        List<String> listeDesSondages = new ArrayList<String>();
        return listeDesSondages;
    }

    public void quitter(){
        Platform.exit();
    }

    // public Chart Diagrammes(){
        
    // }
*/
    public static void main(String[] args){
        Application.launch(args);
    }

}
