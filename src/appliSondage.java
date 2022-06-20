import javafx.application.Application;
import javafx.scene.Scene;
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
import javafx.geometry.Pos;

public class appliSondage extends Application{

    private Button boutonConnexion;

    private Button boutonAnalyste;

    private Button boutonSondeur;

    private Button boutonDonneesBrutes;

    private Button boutonHome;

    private Button boutonDeconnexion;

    private Button boutonInscription;

    private Scene scene;

    @Override
    public void init(){
        this.boutonConnexion = new Button("Se connecter");
        this.boutonAnalyste = new Button("Analyser les sondage");
        this.boutonSondeur = new Button("Sélectionner");
        this.boutonDonneesBrutes = new Button("Données Brutes");
        this.boutonHome = new Button(); //image home
        this.boutonDeconnexion = new Button("Se déconnecter");
        this.boutonInscription = new Button("S'inscrire");

        ControleurChangementFenetre windowSwitcher = new ControleurChangementFenetre(this);
        RetourHome controlHome = new RetourHome(this);

        this.boutonDeconnexion.setOnAction(windowSwitcher);
        this.boutonInscription.setOnAction(windowSwitcher);
        this.boutonConnexion.setOnAction(windowSwitcher);
        this.boutonAnalyste.setOnAction(windowSwitcher);
        this.boutonSondeur.setOnAction(windowSwitcher);
        this.boutonDonneesBrutes.setOnAction(windowSwitcher);
        this.boutonHome.setOnAction(controlHome);

    }
    @Override
    public void start(Stage stage){
        Pane root = new FenetreConnexion(this.btnConnexion);
        this.scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Allo45");
        stage.show();
    }

    public void modeAnalyste(){
        Pane root = new FenetreAnalyste(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeDonneesBrutes(){
        Pane root = new FenetreDonneesBrutes(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeSondeur(){
        Pane root = new FenetreSondeur(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeConnexion(){
        Pane root = new FenetreConnexion(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeInscription(){
        Pane root = new FenetreInscription(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeParametreAnalyste(){
        Pane root = new FenetreParametreAnalyste(this.btnConnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}
