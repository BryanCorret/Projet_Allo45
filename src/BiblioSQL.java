import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BiblioSQL {

    //récupérer l'id le plus haut
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

    //récupérer l'état du questionnaire
    public static String getEtatQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT ETAT FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        rs.next();
        return rs.getString("ETAT");
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return "";
    }

    //récupérer le nom du questionnaire
    public static String getTitreQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT TITRE FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        rs.next();
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


    //récupérer les valeurs possibles d'une question
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

      st.executeUpdate("INSERT INTO INTERROGER VALUES("+utilisateur.getId()+","+sonde.getNumSond()+","+rep.getIdQ()+");");
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


  // a modif
    // public static int getNbQuestionDansQuestionnaire(ConnexionMySQL laConnection, int idQ){
    //   Statement st;
    //   int nbQuestion = 0;
    //   try {
    //     st = laConnection.createStatement();
    //     ResultSet rs = st.executeQuery("SELECT COUNT(*) AS nbQuestion FROM QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + ";");
    //     rs.next();
    //     nbQuestion = rs.getInt("nbQuestion");
    //   }
    //   catch (SQLException e) {
    //     e.getMessage();
    //   }
    //   return nbQuestion;
    // }


    //  public static List<HashMap<String,List<Object>>> getReponseDunQuestionnaire(ConnexionMySQL laConnection, int idQ){
    //    Statement st;
    //     List<HashMap<String,List<Object>>> reponses = new ArrayList<HashMap<String,List<Object>>>();
    //     int nbQuestion = getNbQuestionDansQuestionnaire(laConnection, idQ);
    //     try {
    //       st = laConnection.createStatement();
    //       for (int i = 1; i <= nbQuestion; i++) {
    //         ResultSet rs = st.executeQuery("SELECT * FROM REPONSE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + " AND numQ = " + i + ";");
    //       }
    //     } catch (SQLException e) {
    //       e.getMessage();
    //     }
    //     return reponses;
    //  }
}
