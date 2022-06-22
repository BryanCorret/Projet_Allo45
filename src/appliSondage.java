import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.List;
import java.util.ArrayList;

public class appliSondage extends Application{


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

    private BorderPane fleches;

    private Questionnaire sondageSelectionne;

    private String fenetreActu;

    private Scene scene;

    private Utilisateur utilisateurActu;

    private Sonde sondeActu;

  

    private ComboBox<String> cbTypediag;

    private ComboBox<String> cbTri;

    @Override
    public void init(){
        this.ConnexionSQL = BiblioSQL.connectRoot();
        this.cbTypediag = new ComboBox<>();
        this.cbTypediag.getItems().addAll("Circulaire","Courbes","Bâtons");
        this.cbTri = new ComboBox<>();
        this.cbTri.getItems().addAll("Femmes","Hommes","Tous");
        this.boutonAnalyste = new Button("Analyser les sondage");
        this.boutonSondeur = new Button("Sélectionner");
        this.boutonDonneesBrutes = new Button("Données Brutes");
        this.boutonHome = new Button(); //image home
        this.boutonParam = new Button(); //image Param
        this.boutonRefresh = new Button(); //image f5
        this.boutonDeconnexion = new Button(); //image deconnexion
        this.boutonConnexion = new Button();
        this.boutonInscription = new Button();
        this.fleches = null;

        ImageView home = new ImageView("file:IMG/home.png");
        ImageView refresh = new ImageView("file:IMG/reload.png");
        ImageView deco = new ImageView("file:IMG/Disconnect.png");
        ImageView param = new ImageView("file:IMG/parametre.png");
        home.setFitHeight(30);home.setFitWidth(30);
        refresh.setFitHeight(30);refresh.setFitWidth(30);
        deco.setFitHeight(40);deco.setFitWidth(50);
        param.setFitHeight(40);param.setFitWidth(40);
        boutonRefresh.setPrefHeight(45); boutonRefresh.setPrefWidth(55);
        boutonHome.setPrefHeight(45); boutonHome.setPrefWidth(55);

        this.boutonHome.setGraphic(home);
        this.boutonRefresh.setGraphic(refresh);
        this.boutonDeconnexion.setGraphic(deco);
        this.boutonParam.setGraphic(param);
        this.boutonParam.setStyle("-fx-background-color:transparent;");
        this.boutonHome.setStyle("-fx-background-color:transparent;");
        this.boutonDeconnexion.setStyle("-fx-background-color:transparent;");
        this.boutonRefresh.setStyle("-fx-background-color:transparent;");

        ControleurHome windowSwitcher = new ControleurHome(this);

        this.boutonDeconnexion.setOnAction(new ControleurDeconnexion(this));
        this.boutonInscription.setOnAction(windowSwitcher);
        this.boutonConnexion.setOnAction(windowSwitcher);
        this.boutonAnalyste.setOnAction(windowSwitcher);
        this.boutonSondeur.setOnAction(windowSwitcher);
        this.boutonDonneesBrutes.setOnAction(windowSwitcher);
        this.boutonHome.setOnAction(windowSwitcher);
        this.boutonParam.setOnAction(windowSwitcher);
        this.boutonRefresh.setOnAction(new ControleurRefresh(this));

    }
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

        if(this.fenetreActu == "Sondeur"){ 
            boutonFlecheDroite.setOnAction(new ControleurFleche(this,(FenetreSondeur)this.scene.getRoot()));
            boutonFlecheGauche.setOnAction(new ControleurFleche(this,(FenetreSondeur)this.scene.getRoot()));
        }
        if(this.fenetreActu == "Analyste"){ 
            boutonFlecheDroite.setOnAction(new ControleurFleche(this,(FenetreAnalyste)this.scene.getRoot()));
            boutonFlecheGauche.setOnAction(new ControleurFleche(this,(FenetreAnalyste)this.scene.getRoot()));
        }
       
