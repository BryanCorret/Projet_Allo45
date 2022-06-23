public class Reponse{
    private int idQ;
    private int numQ;
    private String idC;
    private String value;

    public Reponse(int idQ, int numQ, String idC, String value){
        this.idQ = idQ;
        this.numQ = numQ;
        this.idC = idC;
        this.value = value;
    }

    public int getIdQ(){return this.idQ;}
    public int getNumQ(){return this.numQ;}
    public String getidC(){return this.idC;}
    public String getValue(){return this.value;}

    public void setIdQ(int idQ){this.idQ = idQ;}
    public void setNumQ(int numQ){this.numQ = numQ;}
    public void setidC(String idC){this.idC = idC;}
    public void setValue(String value){this.value = value;}


    @Override
    public String toString(){
        return "Reponse: " + idQ + " " + numQ + " " + idC + " " + value;
    }
    @Override
    public boolean equals(Object o){ //on considère qu'une réponse est différente d'une autre en fonction de sa valeur et le numéro du question
        if(o==null){
            return false;
        }
        if(o==this){
            return true;
        }
        if(o instanceof Reponse){
            Reponse rep = (Reponse)o;
            return rep.getValue()==this.getValue() && rep.getIdQ()==this.idQ && rep.getNumQ()==this.numQ;
        }
        return false;
    }
}