public class Question{
    private int numQ;
    private String textQ;
    private int MaxVal;

    public Question(int numQ, String textQ, int MaxVal){
        this.numQ = numQ;
        this.textQ = textQ;
        this.MaxVal = MaxVal;
    }

    public int getNumQ(){return numQ;}
    public String getTextQ(){return textQ;}
    public int getMaxVal(){return MaxVal;}

    public void setNumQ(int numQ){this.numQ = numQ;}
    public void setTextQ(String textQ){this.textQ = textQ;}
    public void setMaxVal(int MaxVal){this.MaxVal = MaxVal;}

    @Override
    public String toString(){
        return "Question #" + numQ + ": " + textQ + " (Max Value: " + MaxVal + ")";
    }
}