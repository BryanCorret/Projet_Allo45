import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurInpecterElement implements EventHandler<ActionEvent>{

    appliSondage  s;
    FenetreHomeAnalyste fen;

    public ControleurInpecterElement(appliSondage s, FenetreHomeAnalyste fen){
        this.s = s;
        this.fen = fen;   
    }


    @Override
    public void handle(ActionEvent arg0) {
        
        // Questionnaire sondageSelectionne = BiblioSQL.getSondage(this.fen.getId());
        // this.s.setSondageSelectionne(sondageSelectionne);

        // Pour test
        Questionnaire r = new Questionnaire(1, "zesrdtfyguhijokpl", "OUVERT");
        r.addQuestion(new Question(1, "Etes vous un Homme", 0, 'u', 01));
        r.addQuestion(new Question(2, "Etes vous majeur", 0, 'u', 02));

        this.s.setSondageSelectionne(r);

        this.s.modeAnalyste();
    }
}