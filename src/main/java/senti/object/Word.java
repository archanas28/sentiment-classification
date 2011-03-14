package senti.object;

import senti.object.Constants.Polarity;
import senti.object.Constants.SubjType;
import senti.object.Constants.Subjectivity;
import senti.util.Props;

public class Word {

    public int position = -1;

    public Polarity polarity = Polarity.UNKNOWN;

    public Subjectivity subjectivity = Subjectivity.UNKNOWN;

    public SubjType subjType = SubjType.UNKNOWN;

    public String POS = "";

    public String wordString;

    private static boolean use_punct_mark = false;

    static{
        if(new Props().getProperty("use_punct_mark_feature").equals("0"))
            use_punct_mark = false;
        else 
            use_punct_mark = true;
    }


    public Word(String wordString, int position, String POS){
        this.position = position;
        this.POS = POS;
        this.wordString = wordString;


        if(use_punct_mark){
            if(wordString.equals("!")){
                this.wordString = new Props().getProperty("exclamation_mark");
                this.POS = new Props().getProperty("exclamation_mark");
            }

            if(wordString.equals("?")){
                this.wordString = new Props().getProperty("question_mark");
                this.POS = new Props().getProperty("question_mark");
            }
        }

    }

    public Word(String wordString){
        this.wordString = wordString;
    }
    
    
    public boolean equals(Object obj){
        if(((Word)obj).wordString.equals(this.wordString))
                return true;
        else {
            return false;
        }
    }

}
