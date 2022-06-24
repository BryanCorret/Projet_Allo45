import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurInscription implements EventHandler<ActionEvent> {
    
    private FenetreInscription fenInscription;
    private AppliSondage sondage;

    public ControleurInscription(FenetreInscription fenInscription, AppliSondage sondage) {
        this.fenInscription = fenInscription;
        this.sondage = sondage;
    }

    @Override
    public void handle(ActionEvent event) {
        // recuperer les donnees de la fenetre
        String Nom = this.fenInscription.getNomF();
        String Prenom = this.fenInscription.getNomP();
        String NomU = this.fenInscription.getNomU();
        String Mdp = this.fenInscription.getMdp();
        String Conf = this.fenInscription.getMdp2();
        
        //System.out.println(Nom);
        //System.out.println(Prenom);
        //System.out.println(NomU);
        //System.out.println(Mdp);
        //System.out.println(Conf);



        if (Nom.equals("") || Prenom.equals("") || NomU.equals("") || Mdp.equals("") || Conf.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } 
        else {
            if(Mdp.length() < 8){
                System.out.println("Veuillez rentrer un mot de passe de plus de 8 caracteres");
            }

            else if (Mdp.equals(Conf)) {
                BiblioSQL.register(this.fenInscription);
                //this.sondage.Inscription(Nom, Prenom, NomU, Mdp);

            } 
            
            else {
                System.out.println("Les mots de passe ne correspondent pas");
            }
        }
    }
}
