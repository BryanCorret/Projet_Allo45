import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FenetreInscription extends VBox{
    VBox VBprincipal;
    TextField NomU;
    TextField Mdp;
    TextField Mdp2;
    TextField NomF;
    TextField NomP;
    appliSondage sondage;
    
    public FenetreInscription(){
        this.VBprincipal = new VBox();
        VBprincipal.setSpacing(10);
        VBprincipal.setStyle("-fx-background-color: #FFFFFF;");
        VBprincipal.setPrefSize(600, 500);
        
        this.Mdp2 = new TextField();
        this.Mdp = new TextField();
        this.NomU = new TextField();
        this.NomP = new TextField();
        this.NomF = new TextField();
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
        
        
        
        // text Prénom
        Text textP = new Text("Prénom :");
        textP.setFont(javafx.scene.text.Font.font("Arial", 15));

        //textfield Prénom
        TextField NomP = new TextField();
        NomP.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomP.setPromptText("Jean");

        // text Nom
        Text textNom = new Text("Nom :");
        textNom.setFont(javafx.scene.text.Font.font("Arial", 15)); 
 
        //textfield Nom
        TextField NomF = new TextField();
        NomF.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomF.setPromptText("Dupont");
        
        
        // text Nom Utilisateur
        Text textU = new Text("Nom utilisateur :");
        textU.setFont(javafx.scene.text.Font.font("Arial", 15)); 
    
        //textfield Utilisateur
        TextField NomU = new TextField();
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
        TextField Mdp = new TextField();
        Mdp.setStyle("-fx-Background-Color: #ADAEB0;");
        this.Mdp.setPromptText("Un mot de passe");

        // text Mot de passe
        Text textM2 = new Text("Confirmez votre mot de passe :");
        textM2.setFont(javafx.scene.text.Font.font("Arial", 15));
       
        //textfield mdp
        TextField Mdp2 = new TextField();
        Mdp2.setStyle("-fx-Background-Color: #ADAEB0;");
        this.Mdp2.setPromptText("Confirmation du mot de passe");

        
        // Button
        ImageView img = new ImageView(new Image("file:IMG/buttonIn.png"));
        img.setFitHeight(100);
        img.setFitWidth(200);

        Button btn = new Button("");//Créer votre compte
        btn.setGraphic(img);

        btn.setAlignment(javafx.geometry.Pos.CENTER);
        btn.setStyle("-fx-background-color: transparent;");
        btn.setOnAction((EventHandler<ActionEvent>) new ControleurInscription(this,this.sondage));

        // Ajout text changment de page
        HBox HBox = new HBox();
        Text text = new Text("Déjà un compte ? ");
        Text textConnecter = new Text("Connectez-vous");
        text.setFont(javafx.scene.text.Font.font("Arial", 15));
        textConnecter.setFont(javafx.scene.text.Font.font("Arial", 15));

        textConnecter.setOnMouseClicked((EventHandler<javafx.scene.input.MouseEvent>) new ControleurConnecterVous(this,this.sondage));
        textConnecter.setUnderline(true);
        textConnecter.setStyle("-fx-text-fill: blue;");

        HBox.getChildren().addAll(text, textConnecter);
        VBincription.getChildren().addAll(textP, NomP, textNom, NomF, textU, NomU, textM, Mdp, textM2, Mdp2, btn, HBox);
        VBprincipal.getChildren().add(VBincription);

    }

    public String getNomU(){
        return NomU.getText();
    }

    public String getMdp(){
        return Mdp.getText();
    }

    public String getMdp2(){
        return Mdp2.getText();
    }

    public String getNomF(){
        return NomF.getText();
    }

    public String getNomP(){
        return NomP.getText();
    }

}
