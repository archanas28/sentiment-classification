package senti.object;

import java.util.ArrayList;
import java.util.List;

import senti.object.Constants.Polarity;
import senti.preprocess.SentOpenNLP;
import senti.util.FileOperation;

public class Doc {
    
    public List<Integer> biModelRep = new ArrayList<Integer>();

    public Polarity polarity = Polarity.UNKNOWN;
    
    public Polarity predictPolarity = Polarity.UNKNOWN;

    public String content;

    public List<Sentence> sentList = new ArrayList<Sentence>();
    
    public List<Word> allWordList = new ArrayList<Word>();
    

    public Doc(String filePath, Polarity polarity){
        
        this.polarity = polarity;
        this.content = FileOperation.ReadText(filePath);
        
        //initialize all the sentences
        String[] sentences = SentOpenNLP.getSents(content);
        for(int i = 0; i < sentences.length; i++)
            sentList.add(new Sentence(sentences[i], i+1));
        
        //initialize all the words
        for (Sentence sent : sentList)
            for (Word word : sent.wordList) 
                allWordList.add(word);

    }
    
    
}
