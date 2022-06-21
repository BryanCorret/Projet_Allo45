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
    public static List<List<String>> getQuestionQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      List<List<String>> questionnaire = new ArrayList<List<String>>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT numQ, texteQ, MaxVal, typeReponse, idT, Valeur FROM TYPEQUESTION natural join VALPOSSIBLE natural join QUESTION Qst natural join QUESTIONNAIRE Qest WHERE Qest.IDQ = " + idQ + ";");
        while(rs.next()){
          List<String> question = new ArrayList<String>();
          int idQst = rs.getInt("numQ");
          String idQstS = String.valueOf(idQst);
          question.add(idQstS);
          question.add(rs.getString("texteQ"));
          question.add(rs.getString("MaxVal"));
          question.add(rs.getString("idT"));
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
