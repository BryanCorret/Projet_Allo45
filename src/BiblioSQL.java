import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BiblioSQL {

    //récupérer l'id le plus haut
    public static int getMaxID(ConnexionMySQL laConnection){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(IDU) IDU FROM UTILISATEUR;");  
        rs.first();  
        return rs.getInt("IDU") +1;
      } catch (SQLException e) {
        e.printStackTrace();
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
        Utilisateur user = new Utilisateur(BiblioSQL.getMaxID(laConnexion), fenetre.getNomF(), fenetre.getNomP(), fenetre.getNomU(), fenetre.getMdp(), fenetre.getRole());
        String requette = "INSERT INTO UTILISATEUR VALUES(" + user.getId() + ",'" + user.getNom() + "','" + user.getPrenom() + "','" + user.getLogin() + "','" + user.getPassword() + "'," + user.getIdRole() +");";
        try{
          st = laConnexion.createStatement();
          st.executeUpdate(requette);
          System.out.println("Le compte a bien été créé.");
        } catch (SQLException e) {
          e.printStackTrace();
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
            String requette5 = "GRANT ALL ON sondage.* TO " + user.getLogin() +";";
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

    //dans la bd, il cherche la question contenant le mot recherché
    // public static List<List<String>> getQuestion(ConnexionMySQL laConnection, String mot){
    //     Statement st;
    //     List<List<String>> questionsSondage = new ArrayList<>();
    //     try{
    //         st = laConnection.createStatement();
    //         ResultSet rs = st.executeQuery("SELECT * FROM QUESTIONNAIRE;");
    //         while(rs.next()){
    //           for(List<String> questions: BiblioSQL.getQuestionQuestionnaire(laConnection, rs.getInt("IDQ"))){
    //             List<String> temp = new ArrayList<>();
    //             for(String quest: questions){
    //               if(quest.contains(mot))
    //               temp.add(quest);
    //             }
    //             questionsSondage.add(temp);
    //           }
    //         }
    //     }
    //     catch(Exception ex){
    //       ex.getMessage();
    //     }
    //     return questionsSondage;
    // }
 


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

    public static String getTypeRepQuestion(ConnexionMySQL laConnection, int idQ, int numQuestion){
      Statement st;
      String res = "";
      try{
          st = laConnection.createStatement();
          ResultSet rs = st.executeQuery("SELECT idT FROM QUESTION natural join TYPEQUESTION WHERE IDQ = " + idQ + " and numQ="+numQuestion+";");
          while(rs.next()){
            res = rs.getString("idT");
          }
          return res;
      }
      catch(Exception ex){
        ex.getMessage();
      }
      return res;
  }




  /**
     ____                  _   _                         _          
  / __ \                | | (_)                       (_)         
 | |  | |_   _  ___  ___| |_ _  ___  _ __  _ __   __ _ _ _ __ ___ 
 | |  | | | | |/ _ \/ __| __| |/ _ \| '_ \| '_ \ / _` | | '__/ _ \
 | |__| | |_| |  __/\__ \ |_| | (_) | | | | | | | (_| | | | |  __/
  \___\_\\__,_|\___||___/\__|_|\___/|_| |_|_| |_|\__,_|_|_|  \___|
  
  */  


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
 public static List<String> getListQuestionnaires(ConnexionMySQL laConnection){
        Statement st;
        List<String> valeurs = new ArrayList<>();
        try{
            st = laConnection.createStatement();
            ResultSet rs = st.executeQuery("SELECT IDQ, TITRE FROM QUESTIONNAIRE;");
            while(rs.next()){
              valeurs.add(rs.getString("IDQ") + " - " + rs.getString("TITRE"));
            }
        }
        catch(Exception ex){
          ex.getMessage();
        }
        return valeurs;
    }
    public static List<Question> getQuestionQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      List<Question> questionnaire = new ArrayList<Question>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT Qst.numQ, Qst.texteQ,TQ.typeReponse, Qst.MaxVal, Qst.idT, Valeur FROM TYPEQUESTION TQ join QUESTION Qst on TQ.idT = Qst.idT join VALPOSSIBLE VP on Qst.idQ=VP.idQ join QUESTIONNAIRE Qest on Qest.idQ=Qst.idQ WHERE Qest.IDQ ="+ idQ +" GROUP BY Qst.numQ;");
        while(rs.next()){
          Question ques = new Question(rs.getInt("Qst.numQ"),rs.getString("Qst.texteQ"),rs.getInt("Qst.MaxVal"),rs.getString("Qst.idT").charAt(0),idQ);
          questionnaire.add(ques);
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      return questionnaire;
    }
    public static Questionnaire getQuestionnaire(ConnexionMySQL laConnexion, int idQ){
      Statement st;
      Questionnaire q;
      List<Question> questions = getQuestionQuestionnaire(laConnexion, idQ);
      try{
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        rs.first();
        q = new Questionnaire(rs.getInt("idQ"), rs.getString("Titre"), rs.getString("Etat"));
        rs.close();
        for(Question question:questions){
          q.addQuestion(question);
      }
        return q;
    }
      catch(SQLException e){
        e.printStackTrace();
      }
      return null;
    }

    public static Questionnaire getQuestionnaire(ConnexionMySQL laConnexion, String nomQ){
      Statement st;
      Questionnaire q;
      try{
        st = laConnexion.createStatement();
        System.out.println("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE titre = '" + nomQ + "';");
        ResultSet rs1 = st.executeQuery("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE titre = '" + nomQ + "';");
        rs1.first();
        int idQ = rs1.getInt("idQ");

        List<Question> questions = getQuestionQuestionnaire(laConnexion, idQ);
        System.out.println(questions);
        ResultSet rs = st.executeQuery("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        rs.first();
        q = new Questionnaire(rs.getInt("idQ"), rs.getString("Titre"), rs.getString("Etat"));
        rs.close();
        for(Question question:questions){
          q.addQuestion(question);
      }
        return q;
    }
      catch(SQLException e){
        e.printStackTrace();
      }
      return null;
    }

    public static Questionnaire getQuestionnaireId(ConnexionMySQL laConnexion, String IdQ){
      Statement st;
      try{
        st = laConnexion.createStatement();
        System.out.println("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE idQ = '" + IdQ.charAt(0) + "';");
        ResultSet rs1 = st.executeQuery("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE idQ = '" + IdQ.charAt(0) + "';");
        rs1.first();
        int idQ = rs1.getInt("idQ");

        List<Question> questions = getQuestionQuestionnaire(laConnexion, idQ);
        System.out.println(questions);
        ResultSet rs = st.executeQuery("SELECT idQ,Titre,Etat FROM QUESTIONNAIRE WHERE IDQ = " + idQ + ";");
        rs.first();
        Questionnaire q = new Questionnaire(rs.getInt("idQ"), rs.getString("Titre"), rs.getString("Etat"));
        rs.close();
        for(Question question:questions){
          q.addQuestion(question);
      }
        return q;
    }
      catch(SQLException e){
        e.printStackTrace();
      }
      return null;
    }

/**
     _____                                 
    |  __ \                                
    | |__) |___ _ __   ___  _ __  ___  ___ 
    |  _  // _ \ '_ \ / _ \| '_ \/ __|/ _ \
    | | \ \  __/ |_) | (_) | | | \__ \  __/
    |_|  \_\___| .__/ \___/|_| |_|___/\___|
               | |                         
               |_|                         
*/


  public static void setReponse(ConnexionMySQL laConnexion, Reponse rep, Sonde sonde, Utilisateur utilisateur){
    Statement st;
    try {
      st = laConnexion.createStatement();
      st.executeUpdate("INSERT INTO REPONDRE VALUES(" + rep.getIdQ() + "," + rep.getNumQ() + ",'" + sonde.getCaracteristique() + "','" + rep.getValue() + "');");

      st.executeUpdate("INSERT INTO INTERROGER VALUES("+utilisateur.getId()+","+sonde.getNumSond()+","+rep.getIdQ()+");");
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }



    public static List<Reponse> getLesReponses(ConnexionMySQL laConnection, int idQ){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT idQ,numQ,idC,valeur FROM REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + ";");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }
     
     public static List<Reponse> getReponseAUneQuestion(ConnexionMySQL laConnection, int idQ, int numQ){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT idQ,numQ,idC,valeur FROM REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + " and numQ="+numQ+";");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }

     public static List<Reponse> getReponseFemmeAUneQuestion(ConnexionMySQL laConnection, int idQ, int numQ){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery(" SELECT distinct idQ,numQ,idC,valeur FROM CARACTERISTIQUE natural join REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ ="+idQ+" and numQ="+numQ+"and sexe='F';");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }

     public static List<Reponse> getReponseHommeAUneQuestion(ConnexionMySQL laConnection, int idQ, int numQ){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery(" SELECT distinct idQ,numQ,idC,valeur FROM CARACTERISTIQUE natural join REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ ="+idQ+" and numQ="+numQ+"and sexe='F';");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }


     public static List<Reponse> getReponseEnFonctionDeLaTrancheDAge(ConnexionMySQL laConnection, int idQ, int numQ, int tranche){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT distinct idQ,numQ,idC,valeur FROM CARACTERISTIQUE natural join REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ="+idQ+" and numQ="+numQ+" and idTr="+tranche+";");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }

     public static List<Reponse> getReponseEnFonctionDuSocio(ConnexionMySQL laConnection, int idQ, int numQ, int socio){
      Statement st;
      List<Reponse> reponses = new ArrayList<Reponse>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT distinct idQ,numQ,idC,valeur FROM CARACTERISTIQUE natural join REPONDRE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ="+idQ+" and numQ="+numQ+" and idCat="+socio+";");
         while(rs.next()){
           Reponse res = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString("idC"), rs.getString("valeur"));
           reponses.add(res);
         }
       }
       catch (SQLException e) {
         e.getMessage();
       }
       return reponses;
     }





    // get tout les panels de la bd donc Liste de panel
    public static List<Panel> getToutLesPanels(ConnexionMySQL laConnection){
      Statement st;
      List<Panel> liste = new ArrayList<Panel>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM PANEL;");
        while(rs.next()){
          Panel pan = new Panel(rs.getInt("idPan"), rs.getString("nomPan"));
          liste.add(pan);
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      return liste;
    }


    // retourne une liste de tt les noms de panel
    public static List<String> getNomDeToutLesPanels(ConnexionMySQL laConnection){
      Statement st;
      List<String> liste = new ArrayList<String>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT nomPan FROM PANEL;");
        while(rs.next()){
          liste.add(rs.getString("nomPan"));
        }
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return liste;
    }


    // retourne la liste des questionnaires qui sont dans le panel donné
    public static List<String> getNomDesQuestionnaireParRapportAUnPanel(ConnexionMySQL laConnection, String nomPan){
      Statement st;
      List<String> liste = new ArrayList<String>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select idPan, nomPan, idQ, titre from PANEL natural join QUESTIONNAIRE where nomPan='"+nomPan+"';");
        while(rs.next()){
          liste.add(rs.getString("titre"));
        }
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return liste;
    }


    //donne tt les sondés qui sont dans le panel
    public static List<Sonde> getSondeParRapportAuPanel(ConnexionMySQL laConnection, String nomPan){
      Statement st;
      List<Sonde> liste = new ArrayList<Sonde>();
      try {
        st = laConnection.createStatement();
        System.out.println("select numSond, nomSond, prenomSond, dateNaisSond, telephoneSond,idC from SONDE natural join PANEL natural join QUESTIONNAIRE where nomPan ='"+nomPan+"';");
        ResultSet rs = st.executeQuery("select numSond, nomSond, prenomSond, dateNaisSond, telephoneSond,idC from SONDE natural join PANEL natural join QUESTIONNAIRE where nomPan ='"+nomPan+"';");
        while(rs.next()){
          Sonde personne = new Sonde(rs.getInt("numSond"), rs.getString("nomSond"), rs.getString("prenomSond"), rs.getDate("dateNaisSond"), rs.getString("telephoneSond"), rs.getString("idC"));
          liste.add(personne);
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      return liste;
    }
    

    // permet de savoir si le sondé a déja répondu au questionnaire
    public static boolean voirSiLeSondeADejaRep(ConnexionMySQL laConnection, int idQ, int numSond){
      String StringDeNumSond = String.valueOf(numSond);
      Statement st;
      List<String> listeDeNumSond = new ArrayList<String>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select numSond from INTERROGER where idQ ="+idQ+";");
        while(rs.next()){
          listeDeNumSond.add(rs.getString("numSond"));
        }        
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      if (listeDeNumSond.contains(StringDeNumSond)){
          return true;
      }
      return false;
        
    }


    //donne un sonde choisi au hasard dans le panel (et si il n'a pas déja répondu au Questionnaire)
    public static Sonde getUnSondeAuHasardDansLePanel(ConnexionMySQL laConnection, int idQ, String nomPan){
      List<Sonde> liste = getSondeParRapportAuPanel(laConnection, nomPan);
      for (Sonde sond : liste){
        if (!voirSiLeSondeADejaRep(laConnection,idQ,sond.getNumSond())) {
          return sond;
        }
      }
      Sonde sond = liste.get((int)Math.random()*liste.size());
      return sond;
    }
    
    
    public static int getNbDeRepDansLaQuestion(ConnexionMySQL laConnection, int idQ, int numQ, String rep ){
      Statement st;
      int cpt = 0;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT valeur res FROM REPONSE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ ="+idQ+" and numQ ="+ numQ+";");
        while(rs.next()){
          if (rep.equals(rs.getString("valeur"))){
            cpt++;
          }
        }
        return cpt;
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return -1;
    }

    public static int getOccurenceReponseDansQuestionPourCarac(ConnexionMySQL laCo,int numQ,int idQ,String rep,Integer carac){
      Statement st;
      int cpt = 0;
      try {
        st = laCo.createStatement();
        ResultSet rs = st.executeQuery("SELECT valeur res FROM REPONSE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest natural join CARACTERISTIQUE cerec WHERE IDQ ="+idQ+" and numQ ="+ numQ+ "and idTr ='"+String.valueOf(carac).charAt(0)+"';");
        while(rs.next()){
          if (rep.equals(rs.getString("valeur"))){
            cpt++;
          }
        }
        return cpt;
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return -1;
    }
    public static int getOccurenceReponseDansQuestionPourCarac(ConnexionMySQL laCo,int numQ,int idQ,String rep,String categ){
      Statement st;
      int cpt = 0;
      try {
        st = laCo.createStatement();
        ResultSet rs = st.executeQuery("SELECT valeur res FROM REPONSE Rsp natural join QUESTION Qst natural join QUESTIONNAIRE Quest natural join CARACTERISTIQUE cerec WHERE IDQ ="+idQ+" and numQ ="+ numQ+ "and idCat ='"+categ.charAt(0)+"';");
        while(rs.next()){
          if (rep.equals(rs.getString("valeur"))){
            cpt++;
          }
        }
        return cpt;
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return -1;
    }


    public static String leSexeDuCaracteristique(ConnexionMySQL laConnection,String idC){
      Statement st;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select sexe from CARACTERISTIQUE where idC = '"+idC+"';");
        String sexe = rs.getString("sexe");
        return sexe;
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return "";
    }

    public static List<String> laTrancheDAgeDuCarac(ConnexionMySQL laConnection,String idC){
      Statement st;
      List<String> liste = new ArrayList<String>();
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select idTr from CARACTERISTIQUE where idC = '"+idC+"';");
        String idTranche = rs.getString("idTr");
        ResultSet rs2 = st.executeQuery("select idTr,valDebut, valFin from TRANCHE where idTr = '"+idTranche+"';");
        String valD = rs2.getString("valDebut");
        String valF = rs2.getString("valFin");
        liste.add(valD);
        liste.add(valF);
        return liste;
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return liste;
    }

    public static int appartientCategorie(ConnexionMySQL laConnection,Reponse reponse){
      Statement st;
      String rep = reponse.getValue();
      String caracteristique = reponse.getidC();
      try{        
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select count(valeur) val from REPONDRE where valeur = '"+rep+" 'and idC ='"+caracteristique+"';");
        return rs.getInt("val");
      } catch (SQLException e){
        e.getMessage();
      }
      return -1;
    }   
    
    public static List<String> getToutLesCaracteristique(ConnexionMySQL laConnection){
      Statement st;
      List<String> liste = new ArrayList<String>();
      try{        
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select idC from CARACTERISTIQUE;");
        while(rs.next()){
          liste.add(rs.getString("idC"));
        }
        return liste;
      } catch (SQLException e){
        e.getMessage();
      }
      return liste;
    }

    public static List<String> getLesCat(ConnexionMySQL laConnection){
      Statement st;
      List<String> liste = new ArrayList<>();
      try{
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select intituleCat from CATEGORIE;");
        while(rs.next()){
          liste.add(rs.getString("intituleCat"));
      }
    }
      catch(SQLException e){
        e.getMessage();
      }
      return liste;
    }
    public static List<String> getLesTr(ConnexionMySQL laConnection){
      Statement st;
      List<String> liste = new ArrayList<>();
      try{
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("select valDebut AS d,valFin AS f from TRANCHE;");
        while(rs.next()){
          liste.add(rs.getString("d")+" - "+rs.getString("f"));
      }
    }
      catch(SQLException e){
        e.getMessage();
      }
      return liste;
    }
 
    public static int getNbQuestionDansQuestionnaire(ConnexionMySQL laConnection, int idQ){
      Statement st;
      int nbQuestion = 0;
      try {
        st = laConnection.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS nbQuestion FROM QUESTION Qst natural join QUESTIONNAIRE Quest WHERE IDQ = " + idQ + ";");
        rs.next();
        nbQuestion = rs.getInt("nbQuestion");
      }
      catch (SQLException e) {
        e.getMessage();
      }
      return nbQuestion;
    }

    public static Map<String,List<Reponse>> recupererReponses(ConnexionMySQL laCo,String tri,int idQ,int numQ){
      Map<String,List<Reponse>> res = new HashMap<>();
      List<List<Reponse>> rep = new ArrayList<>();
      Statement st;
      String requete;
      String valActu ="1";
      boolean premiereBoucle = true;
      try{
        st = laCo.createStatement();
        if (tri=="idTr"){
          requete = "SELECT idTr,idQ,numQ,valeur FROM QUESTIONNAIRE NATURAL JOIN QUESTION NATURAL JOIN REPONDRE NATURAL JOIN CARACTERISTIQUE WHERE IDQ = " + idQ +" AND NUMQ = "+numQ+ " ORDER BY"+" idTr"+";";
        }
        else{
          requete = "SELECT idCat,idQ,numQ,valeur FROM QUESTIONNAIRE NATURAL JOIN QUESTION NATURAL JOIN REPONDRE NATURAL JOIN CARACTERISTIQUE WHERE IDQ = " + idQ +" AND NUMQ = "+numQ+ " ORDER BY"+" idCat"+";";
        }
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
          if (premiereBoucle){
            rep.add(new ArrayList<Reponse>());
            premiereBoucle = false;
          }
          if (rs.getString(tri).equals(valActu)){
            Reponse reponse = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString(tri), rs.getString("valeur"));
            rep.get(Integer.valueOf(valActu)-1).add(reponse);
          }
          else{
            res.put(valActu,rep.get(Integer.valueOf(valActu)-1));
            rep.add(new ArrayList<Reponse>());
            valActu = String.valueOf(Integer.valueOf(valActu)+1);
            Reponse reponse = new Reponse(rs.getInt("idQ"), rs.getInt("numQ"), rs.getString(tri), rs.getString("valeur"));
            rep.get(Integer.valueOf(valActu)-1).add(reponse);
          }
        }
      }
      catch(SQLException e){
        e.getMessage();
      }
      return res;
    }

    public static List<Questionnaire> getLesQuestionnaires(ConnexionMySQL co){
      List<Questionnaire> l = new ArrayList<>();
      Statement st;
      try{
        st = co.createStatement();
        ResultSet rs = st.executeQuery("SELECT idQ, Titre, Etat FROM QUESTIONNAIRE");
        while(rs.next()){
          l.add(new Questionnaire(rs.getInt("idQ"), rs.getString("Titre"), rs.getString("Etat")));
        }
      }
      catch(SQLException sql){
        sql.getMessage();
      }
      return l;
    }
    
    public static void exit(ConnexionMySQL laConnexion){
      Statement st;
      try {
        st = laConnexion.createStatement();
        st.executeQuery("exit");
    }
      catch(SQLException e){

      }
    }
}
