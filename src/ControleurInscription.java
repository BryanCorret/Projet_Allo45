import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurInscription implements EventHandler<ActionEvent> {
    
    private FenetreInscription fenInscription;

    public ControleurInscription(FenetreInscription fenInscription, appliSondage sondage) {
        this.fenInscription = fenInscription;
    }

    @Override
    public void handle(ActionEvent event) {
        // recuperer les donnees de la fenetre
        String Nom = this.fenInscription.getNomF();
        System.out.println(Nom);
        String Prenom = this.fenInscription.getNomP();
        System.out.println(Prenom);
        String NomU = this.fenInscription.getNomU();
        System.out.println(NomU);
        String Mdp = this.fenInscription.getMdp();
        System.out.println(Mdp);
        String Conf = this.fenInscription.getMdp2();
        System.out.println(Conf);



        if (Nom.equals("") || Prenom.equals("") || NomU.equals("") || Mdp.equals("") || Conf.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } 
        else {
            if(Mdp.length() < 8){
                System.out.println("Veuillez rentrer un mot de passe de plus de 8 caracteres");
            }

            if (Mdp.equals(Conf)) {
                System.out.println("Inscription réussie");

            } 
            
            else {
                System.out.println("Les mots de passe ne correspondent pas");
            }
        }
    }
}
