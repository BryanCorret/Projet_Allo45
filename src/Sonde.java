public class Sonde {
    private int numSond;
    private String nomSond;
    private String prenomSond;
    private String dateNaisSond;
    private String telephoneSond;
    private String caracteristique;

    public Sonde(int numSond, String nomSond, String prenomSond, String dateNaisSond, String telephone, String caracteristique) {
        this.numSond = numSond;
        this.nomSond = nomSond;
        this.prenomSond = prenomSond;
        this.dateNaisSond = dateNaisSond;
        this.telephoneSond = telephone;
        this.caracteristique = caracteristique;
    }

    public int getNumSond(){return this.numSond;}
    public String getNomSond(){return this.nomSond;}
    public String getPrenomSond(){return this.prenomSond;}
    public String getDateNaisSond(){return this.dateNaisSond;}
    public String getTelephoneSond(){return this.telephoneSond;}
    public String getCaracteristique(){return this.caracteristique;}

    public void setNumSond(int numSond){this.numSond = numSond;}
    public void setNomSond(String nomSond){this.nomSond = nomSond;}
    public void setPrenomSond(String prenomSond){this.prenomSond = prenomSond;}
    public void setDateNaisSond(String dateNaisSond){this.dateNaisSond = dateNaisSond;}
    public void setTelephoneSond(String telephoneSond){this.telephoneSond = telephoneSond;}
    public void setCaracteristique(String caracteristique){this.caracteristique = caracteristique;}

    @Override
    public String toString(){
        return "Sonde{" +
                "numSond=" + numSond +
                ", nomSond='" + nomSond + '\'' +
                ", prenomSond='" + prenomSond + '\'' +
                ", dateNaisSond='" + dateNaisSond + '\'' +
                ", telephoneSond='" + telephoneSond + '\'' +
                ", caracteristique='" + caracteristique + '\'' +
                '}';
    }


}
