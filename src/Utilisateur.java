public class Utilisateur {
    private int idU;
    private String nomU;
    private String prenomU;
    private String loginU;
    private String passwordU;
    private int role;
  
    public Utilisateur(int id, String nom, String prenom, String login, String password, int role) {
      this.idU = id;
      this.nomU = nom;
      this.prenomU = prenom;
      this.loginU = login;
      this.passwordU = password;
      this.role = role;
    }
  
  
  public int getIdRole(){return this.role;}
  public String getNom() {return this.nomU;}
  public int getId() {return this.idU;}
  public String getPrenom() {return this.prenomU;}
  public String getPassword() {return this.passwordU;}
  public String getLogin() {return this.loginU;}
  public void setLogin(String login) {this.loginU = login;}
  public void setPrenom(String prenom) {this.prenomU = prenom;}
  public void setPassword(String password) {this.passwordU = password;}
  public void setNom(String nom) {this.nomU = nom;}
  public void setRole(int role){this.role = role;}
  public void setId(int id) {this.idU = id;}
  
    @Override
    public String toString() {
      return "Utilisateur{" + "id=" + this.getId() + ", nom='" + this.getNom() + '\'' + ", prenom='" + this.getPrenom() + '\'' + ", login='" + this.getLogin() + '\'' + ", password='" + this.getPassword() + '\'' + '}';
    }
}
    
  
  