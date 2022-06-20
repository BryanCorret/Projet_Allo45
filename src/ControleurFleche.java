import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

import java.util.*;


public class ControleurFleche implements EventHandler<ActionEvent>{
    
    private FenetreAnalyste analyste;
    private appliSondage appli;
    
    public ControleurFleche(FenetreAnalyste analyste, appliSondage appli){
        this.analyste = analyste;
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button bouton = (Button) (event.getTarget());

        if (bouton.getId().equals("flecheDroite")){

            List<String> lesQuestions = this.analyste.getLesQuestions();

            //on passe à la question suivante
            int index; index = lesQuestions.indexOf(this.analyste.getQuestionActuel());
            if (index+1 > lesQuestions.size()){ index =0;} else{ index ++;}

                /* 
                          __  _  __     
                         / / | | \ \    
                        / /  | |  \ \   
                       / /   | |   \ \  
                      / /    |_|    \ \ 
                     /_/     (_)     \_\        
                    
            Il faudra sauvegarder les questions dans un fichier
                */


            //on change de question
            this.analyste.setQuestionActuel(lesQuestions.get(index));

            this.appli.majAffichageAnalyste();


        }
    }
}