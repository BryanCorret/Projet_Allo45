import java.util.*;

public class Question{
    private int numQ;
    private String textQ;
    private int MaxVal;
    private char type;
    private int idQ;

    public Question(int numQ, String textQ, int MaxVal,char type,int idQ){
        this.idQ = idQ;
        this.numQ = numQ;
        this.textQ = textQ;
        this.MaxVal = MaxVal;
        this.type=type;
    }

    public int getNumQ(){return numQ;}
    public String getTextQ(){return textQ;}
    public int getMaxVal(){return MaxVal;}
    public char getType(){return type;}

    public void setNumQ(int numQ){this.numQ = numQ;}
    public void setTextQ(String textQ){this.textQ = textQ;}
    public void setMaxVal(int MaxVal){this.MaxVal = MaxVal;}
    public void setType(char a){this.type = a;}

    public List<String> getValeursPossible(ConnexionMySQL laConnection,int idQ){
        return BiblioSQL.getValeurQuestion(laConnection,idQ,numQ);
    }
    @Override
    public String toString(){
        return "Question #" + numQ + ": " + textQ + " (Max Value: " + MaxVal + ")";
    }
}