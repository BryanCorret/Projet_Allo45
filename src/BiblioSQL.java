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
            ResultSet rs = st.executeQuery("SELECT MAX(IDU) IDU FROM UTILISATEUR;");  
            rs.next();  
            return rs.getInt("IDU")+1;
		} catch (SQLException e) {
			e.getMessage();
		}
		return -1;
    }

    public static int userExists(String username, String password){
      Statement st;
      ConnexionMySQL connect = BiblioSQL.connectRoot();
      String requete = "SELECT U.LOGIN LOG, U.MOTDEPASSE MDP, U.IDR IDR FROM UTILISATEUR U;";
        try {
          st = connect.createStatement();
          ResultSet rs = st.executeQuery(requete);
          while(rs.next())
          if(rs.getString("LOG").equals(username) && rs.getString("MDP").equals(password))
          return rs.getInt("IDR");
        } catch (SQLException e) {
          e.getMessage();
          System.out.println("Problème d'existence utilisateur");
        }
        try {
          connect.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return -1;
    }

    public static Utilisateur login(ConnexionMySQL laConnexion, String username, String password){
      int valueRole = BiblioSQL.userExists(username, password);
      if(valueRole != -1){
        try{
          if(laConnexion.isConnecte())
          laConnexion.close();
          laConnexion.connecter(username, password);
          Statement st = laConnexion.createStatement();
          ResultSet rs = st.executeQuery("SELECT IDU, NOMU, PRENOMU, LOGIN, MOTDEPASSE FROM UTILISATEUR WHERE LOGIN='" + username +"';");
          rs.first();
          System.out.println(rs.getRow());
          Utilisateur tempUser = new Utilisateur(rs.getInt("IDU"), rs.getString("NOMU"), rs.getString("PRENOMU"), username, password, valueRole);
          System.out.println("Tu es connecté !");
          return tempUser;
          
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }
      }
      else{
        System.out.println("Ce compte n'existe pas");
      }
        return null;
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

      public static void register(FenetreInscription fenetre){
        Statement st;
        ConnexionMySQL laConnexion = BiblioSQL.connectRoot();
        Utilisateur user = new Utilisateur(BiblioSQL.getMaxID(laConnexion), fenetre.getNomF(), fenetre.getNomP(), fenetre.getNomU(), fenetre.getMdp(), 2);
        String requette = "INSERT INTO UTILISATEUR VALUES(" + user.getId() + ",'" + user.getNom() + "','" + user.getPrenom() + "','" + user.getLogin() + "','" + user.getPassword() + "'," + user.getIdRole() +");";
        try{
          st = laConnexion.createStatement();
          st.executeUpdate(requette);
          System.out.println("Le compte a bien été créé.");
        } catch (SQLException e) {
          e.getMessage();
          System.out.println("Le compte n'a pas été créé.");
        }
        Statement st2;
        ConnexionMySQL laCoUser = BiblioSQL.connectRoot();
        // if(user.getIdRole()==1) Le concepteur n'est pas implémenté.
        switch(user.getIdRole()){
          case 2:
            String requette12 = "DROP USER " + user.getLogin() + ";";
            String requette2 = "CREATE USER " + user.getLogin() + " IDENTIFIED BY '" + user.getPassword() + "';";
            String requette4 = "GRANT ALL ON sondage.* TO " + user.getLogin() +";";
            try {
              laConnexion.close();
              st2 = laCoUser.createStatement();
              st2.executeUpdate(requette12);
              st2.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            try {
              laConnexion.close();
              st2 = laCoUser.createStatement();
              st2.executeUpdate(requette2);
              st2.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            try {
              laConnexion.close();
              st2 = laCoUser.createStatement();
              st2.executeUpdate(requette4);
              st2.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            break;
          case 3:
            String requette3 = "CREATE USER " + user.getLogin() + " IDENTIFIED BY '" + user.getPassword() +"';";
            String requette5 = "GRANT ANALYSTE TO " + user.getLogin() +";";
            try {
              laConnexion.close();
              st2 = laCoUser.createStatement();
              st2.executeUpdate(requette3);
              st2.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            try {
              laConnexion.close();
              st2 = laCoUser.createStatement();
              st2.executeUpdate(requette5);
              st2.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
            break;
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
