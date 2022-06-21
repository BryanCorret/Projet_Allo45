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

      public void createUser(FenetreInscription ){

      }
      public void register(ConnexionMySQL laConnexion, Utilisateur user){
        Statement st;
        String requette = "INSERT INTO UTILISATEUR VALUES(" + BiblioSQL.getMaxID(laConnexion) + ",'" + user.getNom() + "','" + user.getPrenom() + "','" + user.getLogin() + "','" + user.getPassword() + "','" + user.getIdRole() +"';";
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

    public static List<List<String>> getQuestionQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      List<List<String>> questionnaire = new ArrayList<List<String>>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM QUESTION Qst natural join QUESTIONNAIRE Qest WHERE IDQ = " + idQ + ";");
        while(rs.next()){
          List<String> question = new ArrayList<String>();
          int idQst = rs.getInt("numQ");
          String idQstS = String.valueOf(idQst);
          question.add(idQstS);
          question.add(rs.getString("texteQ"));
          question.add(rs.getString("MaxVal"));
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
