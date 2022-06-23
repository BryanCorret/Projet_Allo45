import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurCBAnalyste implements EventHandler<ActionEvent>{
   private AppliSondage app;
   private String valPrec;
   public ControleurCBAnalyste(AppliSondage app){
    this.app = app;
    this.valPrec = "";
   }
   @Override
   public void handle(ActionEvent e){
    ComboBox<String> cbTri = this.app.getcbTri();
    ComboBox<String> cbDiag = this.app.getcbDiag();
    ComboBox<String> cbRep = this.app.getcbRep();
    ComboBox<String> cbQuestion = this.app.getcbQuestion();
    for(Question question:this.app.getSondage().getListQ()){
        if(question.getTextQ().equals(cbQuestion.getValue())){
            this.app.setQuestion(question);
        }
    }
    
    switch(cbTri.getValue()){
        case "Tranche d'âge":
            if(this.valPrec != "idTr"){
                this.valPrec = "idTr";
                if(!(cbRep.getSelectionModel().isEmpty())){
                cbRep.getSelectionModel().clearSelection();
                }
                this.app.remplirComboBoxTr();
            }
        break;
        case "Catégorie":
        if(this.valPrec != "idCat"){
            this.valPrec = "idCat";
            if(!(cbRep.getSelectionModel().isEmpty())){
            cbRep.getSelectionModel().clearSelection();
            }
            this.app.remplirComboBoxCat();
        }
        break;
    }
    switch(cbDiag.getValue()){
        case "Circulaire":
            this.app.createPieChart(BiblioSQL.recupererReponses(this.app.getConnexion(), this.valPrec,this.app.getSondage().getIdQ(),this.app.getQuestion().getNumQ()),this.app.getQuestion());
            break;
        //faire les autres quand ils seront implémentés
    }
    }
    
   }

