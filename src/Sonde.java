public class Sonde {
    private int numSond;
    private String nomSond;
    private String prenomSond;
    private String dateNaisSond;
    private String telephoneSond;

    public Sonde(int numSond, String nomSond, String prenomSond, String dateNaisSond, String telephone){
        this.numSond = numSond;
        this.nomSond = nomSond;
        this.prenomSond = prenomSond;
        this.dateNaisSond = dateNaisSond;
        this.telephoneSond = telephone;
    }

    public int getNumSond(){return this.numSond;}
    public String getNomSond(){return this.nomSond;}
    public String getPrenomSond(){return this.prenomSond;}
    public String getDateNaisSond(){return this.dateNaisSond;}
    public String getTelephoneSond(){return this.telephoneSond;}

    public void setNumSond(int numSond){this.numSond = numSond;}
    public void setNomSond(String nomSond){this.nomSond = nomSond;}
    public void setPrenomSond(String prenomSond){this.prenomSond = prenomSond;}
    public void setDateNaisSond(String dateNaisSond){this.dateNaisSond = dateNaisSond;}
    public void setTelephoneSond(String telephoneSond){this.telephoneSond = telephoneSond;}

    @Override
    public String toString(){
        return "Sonde{" +
                "numSond=" + this.getNumSond() +
                ", nomSond='" + this.getNomSond() + '\'' +
                ", prenomSond='" + this.getPrenomSond() + '\'' +
                ", dateNaisSond='" + this.getDateNaisSond() + '\'' +
                ", telephoneSond='" + this.getTelephoneSond() + '\'' +
                '}';
    }


}
