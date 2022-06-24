import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.*;

public class ControleurValiderReponse implements EventHandler<ActionEvent>{
    
    private FenetreSondeur sondeur;
    private AppliSondage appli;

    private ConnexionMySQL laConnexionMySQL;
    private Sonde sonde;
    private Utilisateur utilisateur;


    public ControleurValiderReponse(FenetreSondeur sondeur, AppliSondage appli, ConnexionMySQL laConnexionMySQL, Sonde sonde, Utilisateur utilisateur){
        this.sondeur = sondeur;
        this.appli = appli;

        this.laConnexionMySQL = laConnexionMySQL;
        this.sonde = sonde;
        this.utilisateur = utilisateur;
    }

    @Override
    public void handle(ActionEvent event){
        addRepSet();
    }
    public void addRepSet(){
        //On va enregistrer les informations du questionnaire

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Êtes vous sur de vouloir valider et quitter le sondage?", ButtonType.NO,ButtonType.YES);
        alert.setTitle("Valider");

        Optional<ButtonType> reponse = alert.showAndWait();
        //si la réponse est oui
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){        

            //on prend les informations stockées dans le fichier
            try{
                File fic = new File("./infosTempQuestions.txt");
                FileReader fr = new FileReader(fic);
                BufferedReader br = new BufferedReader(fr);  
                StringBuffer sb = new StringBuffer();

                String line;
                while((line = br.readLine()) != null){
                // ajoute la ligne au buffered reader
                sb.append(line); sb.append("\n");     
                }
                //on fait une liste contenant chaque question
                String[] question = sb.toString().split("\n");
                fr.close();

                //on écrit les réponses dans la bases de données
                for (int i=0;i<question.length;i++){
                    //on prends chaque donnée
                    String donnee[] = question[i].split("/");

                    //pour chaque donnée de la réponse (sauf le premier, i.e. le TextArea, le texte et le numéro de la question)
                        //on modélise une réponse avec les données du fichier
                        Reponse rep = new Reponse(this.sondeur.getSondage().getIdQ(), Integer.valueOf(donnee[0]), this.sonde.getCaracteristique(), donnee[2]);
                        BiblioSQL.setReponse(this.laConnexionMySQL, rep, this.sonde, this.utilisateur);
                }
            
            }catch (IOException e){System.out.println("ERREUR 404");}

            //on revient au Home Sondeur
            this.appli.modeHomeSondeur();

            //on remet à zéro les infos du fichiers, étant donné que tout à été enregitré
            try{
                File fic = new File("./infosTempQuestions.txt");
            FileWriter fw = new FileWriter(fic.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            }catch (IOException e){System.out.println("ERREUR 405");}

        }
        //on ferme simplement la fenêtre pop-up si la réponse est non
        
    } 
    }