        bpFleche.setRight(boutonFlecheDroite);
        bpFleche.setLeft(boutonFlecheGauche);
        return bpFleche;
    }

    @Override
    public void start(Stage stage){
        Pane root = new FenetreConnexion(this);
        this.scene = new Scene(root);
        this.fenetreActu = "Connexion";
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:IMG/user.jpg"));
        stage.setTitle("Allo45");
        stage.show();
    }

    public void modeAnalyste(){
        this.fenetreActu = "Analyste";
        Pane root = new FenetreAnalyste(this.boutonHome,this.boutonRefresh,this.boutonParam,this.sondageSelectionne,this.fleches,this);
        // ,this.cbTypediag,this.cbTri
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }
    
    public void modeHomeSondeur(){
        this.fenetreActu = "HomeSondeur";
        Pane root = new FenetreHomeSondeur(this,this.boutonHome,this.boutonRefresh,this.boutonDeconnexion);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeHomeAnalyste(){
        this.fenetreActu = "HomeAnalyste";
        Pane root = new FenetreHomeAnalyste(this.boutonHome,this.boutonRefresh,this.boutonDeconnexion,this.boutonParam,this);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }
/**
    public void modeDonneesBrutes(){
        this.fenetreActu = "Donnees";
        Pane root = new FenetreDonneesBrutes(this.boutonHome, this.boutonRefresh, this.boutonDeconnexion,this.boutonParam, this); //fenetre pas encore faite
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }
    */

     public void modeSondeur(){
         this.fenetreActu = "Sondeur";
         System.out.println(this.sondageSelectionne);
         Pane root = new FenetreSondeur(this,this.boutonHome,this.boutonRefresh,this.boutonParam,this.sondageSelectionne,this.ConnexionSQL); //fenetre pas encore faite
         this.scene.setRoot(root);
         root.getScene().getWindow().sizeToScene(); //redimensionne le root à la place nécéssaire à l'affichage de l'appli
     }
    

    public void modeConnexion(){
        this.fenetreActu = "Connexion";
        Pane root = new FenetreConnexion(this);
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }

    public void modeInscription(){
        this.fenetreActu = "Inscription";
        Pane root = new FenetreInscription(this);
        System.out.println(this.scene);
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
    public Questionnaire getSondage(){
        return this.sondageSelectionne;
    }
    public void setSondeActu(Sonde sonde){
        this.sondeActu = sonde;
    }
    public Sonde getSondeActu(){
        return this.sondeActu;
    }

    public void majAffichageAnalyste(){

    }

    public void EnregistrerReponses(Reponse r){
        BiblioSQL.setReponse(this.ConnexionSQL, r, this.sondeActu, this.utilisateurActu);
    }

    public void majAffichageSondeur(){
    }

    
    public void setUtilisateur(Utilisateur u){
        this.utilisateurActu = u;
    }
    
    public Utilisateur getUtilisateur(){
        return this.utilisateurActu;
    }
    
    public List<String> rechercherSondage(String StrRecherche){
        List<String> listeDesSondages = new ArrayList<String>();
        return listeDesSondages;
    }
    
    public ConnexionMySQL getConnexion(){
        return this.ConnexionSQL;
    }
    public int getUserRole(){
        return this.utilisateurActu.getIdRole();
    }
    public void quitter(){
        Platform.exit();
    }
    

    public void setSondageSelectionne(Questionnaire sondageSelectionne) {
        this.sondageSelectionne = sondageSelectionne;
    }
    /**
    public PieChart createPieChart(int id, List<Reponse> lReponses){ // id de la question
        int sommetotReponse = 0;
        PieChart Circulaire = new PieChart();
        for (Reponse r : lReponses) { // somme de toute les reponses
            // sommetotReponse += BiblioSQL.getNbReponse(id,r.toString());
        }

        for (Reponse r : lReponses) { // somme de toute les reponses
            Circulaire.getData().setAll (
            // new PieChart.Data (r.toString(), BiblioSQL.getNbReponse(id,r.toString())/sommetotReponse)
            );
        }

        return Circulaire;

    }
    */

    
    //public PieChart createBarchar(int id, List<Reponse> lReponses){ // id de la question
    

    //}



    public static void main(String[] args){
        Application.launch(args);
    }

}
