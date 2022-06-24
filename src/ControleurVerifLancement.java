import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurVerifLancement implements EventHandler<ActionEvent>{

    private ControleurNavSondeur ctrl;
    private AppliSondage main;

    public ControleurVerifLancement(AppliSondage main, ControleurNavSondeur ctrl){
        this.main = main;
        this.ctrl = ctrl;
    }
    @Override
    public void handle(ActionEvent event) {
        System.out.println("aoe");
        System.out.println(BiblioSQL.getQuestionnaire(this.main.getConnexion(), this.ctrl.getTxt2()));
        System.out.println("bruh");
        this.main.setSondageSelectionne(BiblioSQL.getQuestionnaire(this.main.getConnexion(), this.ctrl.getTxt2()));
        this.main.setSondeActu(BiblioSQL.getUnSondeAuHasardDansLePanel(this.main.getConnexion(), this.main.getSondage().getIdQ(), this.ctrl.getTxt1()));
        Alert Informations = new Alert(Alert.AlertType.INFORMATION);
        Informations.setTitle("Votre sondé a été choisi");
        Informations.setHeaderText("Votre sondé sera " + this.main.getSondeActu().getPrenomSond() + " " + this.main.getSondeActu().getNomSond());
        Informations.setContentText("Son numéro a appeler est " + this.main.getSondeActu().getTelephoneSond());
        Informations.showAndWait();
        this.main.modeSondeur();
        this.ctrl.close();
    }

}