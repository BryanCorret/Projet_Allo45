import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurVerifLancement implements EventHandler<ActionEvent>{

    private ControleurNavSondeur ctrl;
    private appliSondage main;

    public ControleurVerifLancement(appliSondage main, ControleurNavSondeur ctrl){
        this.main = main;
        this.ctrl = ctrl;
    }
    @Override
    public void handle(ActionEvent event) {
        if(!(ctrl.getTxt1().equals(""))){
        String inn = "";
        for(int i=0; i<ctrl.getTxt2().length(); i++){
            try{
                Double.parseDouble(String.valueOf(ctrl.getTxt2().charAt(i)));
                inn += String.valueOf(ctrl.getTxt2().charAt(i));

            }
            catch(NumberFormatException ex){
                break;
            }
        if(!(inn == ""))
        this.main.modeSondeur(Integer.valueOf(inn));
        else{
        Alert Listesondage = new Alert(Alert.AlertType.ERROR);
        Listesondage.setTitle("Attention");
        Listesondage.setHeaderText("Veuillez sélectionner un questionnaire !");
        }
    }
}
        else{
        Alert Listesondage = new Alert(Alert.AlertType.ERROR);
        Listesondage.setTitle("Attention");
        Listesondage.setHeaderText("Veuillez sélectionner un Panel !");
        }

        
    }

}