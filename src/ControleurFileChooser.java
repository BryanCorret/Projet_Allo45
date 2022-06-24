import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class ControleurFileChooser implements EventHandler<ActionEvent> {

    private FenetreParametreAnalyste fenPara;
    private AppliSondage sondage;

    public ControleurFileChooser(FenetreParametreAnalyste fenFileChooser, AppliSondage appliSondage) {
        this.fenPara = fenFileChooser;
        this.sondage = appliSondage;
    }

    @Override
    public void handle(ActionEvent event) {
        FileChooser fc = this.fenPara.getFileChooser();
        File file = fc.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichier JSON (*.json)","*.json" );
        fc.getExtensionFilters().add(extFilter);
        if (file != null) {
            System.out.println(file.getAbsolutePath());
        }
    
    }
}