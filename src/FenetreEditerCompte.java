import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FenetreEditerCompte extends VBox{
    VBox VBprincipal;
    TextField NomU;
    TextField Mdp;
    TextField Mdp2;
    TextField NomF;
    TextField NomP;
    AppliSondage sondage;
    
    public FenetreEditerCompte(AppliSondage sondage){
        this.VBprincipal = new VBox();
        VBprincipal.setSpacing(10);
        VBprincipal.setStyle("-fx-background-color: #FFFFFF;");
        VBprincipal.setPrefSize(600, 500);
        
        this.Mdp2 = new PasswordField();
        this.Mdp = new PasswordField();
        this.NomU = new TextField();
        this.NomP = new TextField();
        this.NomF = new TextField();

        this.sondage = sondage;
        
        this.setTitle();
        this.setText();
        this.getChildren().add(VBprincipal);

    }

    public void setTitle(){
        Text title = new Text("Modifier votre compte");
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
        NomP.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomP.setPromptText(this.sondage.getUtilisateur().getPrenom());

        // text Nom
        Text textNom = new Text("Nom :");
        textNom.setFont(javafx.scene.text.Font.font("Arial", 15)); 
 
        //textfield Nom
        NomF.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomF.setPromptText(this.sondage.getUtilisateur().getNom());
        
        
        // text Nom Utilisateur
        Text textU = new Text("Nom utilisateur :");
        textU.setFont(javafx.scene.text.Font.font("Arial", 15)); 
    
        //textfield Utilisateur
        NomU.setStyle("-fx-Background-Color: #ADAEB0;");
        this.NomU.setPromptText(this.sondage.getUtilisateur().getLogin());

        // text Adresse Mail
        //Text textA = new Text("Adresse mail :");
        //textA.setFont(javafx.scene.text.Font.font("Arial", 15)); 

        //textfield Adresse Mail
        //TextField AdM = new TextField();
        //AdM.setStyle("-fx-Background-Color: #ADAEB0;");

        // text Mot de passe
        Text textM = new Text("Mot de passe Actuel :");
        textM.setFont(javafx.scene.text.Font.font("Arial", 15)); 
   
        //textfield mdp
        Mdp.setStyle("-fx-Background-Color: #ADAEB0;");
        this.Mdp.setPromptText(this.sondage.getUtilisateur().getPassword());

        // text Mot de passe
        Text textM2 = new Text("Nouveau mot de passe :");
        textM2.setFont(javafx.scene.text.Font.font("Arial", 15));
       
        //textfield mdp
        Mdp2.setStyle("-fx-Background-Color: #ADAEB0;");
        this.Mdp2.setPromptText("Entrez votre mot de passe");

        
        // Button
        ImageView img = new ImageView(new Image("file:IMG/buttonIn.png"));
        img.setFitHeight(100);
        img.setFitWidth(200);

        Button btn = new Button("");//Modifier votre compte
        btn.setGraphic(img);

        btn.setAlignment(javafx.geometry.Pos.CENTER);
        btn.setStyle("-fx-background-color: transparent;");
        // btn.setOnAction((EventHandler<ActionEvent>) new ControleurValiderCompte(this,this.sondage));
 
        

       
        VBincription.getChildren().addAll(textP, NomP, textNom, NomF, textU, NomU, textM, Mdp, textM2, Mdp2, btn);
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
