import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurCBAnalyste implements EventHandler<ActionEvent>{
   private appliSondage app;
   private String valPrec;
   public ControleurCBAnalyste(appliSondage app){
    this.app = app;
    this.valPrec = "";
   }
   @Override
   public void handle(ActionEvent e){
    ComboBox<String> cbTri = this.app.getcbTri();
    ComboBox<String> cbDiag = this.app.getcbDiag();
    ComboBox<String> cbRep = this.app.getcbRep();
    ComboBox<String> cbQuestion = this.app.getcbQuestion();
    switch(cbTri.getValue()){
        case "Tranche d'âge":
            if(this.valPrec != "idTr"){
                this.valPrec = "idTr";
                this.app.remplirComboBoxTr();
            }
        break;
        case "Catégorie":
        if(this.valPrec != "idCat"){
            this.valPrec = "idCat";
            this.app.remplirComboBoxCat();
        }
        break;
    }
    switch(cbDiag.getValue()){
        case "Circulaire":
            this.app.createPieChart(BiblioSQL., cbQuestion.getValue());
            break;
    }
    
   }
}
