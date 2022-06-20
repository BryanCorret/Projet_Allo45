import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FenetreConnexion extends VBox{


    VBox VBprincipal;
    TextField NomU;
    PasswordField Mdp;

    appliSondage sondage;
    
    public FenetreConnexion(){
        this.VBprincipal = new VBox();
        VBprincipal.setSpacing(10);
        VBprincipal.setStyle("-fx-background-color: #FFFFFF;");
        VBprincipal.setPrefSize(600, 500);
        
        this.Mdp = new PasswordField();
        this.NomU = new TextField();

        this.sondage = new appliSondage();

        this.setTitle();
        this.setText();
        this.getChildren().add(VBprincipal);

    }

    public void setTitle(){
        Text title = new Text("Créez-votre compte");
        title.setFont(javafx.scene.text.Font.font("Arial", 20));
        VBprincipal.getChildren().add(title);
        VBprincipal.setAlignment(javafx.geometry.Pos.CENTER);
    }

    public void setText(){
        // Style Vbox instruction
        VBox VBincription = new VBox();
        VBincription.setSpacing(5);
        VBincription.setStyle("-fx-border-style: solid;" +   
        "-fx-border-width: 1;" +
        "-fx-border-insets: 25 75 100 75;" +
        "-fx-padding: 1em;");
        
        
        
        // text Nom Utilisateur
        Text textU = new Text("Nom utilisateur :");
        textU.setFont(javafx.scene.text.Font.font("Arial", 15)); 
    
        //textfield Utilisateur
        NomU.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomU.setPromptText("Nom utilisateur");

        // text Adresse Mail
        //Text textA = new Text("Adresse mail :");
        //textA.setFont(javafx.scene.text.Font.font("Arial", 15)); 

        //textfield Adresse Mail
        //TextField AdM = new TextField();
        //AdM.setStyle("-fx-Background-Color: #ADAEB0;");

        // text Mot de passe
        Text textM = new Text("Mot de passe :");
        textM.setFont(javafx.scene.text.Font.font("Arial", 15)); 
   
        //textfield mdp
        Mdp.setStyle("-fx-Background-Color: #ADAEB0;");
        this.Mdp.setPromptText("Un mot de passe");
        
        // Button
        ImageView img = new ImageView(new Image("file:IMG/buttonCo.png"));
        img.setFitHeight(100);
        img.setFitWidth(200);

        Button btn = new Button("");//Créer votre compte
        btn.setGraphic(img);

        btn.setAlignment(javafx.geometry.Pos.CENTER);
        btn.setStyle("-fx-background-color: transparent;");
        btn.setOnAction((EventHandler<ActionEvent>) new ControleurConnexion(this,this.sondage));

        // Ajout text changment de page
        HBox HBox = new HBox();
        Text textMdp = new Text("Mot de passe oublié ? ");
        Text textConnecter = new Text("Inscrivez-vous");
        textMdp.setFont(javafx.scene.text.Font.font("Arial", 15));
        textConnecter.setFont(javafx.scene.text.Font.font("Arial", 15));

        textConnecter.setOnMouseClicked((EventHandler<javafx.scene.input.MouseEvent>) new ControleurConnecterVous(this,this.sondage));
        textConnecter.setUnderline(true);
        textConnecter.setStyle("-fx-text-fill: blue;");

        HBox.getChildren().addAll(textMdp, textConnecter);
        VBincription.getChildren().addAll(textU, NomU, textM, Mdp, btn, HBox);
        VBprincipal.getChildren().add(VBincription);

    }

    public String getNomU(){
        return NomU.getText();
    }

    public String getMdp(){
        return Mdp.getText();
    }



}
