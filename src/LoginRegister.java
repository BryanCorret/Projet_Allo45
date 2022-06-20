import java.sql.*;
import java.util.ArrayList;

import javax.management.Query;

public class LoginRegister {
  ConnexionMySQL laConnexion;
  Statement st;

  public LoginRegister(ConnexionMySQL laConnexion) {
    this.laConnexion = laConnexion;
  }

  public boolean userExists(String username, String password){
    String requete = "SELECT U.LOGIN LOG, U.MOTDEPASSE MDP FROM UTILISATEUR U;";
    try {
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

  public void login(String username, String password){
  if(userExists(username, password))
    try{
      if(laConnexion.isConnecte())
      laConnexion.close();
      laConnexion.connecter("127.0.0.1", "sondage", username, password);
    }
    catch(Exception ex){
        ex.getMessage();
    }
  }

  public void register(Utilisateur user){
    String requette = "INSERT INTO UTILISATEUR VALUES(" ;
		st = laConnexion.createStatement();
		st.executeUpdate(requette);
  }
} 
