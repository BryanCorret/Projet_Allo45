import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.*;

public class ControleurFleche implements EventHandler<ActionEvent>{ 

    private AppliTest appli;
    private FenetreAnalyste analyste;
    private FenetreSondeur sondeur;

    public ControleurFleche(FenetreAnalyste analyste){
        // this.appli = appli;
        this.analyste = analyste;
        this.
    }

    @Override
    public void handle(ActionEvent event) {
        Button bouton = (Button) (event.getTarget());
        
        /*      
                                     _           _       
                    __ _ _ __   __ _| |_   _ ___| |_ ___         
                   / _` | '_ \ / _` | | | | / __| __/ _ \
                  | (_| | | | | (_| | | |_| \__ \ ||  __/
                   \__,_|_| |_|\__,_|_|\__, |___/\__\___|
                                       |___/          

            si l'utilisateur est un analyste
        */
        if (this.appli.getFenetreActu().equals("Analyste")){

        
            if (bouton.getId().equals("flecheDroite")){
                
                //on change la question observée 
                List<String> questionnaire = this.analyste.getQuesionnaire().getListQ();

                int index = questionnaire.indexOf(this.analyste.getQuestionActuel());
                if (index+1 >= questionnaire.size()){index = 0;}else{index++;}

                this.analyste.setQuestionActuel(questionnaire.get(index));

                
                //                  PARTIE FICHIER
                
                //enregistrer les infos de la question dans un fichier

                //on récupère les infos déjà écrite dans le fichier
                String prec = "infos précédente"; //ne sera pas écrite dans le fichier
                File fic = new File("./fichier.txt");
                try{
                    FileReader fr = new FileReader(fic);
                    BufferedReader br = new BufferedReader(fr);  
                    StringBuffer sb = new StringBuffer();

                    String line;
                    while((line = br.readLine()) != null){
                    // ajoute la ligne au buffered reader
                    sb.append(line); sb.append("\n");     
                    }prec = sb.toString();
                    fr.close();    
                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}


                //on ré-écrit les anciennes infos et on ajoute les nouvelles au fichier
                try{
                    FileWriter fw = new FileWriter(fic.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(prec);                                             //les infos précédentes
                    bw.write(this.analyste.getQuestionActuel() + "/");              //"Question
                    bw.write(this.analyste.getComboBoxAnalyse().getValue() + "/");  //"ComboBox analyse
                    bw.write(this.analyste.getComboBoxClasse().getValue()+ "/");    //"ComboBox classe
                    bw.write(this.analyste.getComboBoxQuestion().getValue()+ "/");  //"ComboBox question
                    bw.write(this.analyste.getCommentaire() + "\n"); //"Commentaire 
                    bw.close();
                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}


                //maj de l'affichage de l'analyste
                this.appli.majAffichageAnalyste();
            

            }else{  //la flèche de gauche est appuyée
                
                List<String> questionnaire = this.analyste.getquestionnaire().getListQ();

                int index = questionnaire.indexOf(this.analyste.getQuestionActuel());
                if (index-1 < 0){index = questionnaire.size()-1;}else{index--;}
                
                this.analyste.setQuestionActuel(questionnaire.get(index));


                //                  PARTIE FICHIER
                
                //charger les infos du fichier

                File fic = new File("./fichier.txt");
                try{
                    FileReader fr = new FileReader(fic);
                    BufferedReader br = new BufferedReader(fr);  
                    StringBuffer sb = new StringBuffer();

                    String line;
                    while((line = br.readLine()) != null){
                    // ajoute la ligne au buffered reader
                    sb.append(line); sb.append("\n");     
                    }fr.close();
                    
                    //pour chaque question
                    String question[] = sb.toString().split("\n");
                    
                    for (int i=0;i<question.length;i++){
                        //on prends chaque donnée
                        String donnee[] = question[i].split("/");

                        //si on a trouvé la question qu'on veut charger
                        if (donnee[0].equals(this.analyste.getQuestionActuel())){
                            this.analyste.getComboBoxAnalyse().setValue(donnee[1]);  //"ComboBox analyse
                            this.analyste.getComboBoxClasse().setValue(donnee[2]);    //"ComboBox classe
                            this.analyste.getComboBoxQuestion().setValue(donnee[3]);  //"ComboBox question
                            this.analyste.getCommentaire().setText(donnee[4]); //"Commentaire 
                        }

                    }

                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}

                
                //maj de l'affichage de l'analyste
                this.appli.majAffichageAnalyste();

            }
        }
        
        /*
                                       _                 
                   ___  ___  _ __   __| | ___ _   _ _ __ 
                  / __|/ _ \| '_ \ / _` |/ _ \ | | | '__|                
                  \__ \ (_) | | | | (_| |  __/ |_| | |   
                  |___/\___/|_| |_|\__,_|\___|\__,_|_|   
                                       
                si l'utilisateur est un sondeur
         */
        
        
        
        else if(this.appli.getFenetreActu().equals("Sondeur")){
            
            if (bouton.getId().equals("flecheDroite")){
                
                //on change la question observée 
                List<String> questionnaire = this.sondeur.getSondage().getListQ();

                int index = questionnaire.indexOf(this.sondeur.getQuestion());
                if (index+1 >= questionnaire.size()){index = 0;}else{index++;}

                this.sondeur.setQuestion(questionnaire.get(index));

                
                //                  PARTIE FICHIER
                
                //enregistrer les infos de la question dans un fichier

                //on récupère les infos déjà écrite dans le fichier
                String prec = "infos précédente"; //ne sera pas écrite dans le fichier
                File fic = new File("./fichier.txt");
                try{
                    FileReader fr = new FileReader(fic);
                    BufferedReader br = new BufferedReader(fr);  
                    StringBuffer sb = new StringBuffer();

                    String line;
                    while((line = br.readLine()) != null){
                    // ajoute la ligne au buffered reader
                    sb.append(line); sb.append("\n");     
                    }prec = sb.toString();
                    fr.close();    
                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}


                //on ré-écrit les anciennes infos et on ajoute les nouvelles au fichier
                try{
                    FileWriter fw = new FileWriter(fic.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(prec);     //les infos précédentes
                    
                    //selon le type de la question
                    if (this.sondeur.getTypeReponse().equals('u')){
                        bw.write(this.sondeur.getTextArea() + "/");
                        bw.write(this.sondeur.getValeurBouton() + "\n");

                    }else if (this.sondeur.getTypeReponse().equals('m')){
                        bw.write(this.sondeur.getTextArea() + "/");
                        bw.write(this.sondeur.getValeurCombo() + "\n");

                    }else if (this.sondeur.getTypeReponse().equals('c')){
                        //RIEN POUR L'INSTANT, IL FAUT FINIR L'IHM DU CLASSEMENT AVANT

                    }else if (this.sondeur.getTypeReponse().equals('n')){
                        bw.write(this.sondeur.getTextArea() + "/");
                        bw.write(this.sondeur.getSlider() + "\n");

                    }else if (this.sondeur.getTypeReponse().equals('l')){
                        bw.write(this.sondeur.getTextArea() + "\n");
                    }
                    
                    else{ System.out.println("Mauvais type de Question");}

                    bw.close();
                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}



                //maj de l'affichage du sondeur
                this.appli.majAffichageSondeur();
            

            }else{  //la flèche de gauche est appuyée
                
                List<String> questionnaire = this.sondeur.getSondage().getListQ();

                int index = questionnaire.indexOf(this.sondeur.getQuestion());
                if (index-1 < 0){index = questionnaire.size()-1;}else{index--;}
                
                this.sondeur.setQuestion(questionnaire.get(index));


                //                  PARTIE FICHIER
                
                //charger les infos du fichier

                File fic = new File("./fichier.txt");
                try{
                    FileReader fr = new FileReader(fic);
                    BufferedReader br = new BufferedReader(fr);  
                    StringBuffer sb = new StringBuffer();

                    String line;
                    while((line = br.readLine()) != null){
                    // ajoute la ligne au buffered reader
                    sb.append(line); sb.append("\n");     
                    }fr.close();
                    
                    //pour chaque question
                    String question[] = sb.toString().split("\n");
                    
                    for (int i=0;i<question.length;i++){
                        //on prends chaque donnée
                        String donnee[] = question[i].split("/");

                        //si on a trouvé la question qu'on veut charger
                        if (donnee[0].equals(this.analyste.getQuestionActuel())){
                            if (this.sondeur.getTypeReponse().equals('u')){
                                this.sondeur.setTextArea( donnee[1] );
                                this.sondeur.setValeurBouton( donnee[2] );
        
                            }else if (this.sondeur.getTypeReponse().equals('m')){
                                this.sondeur.setTextArea( donnee[1] );
                                this.sondeur.setValeurCombo( donnee[2] );
        
                            }else if (this.sondeur.getTypeReponse().equals('c')){
                                //RIEN POUR L'INSTANT, IL FAUT FINIR L'IHM DU CLASSEMENT AVANT
        
                            }else if (this.sondeur.getTypeReponse().equals('n')){
                                this.sondeur.setTextArea( donnee[1] );
                                this.sondeur.setSlider( donnee[2] );
        
                            }else if (this.sondeur.getTypeReponse().equals('l')){
                                this.sondeur.setTextArea( donnee[1] );
                            }
                                else{ System.out.println("Mauvais type de Question");}
                        }

                    }

                }catch (IOException e){System.out.println("ERREUR fichier inexistant");}

                
                //maj de l'affichage du sondeur
                this.appli.majAffichageSondeur();

            }

        }   
    }
          
}