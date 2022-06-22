public class Panel {
  private int idPan;
  private String nomPan;

  public Panel(int idPan, String nomPan){
    this.idPan = idPan;
    this.nomPan = nomPan;
  }

  public int getIdPan(){return this.idPan;}
  public String getNomPan(){return this.nomPan;}

  public void setIdPan(int id){this.idPan = id;}
  public void setNomPan(String nom){this.nomPan = nom;}
}