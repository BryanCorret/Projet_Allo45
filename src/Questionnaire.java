import java.util.*;

public class Questionnaire {
    private int idQ;
    private String TitreQ;
    private String Etat;
    private List<Question> listQ;

    public Questionnaire(int idQ, String title, String etat){
        this.idQ = idQ;
        this.TitreQ = title;
        this.Etat = etat;
        this.listQ = new ArrayList<>();
    }

    public int getIdQ() {return idQ;}
    public String getTitreQ() {return TitreQ;}
    public String getEtat() {return Etat;}
    public List<Question> getListQ() {return listQ;}

    public void setIdQ(int idQ) {this.idQ = idQ;}
    public void setTitreQ(String title) {this.TitreQ = title;}
    public void setEtat(String etat) {this.Etat = etat;}
    public void setListQ(List<Question> listQ) {this.listQ = listQ;}


    public void addQuestion(Question quest){
        listQ.add(quest);
    }

    public void removeQuestion(Question quest){
        listQ.remove(quest);
    }

    public Question getQuestion(int idQ){
        for(Question quest : listQ){
            if(quest.getNumQ() == idQ){
                return quest;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getTitreQ();
    }
}