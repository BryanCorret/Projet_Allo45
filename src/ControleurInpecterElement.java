import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurInpecterElement implements EventHandler<ActionEvent>{

    AppliSondage  s;
    FenetreHomeAnalyste fen;

    public ControleurInpecterElement(AppliSondage s, FenetreHomeAnalyste fen){
        this.s = s;
        this.fen = fen;   
    }


    @Override
    public void handle(ActionEvent arg0) {

        // Pour test
        
    try{
        //Questionnaire sondageSelectionne = BiblioSQL.getQuestionnaireId(this.s.getConnexion(),this.fen.getId());
        //this.s.setSondageSelectionne(sondageSelectionne);
    }

    catch(NullPointerException e){
        System.out.println("Pas de sondage sélectionné");

    }
        this.s.modeAnalyste();
    }
}