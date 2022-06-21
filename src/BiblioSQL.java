import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BiblioSQL {
    public static int getMaxID(ConnexionMySQL laConnection){
        Statement st;
		try {
			st = laConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(IDU) FROM UTILISATEUR;");    
            return rs.getInt("IDU");
		} catch (SQLException e) {
			e.getMessage();
		}
		return -1;
    }

    public static boolean userExists(ConnexionMySQL laConnexion, String username, String password){
      Statement st;
      String requete = "SELECT U.LOGIN LOG, U.MOTDEPASSE MDP FROM UTILISATEUR U;";
      try {
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while(rs.next())
          if(rs.getString("LOG") == username && rs.getString("MDP") == password) return true;
          return false;
          } 
          catch (SQLException e) {
              e.getMessage();
        return false;
      }
    }  

    public static void login(ConnexionMySQL laConnexion, String username, String password){
      if(BiblioSQL.userExists(laConnexion,username, password))
        try{
          if(laConnexion.isConnecte())
          laConnexion.close();
          laConnexion.connecter(username, password);
        }
        catch(Exception ex){
            ex.getMessage();
        }
      }
      public static ConnexionMySQL connectRoot(){
        try {
          ConnexionMySQL temp = new ConnexionMySQL();
          temp.connecter("root", "mdp_root");
          return temp;
        } catch (ClassNotFoundException e) {
          e.getMessage();
        } catch (SQLException e){
          System.out.println("Problème lors du log");
        }
        return null;
      }
      public static void register(ConnexionMySQL laConnexion, FenetreInscription fenetre){
        Statement st;
        Utilisateur user = new Utilisateur(BiblioSQL.getMaxID(laConnexion), fenetre.getNomF(), fenetre.getNomP(), fenetre.getNomU(), fenetre.getMdp(), 2);
        String requette = "INSERT INTO UTILISATEUR VALUES(" + user.getId() + ",'" + user.getNom() + "','" + user.getPrenom() + "','" + user.getLogin() + "','" + user.getPassword() + "','" + user.getIdRole() +"';";
        try {
          st = laConnexion.createStatement();
          st.executeUpdate(requette);
          System.out.println("Le compte a bien été créé.");
        } catch (SQLException e) {
          e.getMessage();
          System.out.println("Le compte n'a pas été créé.");
        }
      }

    public static String getEtatQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT ETAT FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        return rs.getString("ETAT");
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return "";
    }

    public static String getTitreQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT TITRE FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        return rs.getString("TITRE");
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return "";
    }

  /**
    //dans la bd, il cherche la question contenant le mot recherché
    public static List<List<String>> getQuestion(ConnexionMySQL laConnection, String mot){
        Statement st;
        List<List<String>> questionsSondage = new ArrayList<>();
        try{
            st = laConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUESTIONNAIRE;");
            while(rs.next()){
              for(List<String> questions: BiblioSQL.getQuestionQuestionnaire(laConnection, rs.getInt("IDQ"))){
                List<String> temp = new ArrayList<>();
                for(String quest: questions){
                  if(quest.contains(mot))
                  temp.add(quest);
                }
                questionsSondage.add(temp);
              }
            }
        }
        catch(Exception ex){
          ex.getMessage();
        }
        return questionsSondage;
    }
 */
 
    public static List<String> getValeurQuestion(ConnexionMySQL laConnection, int idQ, int numQuestion){
        Statement st;
        List<String> valeurs = new ArrayList<>();
        try{
            st = laConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT Valeur FROM QUESTIONNAIRE natural join QUESTION natural join VALPOSSIBLE WHERE IDQ = " + idQ + " and numQ="+numQuestion+";");
            while(rs.next()){
                valeurs.add(rs.getString("Valeur"));
            }
        }
        catch(Exception ex){
          ex.getMessage();
        }
        return valeurs;
    }

  /** 
    numQ = numéro question
    texteQ = texte de la question
    MaxVal = stocke une information différente suivant le type de question
      -m et c : indique le nombre maximum de propositions que le sondé pourra sélectionner
      -n : donne la valeur de la note maximum (le minimum étant toujours 0)
      -u et l : inutilisé
    idT = type de question (entier, caractère, etc.)
    Valeur = valeur possible de la question (quand la question est à choix fermé)  
*/
    public static List<List<Object>> getQuestionQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      List<List<Object>> questionnaire = new ArrayList<List<Object>>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT numQ, texteQ, MaxVal, typeReponse, idT FROM TYPEQUESTION natural join QUESTION Qst natural join QUESTIONNAIRE Qest WHERE Qest.IDQ = " + idQ + ";");

        int numQuestionActuelle = 0;
        while(rs.next()){
          numQuestionActuelle = rs.getInt("numQ");
          List<Object> question = new ArrayList<Object>();
          List<String> lesValeurs = getValeurQuestion(laConnection, idQ, numQuestionActuelle);

          int idQst = rs.getInt("numQ");
          String idQstS = String.valueOf(idQst);
          question.add(idQstS);
          question.add(rs.getString("texteQ"));
          question.add(rs.getString("MaxVal"));
          question.add(rs.getString("idT"));
          question.add(lesValeurs);         
          
          
          questionnaire.add(question);
        }
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return questionnaire;
    } 



  public static void setReponse(ConnexionMySQL laConnexion, Reponse rep, Sonde sonde, Utilisateur utilisateur){
    Statement st;
    try {
      st = laConnexion.createStatement();
      st.executeUpdate("INSERT INTO REPONDRE VALUES(" + rep.getIdQ() + "," + rep.getNumQ() + "," + sonde.getCaracteristique() + "," + rep.getValue() + ");");
      
    }
    catch (SQLException e) {
      e.getMessage();
    }
  }

  /**
     public static List<String> getReponse(ConnexionMySQL laConnection, int idQ){
       Statement st;
       List<String> reponses = new ArrayList<String>();
       try {
         st = laConnection.createStatement();
         ResultSet rs = st.executeQuery("SELECT * FROM REPONSE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + ";");
         while(rs.next()){
           reponses.add(rs.getString("texteR"));
         }
       }
      String.valueOf(value); catch (SQLException e) {
         e.getMessage();
       }
     }
     */
}
