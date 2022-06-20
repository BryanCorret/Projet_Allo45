public class Reponse{
    private int idQ;
    private int numQ;
    private int repNum;
    private String value;
    private int valueInt;

    public Reponse(int idQ, int numQ, int repNum, String value){
        this.idQ = idQ;
        this.numQ = numQ;
        this.repNum = repNum;
        this.value = value;
    }

    public int getIdQ(){return this.idQ;}
    public int getNumQ(){return this.numQ;}
    public int getRepNum(){return this.repNum;}
    public String getValue(){return this.value;}

    public void setIdQ(int idQ){this.idQ = idQ;}
    public void setNumQ(int numQ){this.numQ = numQ;}
    public void setRepNum(int repNum){this.repNum = repNum;}
    public void setValue(String value){this.value = value;}


    @Override
    public String toString(){
        return "Reponse: " + idQ + " " + numQ + " " + repNum + " " + value;
    }
}
