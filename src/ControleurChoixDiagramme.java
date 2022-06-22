import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurChoixDiagramme implements EventHandler<ActionEvent> {
    private FenetreAnalyste fen;
    
    public ControleurChoixDiagramme(FenetreAnalyste fen){
        this.fen =fen;

    }

    @Override
    public void handle(ActionEvent arg0) {
        
        this.fen.setSondeActu(this.fen.getComboAnalyse().getValue());
        this.fen.majAffichageDiagrame();
    }
}
