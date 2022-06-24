import javafx.scene.layout.VBox;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class FenetreConnexion extends VBox{


    VBox VBprincipal;
    TextField NomU;
    PasswordField Mdp;
    Boolean erreur ;
    AppliSondage sondage;
    
    public FenetreConnexion(AppliSondage sondage){
        this.VBprincipal = new VBox();
        VBprincipal.setSpacing(10);
        VBprincipal.setStyle("-fx-background-color: #FFFFFF;");
        VBprincipal.setPrefSize(600, 500);
        this.Mdp = new PasswordField();
        this.NomU = new TextField();

        this.sondage = sondage;
        this.erreur =false;

        this.setTitle();
        VBprincipal.getChildren().add(this.setText());
        this.getChildren().add(VBprincipal);

    }

    public void setTitle(){
        ImageView logo = new ImageView("file:./IMG/logo_agrandi.png");
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        Text title = new Text("Connectez-Vous");
        title.setFont(javafx.scene.text.Font.font("Arial", 20));
        VBprincipal.getChildren().addAll(logo,title);
        VBprincipal.setAlignment(javafx.geometry.Pos.CENTER);
    }

    public VBox setText(){
        
        
        // Style Vbox instruction
        VBox VBincription = new VBox();
        VBincription.setSpacing(5);
        VBincription.setStyle("-fx-border-style: solid;" +   
        "-fx-border-width: 1;" +
        "-fx-border-insets: 25 75 100 75;" +
        "-fx-padding: 1em;");
        

        Label textErreur = new Label("Nom d'utilisateur ou mot de passe incorrect");
        textErreur.setFont(javafx.scene.text.Font.font("Arial", 15));         
        textErreur.setStyle("-fx-text-fill: #E42B01;");

        HBox hBoxErreur = new HBox();
        hBoxErreur.setAlignment(Pos.CENTER);
        hBoxErreur.getChildren().add(textErreur);
        hBoxErreur.setSpacing(10);
        hBoxErreur.setPadding(new Insets(10,10,10,10));


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
        ImageView img = new ImageView(new Image("file:IMG/ButtonCo.png"));
        img.setFitHeight(100);
        img.setFitWidth(200);

        Button btn = new Button("");//Créer votre compte
        btn.setGraphic(img);

        btn.setAlignment(javafx.geometry.Pos.CENTER);
        btn.setStyle("-fx-background-color: transparent;");
        btn.setOnAction((EventHandler<ActionEvent>) new ControleurConnexion(this,this.sondage,this.sondage.getConnexion()));

        // Ajout text changment de page
        HBox HBox = new HBox();
        Text textMdp = new Text("Mot de passe oublié ? ");
        textMdp.setFont(javafx.scene.text.Font.font("Arial", 15));

        Label textConnecter = new Label("Inscrivez-vous");
        textConnecter.setUnderline(true);
        textConnecter.setFont(javafx.scene.text.Font.font("Arial", 15));
        textConnecter.setStyle("-fx-text-fill: blue;");
        textConnecter.setOnMouseClicked((EventHandler<javafx.scene.input.MouseEvent>) new ControleurInscrit(this,this.sondage));

        HBox.getChildren().addAll(textMdp, textConnecter);
        if(erreur){
            VBincription.getChildren().addAll(hBoxErreur,textU, NomU, textM, Mdp, btn, HBox);
            VBincription.setAlignment(Pos.CENTER);
            NomU.setStyle("-fx-border-color: #E42B01;");
            Mdp.setStyle("-fx-border-color: #E42B01;");



        }
        else {
            VBincription.getChildren().addAll(textU, NomU, textM, Mdp, btn, HBox);
            VBincription.setAlignment(Pos.CENTER);

        }
        return VBincription;
    }

    public String getNomU(){
        return NomU.getText();
    }

    public String getMdp(){
        return Mdp.getText();
    }
    public void setErreur(Boolean erreur) {
        this.erreur = erreur;
    }
    public void majVue(){
        VBprincipal.getChildren().set(1, this.setText());
        this.getChildren().set(0,VBprincipal);
        
    }

}
