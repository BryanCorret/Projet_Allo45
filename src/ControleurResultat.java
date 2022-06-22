import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.*;

public class ControleurResultat implements EventHandler<ActionEvent>{ 

    private FenetreSondeur sondeur;


    public ControleurResultat(FenetreSondeur sondeur){
        this.sondeur = sondeur;

    }

    @Override
    public void handle(ActionEvent event) {
        String infos ="";
        String[] question;
        String[] donnee;

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
            question = sb.toString().split("\n");
            fr.close();

            // pour chaque question
            for (int i=0;i<question.length;i++){
                //on prends chaque donnée
                donnee = question[i].split("/");
                infos += donnee[0] + donnee[1];
                for (int j=3;j<donnee[i].length();j++){
                    infos += donnee[j];
                }
                //on saute une ligne, on a toute les infos de la question
                infos += "\n";
            }

        }catch (IOException e){System.out.println("ERREUR 404");}
        

        Alert alert = new Alert(Alert.AlertType.INFORMATION,infos);
        alert.setTitle("Resultat");

        //on affiche les résultats et on attend
        Optional<ButtonType> reponse = alert.showAndWait();


        this.sondeur.getSondage();
    }
}