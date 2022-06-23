import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FenetreDonneesBrutes extends BorderPane{
    
    private Button boutonHome;
    private Button boutonRefresh;
    private Button boutonDeco;
    private Button boutonResearch;
    private Button boutonParam;
    private String sondageid;
    private AppliSondage sondage;
    public String SondageNom;

    public FenetreDonneesBrutes(Button boutonHome,Button boutonRefresh,Button boutonDeco, Button Param,AppliSondage s){
        super();
        this.sondage = s;
        this.boutonHome = boutonHome;
        this.boutonRefresh = boutonRefresh;
        this.boutonDeco = boutonDeco;
        this.boutonParam = Param;
        this.sondageid = "";
        this.SondageNom = "";
        this.sondage = s;
        BorderPane borderTop = borderPaneTop();
        this.setTop(borderTop);
        this.setCenter(HBoxMid());
    }       
    
    private BorderPane borderPaneTop(){
        BorderPane border = new BorderPane();
        
        HBox hHome = new HBox();
        HBox hRefresh = new HBox();
        Text textHome = new Text("Sondage séléctionné : " + SondageNom);
        textHome.setFont(Font.font("Arial",15));
        hRefresh.getChildren().add(textHome);
        hRefresh.setAlignment(Pos.CENTER);
        hRefresh.setPadding(new Insets(10,-310,10,10));

        
        if (sondageid.equals("")){
            textHome.setVisible(false);
        }
        else{
            textHome.setVisible(true);
        }

        hHome.getChildren().addAll(this.boutonHome,this.boutonRefresh, this.boutonParam, hRefresh);
        hHome.setSpacing(-5);


        Label ltitre = new Label("Donnée Brutes"+"this.getQuestion()");
        ltitre.setAlignment(Pos.CENTER);

        HBox hID = new HBox();
        
        ImageView profil = new ImageView("file:IMG/user.jpg");
        profil.setFitHeight(50);profil.setFitWidth(50);
       
        Button Deco = this.boutonDeco;  
        hID.getChildren().addAll(profil,Deco);

        ltitre.setFont(Font.font(" Arial ",FontWeight.BOLD,24));
        border.setStyle("-fx-background-color:#ffffff;");
        
        border.setLeft(hHome);
        border.setCenter(ltitre);
        border.setRight(hID);

        Insets arg1 =new Insets(30, 10, 20, 30);
       
        border.setPadding(arg1);   

        return border;
    }

    private HBox HBoxMid(){
        HBox hBox = new HBox();

        Text t = new Text("Données Brutes");
        t.setFont(Font.font("Arial",14));
        // for(Reponse r : BiblioSQL.getDonneBrute()){ Retourne un liste de donnée brute
            // Text t = new Text("r.toString");
            // hBox.getChildren().add(t);}
       
        hBox.getChildren().add(t);

        return hBox;
    }

}
