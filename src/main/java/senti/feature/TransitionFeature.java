package senti.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import senti.object.Doc;
import senti.object.PolarizedLexicon;
import senti.object.Sentence;
import senti.object.Word;
import senti.util.FileOperation;
import senti.util.Props;

public class TransitionFeature {

    public static List<String> GetTransitionFeature(Doc doc){

        List<String> transitionFeatureList = new ArrayList<String>();
        String transWordsString = FileOperation.ReadFile(new Props().getProperty("transition_word_path"));
        List<String> transWordList = Arrays.asList(transWordsString.split("\n"));
        

        for (Sentence sentence : doc.sentList) {
            String transWord = "";
            for (Word word : sentence.wordList) 
                if(transWordList.contains(word.wordString)){
                    transWord = word.wordString;
                    break;
                }

            if(!transWord.equals(""))
                for (Word word : sentence.wordList)
                    if(!word.wordString.equals(transWord)){
                        if(word.POS.startsWith("NN") || word.POS.startsWith("VB") || word.POS.startsWith("JJ") || word.POS.startsWith("RB"))
                            transitionFeatureList.add(transWord + "_" + word.wordString);
                        if(PolarizedLexicon.polarizedLexicon.containsKey(word.wordString))
                            transitionFeatureList.add(transWord + "_" + PolarizedLexicon.polarizedLexicon.get(word.wordString) + "/" + word.POS);
                    }
        }

        return transitionFeatureList;
    }
    
    public static List<String> toStringList(List<String> transitionFeatureList){
        return transitionFeatureList;
    }
    
    
    
    
    
    

}
