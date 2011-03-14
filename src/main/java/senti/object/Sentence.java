package senti.object;

import java.util.ArrayList;
import java.util.List;

import senti.object.Constants.Polarity;
import senti.preprocess.PosOpenNLP;
import senti.preprocess.PunctFilter;
import senti.preprocess.WordNegation;

public class Sentence {

    //sentence's content
    public String sentenceString;
    
    //sentence's position in doc
    public int position;
    
    //sentence's polarity
    public Polarity polarity = Polarity.UNKNOWN;
    
    //transition word in this sentence
    public List<String> transWordList = new ArrayList<String>();
    
    //words contained in this sentence
    public List<Word> wordList =new ArrayList<Word>();
    
    
    //constructor
    public Sentence(String sentenceString, int position){
        
        this.sentenceString = sentenceString;
        this.position = position;
        
        //initialize the word list, wordList
        GetWords();
        
    }
    

    private void GetWords() {
        // Get word information(wordString, position, word pos)
        String[] words = sentenceString.split(" ");
        
        //extend abbreviation form of negation
        WordNegation wordNeg = new WordNegation();
        words = wordNeg.abbrNegExtension(words);
        
        //filter non-sense punctuation marks
        words = PunctFilter.filterPunct(words);
        
        String[] POSs = PosOpenNLP.getPOSs(words);
        //String[] POSs = new String[words.length];
        for(int i = 0; i < words.length; i++){
            this.wordList.add(new Word(words[i], i+1, POSs[i]));
        }
    }
    
    
    
    
}
