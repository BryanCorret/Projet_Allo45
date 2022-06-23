import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;

import java.util.*;

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

    private ComboBox<String> cbTriCat;

    private ComboBox<String> cbTypediag;

    private ComboBox<String> cbTri;

    private ComboBox<String> cbTriTypeRep;

    @Override
    public void init(){
        this.ConnexionSQL = BiblioSQL.connectRoot();
        this.cbTypediag = new ComboBox<>();
        this.cbTypediag.getItems().addAll("Circulaire","Courbes","Bâtons");
        this.cbTri = new ComboBox<>();
        this.cbTri.getItems().addAll("","","Tous");
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
        Pane root = new FenetreAnalyste(this.boutonHome,this.boutonRefresh,this.boutonParam,this.sondageSelectionne,this.fleches,this,this.cbTypediag,this.cbTri);
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

    public void modeDonneesBrutes(){
        this.fenetreActu = "Donnees";
        Pane root = new FenetreDonneesBrutes(this.boutonHome, this.boutonRefresh, this.boutonDeconnexion,this.boutonParam, this); //fenetre pas encore faite
        this.scene.setRoot(root);
        root.getScene().getWindow().sizeToScene();
    }
    

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

    public List<Double> tailleChart(HashMap<String,List<Reponse>> lReponses){
        List<Double> res = new ArrayList<>();
        int cpt = 0;
        double laValeur = 0;
        List<Reponse> repDifferentes = lesReponsesDifferentes(lReponses);
        List<Integer> valbrutes = new ArrayList<>();
        for (List<Reponse> valeur: lReponses.values()){
            cpt+= valeur.size();
        }
        for (Map.Entry<String, List<Reponse>> entry : lReponses.entrySet()) { 
            String key = entry.getKey();
            List<Reponse> value = entry.getValue();
            laValeur = value.size()/cpt;
            res.add(laValeur*100.0);
    }
    return res;
}
    public Integer nbChaqueReponses(Reponse lReponses,Question question,String tri){
            if (tri=="idTr"){
            return BiblioSQL.getOccurenceReponseDansQuestionPourCarac(this.getConnexion(), this.sondageSelectionne.getIdQ(), question.getNumQ(), lReponses.getValue(),Integer.valueOf(lReponses.getidC().charAt(1)));
            }
            else if(tri=="idCat"){
                return BiblioSQL.getOccurenceReponseDansQuestionPourCarac(this.getConnexion(), this.sondageSelectionne.getIdQ(), question.getNumQ(), lReponses.getValue(),String.valueOf(lReponses.getidC().charAt(2)));
            }
            return -1;
    }
public List<Reponse> lesReponsesDifferentes(HashMap<String,List<Reponse>> lReponses){
    List<Reponse> lrep = new ArrayList<>();
    for (List<Reponse> valeur: lReponses.values()){
            for(Reponse r:valeur){
                if(!(lrep.contains(r))){
                    lrep.add(r);
                }
            }
    }
    return lrep;
}
    
    public PieChart createPieChart(HashMap<String,List<Reponse>> lReponses,Question question,String tri){ 
        int rep = 0;
        Integer occRep;
        PieChart circulaire = new PieChart();
        circulaire.setTitle(question.getTextQ());
        for (Map.Entry<String, List<Reponse>> entry : lReponses.entrySet()) { 
            String key = entry.getKey();
            List<Reponse> value = entry.getValue();
            for (Reponse r:value){
                occRep = nbChaqueReponses(r, question,tri);
                circulaire.getData().add(new PieChart.Data(key,occRep));
            }
        }

        return circulaire;

    }
    

    
    public BarChart<String, Number> createBarchar(HashMap<String, List<Reponse>> lReponses , Question question, String caracteristique){

        //si on veut analyser tout ou une partie des sondés
        boolean tout = true;
        if (!caracteristique.equals("null"))
            tout = false;
        
        //on créer le barChart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        if (question.getType() == 'c'){
            //on met un titre seulement si c'est un classement
            xAxis.setLabel("Note");       
            yAxis.setLabel("Nb de réponse");
        }
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle("BarChart this.getNameQuestion(id)");
        
        

            //pour chaque clé et ses données associées
            for (Map.Entry<String, List<Reponse>> entry : lReponses.entrySet()) { 
                String key = entry.getKey();
                List<Reponse> value = entry.getValue();

                //si on a trouvé la caracteristique recherché
                if (tout || key.equals(caracteristique)){

                    //on créer un dictionnaire de la forme <La réponse, fréquence de laréponse>
                    Map<String, Integer> dico = new HashMap<>();
                    for (Reponse rep : value){
                        //on ajoute la clé , 1 valeur par défaut   ou  incrémente la valeur de la clé de 1 
                        dico.merge(rep.getValue(), 1, Integer::sum);
                    }

                    //on fait une série selon la caracteristique
                    XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
                    for (Reponse rep : value){
                        serie.getData().add(new XYChart.Data<String, Number>(rep.getValue(), dico.get(rep.getValue())));
                    }

                    //on ajoute la série
                    barChart.getData().add(serie);

                    //on a trouvé la caracteristique cherchée et on a créer le barChart
                    break;
                }
            }

        return barChart;
    }
        

    



    public static void main(String[] args){
        Application.launch(args);
    }

}
