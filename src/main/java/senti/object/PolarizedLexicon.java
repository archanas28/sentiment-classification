package senti.object;

import java.util.Hashtable;

import senti.object.Constants.Polarity;
import senti.object.Constants.SubjType;
import senti.util.FileOperation;
import senti.util.Props;

public class PolarizedLexicon {

    public static Hashtable<String, Word> polarizedLexicon = initializeLexicon();
    
    private static Hashtable<String, Word> initializeLexicon(){
        
        Hashtable<String, Word> polarizedLexicon = new Hashtable<String, Word>();
        
        String lexicon = FileOperation.ReadFile(new Props().getProperty("sentiment_lexicon_path"));
        
        String[] lines = lexicon.split("\n");
        
        for (String line : lines) {
            String[] items = line.split(" ");
            Word word = new Word(items[2].substring(6));
            
            //get subjectivity type
            if(items[0].contains(("weak")))
                word.subjType = SubjType.WEAK;
            else if (items[0].contains(("strong")))
                word.subjType = SubjType.STRONG;
            
            //get prior polarity
            if(items[5].contains("negative"))
                word.polarity = Polarity.NEG;
            else if(items[5].contains("positive"))
                word.polarity = Polarity.POS;
            else if(items[5].contains("both"))
                word.polarity = Polarity.Both;
            else if (items[5].contains("neutral"))
                word.polarity = Polarity.NEU;
            
            polarizedLexicon.put(word.wordString, word);
        }
        
        return polarizedLexicon;
    }
    
}
